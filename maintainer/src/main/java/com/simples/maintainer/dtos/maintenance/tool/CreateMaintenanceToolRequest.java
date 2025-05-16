package com.simples.maintainer.dtos.maintenance.tool;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateMaintenanceToolRequest(
        @NotNull(message = "maintenanceId must not be null")
        Long maintenanceId,

        @NotNull(message = "toolId must not be null")
        Long toolId,

        @Positive(message = "quantity used must be greater than zero")
        Integer quantityUsed
) { }

