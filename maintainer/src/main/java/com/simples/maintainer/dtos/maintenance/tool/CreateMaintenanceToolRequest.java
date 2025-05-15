package com.simples.maintainer.dtos.maintenance.tool;

public record CreateMaintenanceToolRequest(
        Long maintenanceId,
        Long toolId,
        Integer quantityUsed
) { }
