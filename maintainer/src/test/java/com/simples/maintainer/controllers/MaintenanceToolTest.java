package com.simples.maintainer.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simples.maintainer.dtos.employee.CreateEmployeeRequest;
import com.simples.maintainer.dtos.maintenance.CreateMaintenanceRequest;
import com.simples.maintainer.dtos.maintenance.status.CreateMaintenanceStatusRequest;
import com.simples.maintainer.dtos.maintenance.tool.CreateMaintenanceToolRequest;
import com.simples.maintainer.dtos.maintenance.tool.UpdateMaintenanceToolRequest;
import com.simples.maintainer.dtos.tool.CreateToolRequest;
import com.simples.maintainer.models.entities.MaintenanceToolId;
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
public class MaintenanceToolTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestFactory testFactory;

    private MaintenanceToolId maintenanceToolId;

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
        var maintenanceId = testFactory.createMaintenance(new CreateMaintenanceRequest(
                "Description Test",
                LocalDate.now(),
                LocalDate.now(),
                employeeId,
                maintenanceStatusId
        ));
        var toolId = testFactory.createTool(new CreateToolRequest(
                "Name Test",
                "Serial Code Test",
                LocalDate.now()
        ));

        maintenanceToolId = testFactory.createMaintenanceTool(new CreateMaintenanceToolRequest(
                maintenanceId,
                toolId,
                1
        ));
    }

    @Test
    public void createMaintenanceTool() throws Exception {
        testFactory.deleteTool(maintenanceToolId.getToolId());
        Assertions.assertNotNull(maintenanceToolId);
    }

    @Test
    public void updateMaintenanceTool() throws Exception {
        var request = new UpdateMaintenanceToolRequest(
                maintenanceToolId.getMaintenanceId(),
                maintenanceToolId.getToolId(),
                null
        );

        var content = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(request);

        var result = mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/api/maintenance-tools")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        var status = result.getResponse().getStatus();
        Assertions.assertEquals(HttpStatus.OK.value(), status);
    }

    @Test
    public void findMaintenanceToolById() throws Exception {
        var result = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/maintenance-tools/{maintenanceId}/{toolId}",
                                maintenanceToolId.getMaintenanceId(),
                                maintenanceToolId.getToolId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        testFactory.deleteTool(maintenanceToolId.getToolId());

        var status = result.getResponse().getStatus();
        Assertions.assertEquals(HttpStatus.OK.value(), status);
    }

    @Test
    public void deleteMaintenanceTool() throws Exception {
        var result = mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/api/maintenance-tools/{maintenanceId}/{toolId}",
                                maintenanceToolId.getMaintenanceId(),
                                maintenanceToolId.getToolId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        testFactory.deleteTool(maintenanceToolId.getToolId());

        var status = result.getResponse().getStatus();
        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), status);
    }

}
