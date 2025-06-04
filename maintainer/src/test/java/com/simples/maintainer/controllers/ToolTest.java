package com.simples.maintainer.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simples.maintainer.dtos.tool.CreateToolRequest;
import com.simples.maintainer.dtos.tool.UpdateToolRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.MethodName.class)
public class ToolTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestFactory testFactory;

    private Long toolId;

    @BeforeEach
    public void setup() throws Exception {
       toolId = testFactory.createTool(new CreateToolRequest(
               "Name Test",
               "Serial Code Test",
               LocalDate.now()
       ));
    }

    @Test
    public void createTool() throws Exception {
        testFactory.deleteTool(toolId);
        Assertions.assertNotNull(toolId);
    }

    @Test
    public void updateTool() throws Exception {
        var request = new UpdateToolRequest(
                toolId,
                null,
                null,
                null
        );

        var content = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(request);

        var result = mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/api/tools")
                        .header("Authorization", "Bearer " + testFactory.getToken())
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        var status = result.getResponse().getStatus();
        Assertions.assertEquals(HttpStatus.OK.value(), status);
    }

    @Test
    public void findToolById() throws Exception {
        var result = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/tools/{id}", toolId)
                        .header("Authorization", "Bearer " + testFactory.getToken())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        testFactory.deleteTool(toolId);

        var status = result.getResponse().getStatus();
        Assertions.assertEquals(HttpStatus.OK.value(), status);
    }

    @Test
    public void deleteTool() throws Exception {
        var result = mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/api/tools/{id}", toolId)
                        .header("Authorization", "Bearer " + testFactory.getToken())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        var status = result.getResponse().getStatus();
        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), status);
    }

}
