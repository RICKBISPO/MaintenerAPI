package com.simples.maintainer.controllers;

import com.simples.maintainer.dtos.maintenance.status.CreateMaintenanceStatusRequest;
import com.simples.maintainer.dtos.maintenance.status.UpdateMaintenanceStatusRequest;
import com.simples.maintainer.models.services.maintenance.status.IMaintenanceStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/maintenance-statuses")
@Tag(name = "maintenance-statuses")
public class MaintenanceStatusController {

    private final IMaintenanceStatusService maintenanceStatusService;

    public MaintenanceStatusController(IMaintenanceStatusService maintenanceStatusService) {
        this.maintenanceStatusService = maintenanceStatusService;
    }

    @Operation(
            summary = "Create a new maintenance status",
            description = "Creates a new maintenance status using the provided request data."
    )
    @PostMapping
    public ResponseEntity<?> createMaintenanceStatus(@RequestBody CreateMaintenanceStatusRequest request) {
        return maintenanceStatusService.create(request);
    }

    @Operation(
            summary = "Update an existing maintenance status",
            description = "Updates an existing maintenance status with the provided data."
    )
    @PutMapping
    public ResponseEntity<?> updateMaintenanceStatus(@RequestBody UpdateMaintenanceStatusRequest request) {
        return maintenanceStatusService.update(request);
    }

    @Operation(
            summary = "Get all maintenance statuses",
            description = "Retrieves a list of all maintenance statuses available in the system."
    )
    @GetMapping
    public ResponseEntity<?> findAllMaintenanceStatuses() {
        return maintenanceStatusService.findAll();
    }

    @Operation(
            summary = "Get maintenance status by ID",
            description = "Retrieves a specific maintenance status by its unique ID."
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> findMaintenanceStatusById(@PathVariable Long id) {
        return maintenanceStatusService.findById(id);
    }

    @Operation(
            summary = "Delete maintenance status by ID",
            description = "Deletes a maintenance status from the system using its ID."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMaintenanceStatus(@PathVariable Long id) {
        return maintenanceStatusService.delete(id);
    }

}
