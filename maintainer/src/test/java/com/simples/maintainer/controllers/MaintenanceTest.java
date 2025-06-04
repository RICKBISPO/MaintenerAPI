package com.simples.maintainer.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simples.maintainer.dtos.employee.CreateEmployeeRequest;
import com.simples.maintainer.dtos.maintenance.CreateMaintenanceRequest;
import com.simples.maintainer.dtos.maintenance.UpdateMaintenanceRequest;
import com.simples.maintainer.dtos.maintenance.status.CreateMaintenanceStatusRequest;
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
public class MaintenanceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestFactory testFactory;

    private Long maintenanceId;

    @BeforeEach
    public void setup() throws Exception {
        var employeeId = testFactory.createEmployee(new CreateEmployeeRequest(
                "Name Test",
                "Position Test",
                LocalDate.now()
        ));
        var maintenanceStatusId = testFactory.createMaintenanceStatus(new CreateMaintenanceStatusRequest(
                "Description Test"
        ));

        maintenanceId = testFactory.createMaintenance(new CreateMaintenanceRequest(
                "Description Test",
                LocalDate.now(),
                LocalDate.now(),
                employeeId,
                maintenanceStatusId
        ));
    }

    @Test
    public void createMaintenance() {
        Assertions.assertNotNull(maintenanceId);
    }

    @Test
    public void updateMaintenance() throws Exception {
        var request = new UpdateMaintenanceRequest(
                maintenanceId,
                null,
                null,
                null,
                null,
                null
        );

        var content = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(request);

        var result = mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/api/maintenances")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        var status = result.getResponse().getStatus();
        Assertions.assertEquals(HttpStatus.OK.value(), status);
    }

    @Test
    public void findMaintenanceById() throws Exception {
        var result = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/maintenances/{id}", maintenanceId)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        var status = result.getResponse().getStatus();
        Assertions.assertEquals(HttpStatus.OK.value(), status);
    }

    @Test
    public void deleteMaintenance() throws Exception {
        var result = mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/api/maintenances/{id}", maintenanceId)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        var status = result.getResponse().getStatus();
        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), status);
    }

}
