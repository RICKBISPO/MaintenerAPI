package com.simples.maintainer.controllers;

import com.simples.maintainer.dtos.maintenance.CreateMaintenanceRequest;
import com.simples.maintainer.dtos.maintenance.UpdateMaintenanceRequest;
import com.simples.maintainer.models.services.maintenance.IMaintenanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/maintenances")
@Tag(name = "maintenances", description = "Operations related to maintenances")
public class MaintenanceController {

    private final IMaintenanceService maintenanceService;

    public MaintenanceController(IMaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @Operation(
            summary = "Create a new maintenance record",
            description = "Creates a new maintenance entry with the provided request data."
    )
    @PostMapping
    public ResponseEntity<?> createMaintenance(@Valid @RequestBody CreateMaintenanceRequest request) {
        return maintenanceService.create(request);
    }

    @Operation(
            summary = "Update an existing maintenance record",
            description = "Updates an existing maintenance entry using the provided data."
    )
    @PutMapping
    public ResponseEntity<?> updateMaintenance(@Valid @RequestBody UpdateMaintenanceRequest request) {
        return maintenanceService.update(request);
    }

    @Operation(
            summary = "Get all maintenance records",
            description = "Retrieves a list of all maintenance entries in the system."
    )
    @GetMapping
    public ResponseEntity<?> findAllMaintenances() {
        return maintenanceService.findAll();
    }

    @Operation(
            summary = "Get maintenance record by ID",
            description = "Retrieves the details of a specific maintenance entry by its ID."
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> findMaintenanceById(@PathVariable Long id) {
        return maintenanceService.findById(id);
    }

    @Operation(
            summary = "Delete maintenance record by ID",
            description = "Deletes a maintenance entry from the system using its ID."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMaintenance(@PathVariable Long id) {
        return maintenanceService.delete(id);
    }

}
