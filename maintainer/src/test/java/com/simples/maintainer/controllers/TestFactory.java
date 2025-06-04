package com.simples.maintainer.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simples.maintainer.dtos.employee.CreateEmployeeRequest;
import com.simples.maintainer.dtos.maintenance.CreateMaintenanceRequest;
import com.simples.maintainer.dtos.maintenance.status.CreateMaintenanceStatusRequest;
import com.simples.maintainer.dtos.maintenance.tool.CreateMaintenanceToolRequest;
import com.simples.maintainer.dtos.tool.CreateToolRequest;
import com.simples.maintainer.models.entities.MaintenanceToolId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Component
public class TestFactory {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    public Long createEmployee(CreateEmployeeRequest request) throws Exception {
        var content = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(request);

        var result = mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/employees")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        var resultStringPost = result.getResponse().getContentAsString();
        var resultJSONPost = objectMapper.readTree(resultStringPost);

        return resultJSONPost.get("id").asLong();
    }

    public Long createTool(CreateToolRequest request) throws Exception {
        var content = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(request);

        var result = mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/tools")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        var resultStringPost = result.getResponse().getContentAsString();
        var resultJSONPost = objectMapper.readTree(resultStringPost);

        return resultJSONPost.get("id").asLong();
    }

    public Long createMaintenance(CreateMaintenanceRequest request) throws Exception {
        var content = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(request);

        var result = mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/maintenances")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        var resultStringPost = result.getResponse().getContentAsString();
        var resultJSONPost = objectMapper.readTree(resultStringPost);

        return resultJSONPost.get("id").asLong();
    }

    public Long createMaintenanceStatus(CreateMaintenanceStatusRequest request) throws Exception {
        var content = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(request);

        var result = mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/maintenance-statuses")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        var resultStringPost = result.getResponse().getContentAsString();
        var resultJSONPost = objectMapper.readTree(resultStringPost);

        return resultJSONPost.get("id").asLong();
    }

    public MaintenanceToolId createMaintenanceTool(CreateMaintenanceToolRequest request) throws Exception {
        var content = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(request);

        var result = mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/maintenance-tools")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        var resultStringPost = result.getResponse().getContentAsString();
        var resultJSONPost = objectMapper.readTree(resultStringPost);

        return new MaintenanceToolId(
                resultJSONPost.get("id").get("maintenanceId").asLong(),
                resultJSONPost.get("id").get("toolId").asLong()
        );
    }

    public void deleteTool(Long toolId) throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/api/tools/{id}", toolId)
                        .contentType(MediaType.APPLICATION_JSON)
        );
    }

}
