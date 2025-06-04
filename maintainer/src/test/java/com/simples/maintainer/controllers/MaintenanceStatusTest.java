package com.simples.maintainer.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simples.maintainer.dtos.maintenance.status.CreateMaintenanceStatusRequest;
import com.simples.maintainer.dtos.maintenance.status.UpdateMaintenanceStatusRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.MethodName.class)
public class MaintenanceStatusTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestFactory testFactory;

    private Long maintenanceStatusId;

    @BeforeEach
    public void setup() throws Exception {
       maintenanceStatusId = testFactory.createMaintenanceStatus(new CreateMaintenanceStatusRequest(
               "Description Test"
       ));
    }

    @Test
    public void createMaintenanceStatus() {
        Assertions.assertNotNull(maintenanceStatusId);
    }

    @Test
    public void updateMaintenance() throws Exception {
        var request = new UpdateMaintenanceStatusRequest(
                maintenanceStatusId,
                null
        );

        var content = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(request);

        var result = mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/api/maintenance-statuses")
                        .header("Authorization", "Bearer " + testFactory.getToken())
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        var status = result.getResponse().getStatus();
        Assertions.assertEquals(HttpStatus.OK.value(), status);
    }

    @Test
    public void findMaintenanceStatusById() throws Exception {
        var result = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/maintenance-statuses/{id}", maintenanceStatusId)
                        .header("Authorization", "Bearer " + testFactory.getToken())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        var status = result.getResponse().getStatus();
        Assertions.assertEquals(HttpStatus.OK.value(), status);
    }

    @Test
    public void deleteMaintenanceStatus() throws Exception {
        var result = mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/api/maintenance-statuses/{id}", maintenanceStatusId)
                        .header("Authorization", "Bearer " + testFactory.getToken())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        var status = result.getResponse().getStatus();
        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), status);
    }

}
