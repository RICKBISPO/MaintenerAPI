package com.simples.maintainer.controllers;

import com.simples.maintainer.dtos.maintenance.tool.CreateMaintenanceToolRequest;
import com.simples.maintainer.dtos.maintenance.tool.UpdateMaintenanceToolRequest;
import com.simples.maintainer.models.services.maintenance.tool.IMaintenanceToolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/maintenance-tools")
@Tag(name = "maintenance-tools")
public class MaintenanceToolController {

    private final IMaintenanceToolService maintenanceToolService;

    public MaintenanceToolController(IMaintenanceToolService maintenanceToolService) {
        this.maintenanceToolService = maintenanceToolService;
    }

    @Operation(
            summary = "Create a new maintenance-tool association",
            description = "Creates a new association between a maintenance and a tool with the provided data."
    )
    @PostMapping
    public ResponseEntity<?> createMaintenanceTool(@RequestBody CreateMaintenanceToolRequest request) {
        return maintenanceToolService.create(request);
    }

    @Operation(
            summary = "Update an existing maintenance-tool association",
            description = "Updates an existing maintenance-tool association with the provided data."
    )
    @PutMapping
    public ResponseEntity<?> updateMaintenanceTool(@RequestBody UpdateMaintenanceToolRequest request) {
        return maintenanceToolService.update(request);
    }

    @Operation(
            summary = "Get all maintenance-tool associations",
            description = "Retrieves a list of maintenance-tool associations, " +
                    "optionally filtered by maintenanceId or toolId."
    )
    @GetMapping
    public ResponseEntity<?> findAllMaintenanceTools(
            @RequestParam(required = false) Long maintenanceId,
            @RequestParam(required = false) Long toolId
    ) {
        return maintenanceToolService.findAll(maintenanceId, toolId);
    }

    @Operation(
            summary = "Get maintenance-tool association by maintenanceId and toolId",
            description = "Retrieves a specific maintenance-tool " +
                    "association by the composite key of maintenanceId and toolId."
    )
    @GetMapping("/{maintenanceId}/{toolId}")
    public ResponseEntity<?> findMaintenanceToolById(
            @PathVariable Long maintenanceId,
            @PathVariable Long toolId
    ) {
        return maintenanceToolService.findById(maintenanceId, toolId);
    }

    @Operation(
            summary = "Delete maintenance-tool association by maintenanceId and toolId",
            description = "Deletes a maintenance-tool " +
                    "association using the composite key of maintenanceId and toolId."
    )
    @DeleteMapping("/{maintenanceId}/{toolId}")
    public ResponseEntity<?> deleteMaintenanceTool(
            @PathVariable Long maintenanceId,
            @PathVariable Long toolId
    ) {
        return maintenanceToolService.delete(maintenanceId, toolId);
    }

}
