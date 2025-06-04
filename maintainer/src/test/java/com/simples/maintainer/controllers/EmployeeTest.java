package com.simples.maintainer.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simples.maintainer.dtos.employee.CreateEmployeeRequest;
import com.simples.maintainer.dtos.employee.UpdateEmployeeRequest;
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
public class EmployeeTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestFactory testFactory;

    private Long employeeId;

    @BeforeEach
    public void setup() throws Exception {
        employeeId = testFactory.createEmployee(new CreateEmployeeRequest(
                "Name Test",
                "Position Test",
                LocalDate.now()
        ));
    }

    @Test
    public void createEmployee() {
        Assertions.assertNotNull(employeeId);
    }

    @Test
    public void updateEmployee() throws Exception {
        var request = new UpdateEmployeeRequest(
                employeeId,
                null,
                null,
                null
        );

        var content = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(request);

        var result = mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/api/employees")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        var status = result.getResponse().getStatus();
        Assertions.assertEquals(HttpStatus.OK.value(), status);
    }

    @Test
    public void findEmployeeById() throws Exception {
        var result = mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/employees/{id}", employeeId)
                                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        var status = result.getResponse().getStatus();
        Assertions.assertEquals(HttpStatus.OK.value(), status);
    }

    @Test
    public void deleteEmployee() throws Exception {
        var result = mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/api/employees/{id}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        var status = result.getResponse().getStatus();
        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), status);
    }

}
