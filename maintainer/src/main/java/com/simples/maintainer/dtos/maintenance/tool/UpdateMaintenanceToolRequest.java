package com.simples.maintainer.dtos.maintenance.tool;

import com.simples.maintainer.validation.annotations.OptionalPositive;
import jakarta.validation.constraints.NotNull;

public record UpdateMaintenanceToolRequest(
        @NotNull(message = "maintenanceId must not be null")
        Long maintenanceId,

        @NotNull(message = "toolId must not be null")
        Long toolId,

        @OptionalPositive(message = "quantity used must be greater than zero")
        Integer quantityUsed
) { }

