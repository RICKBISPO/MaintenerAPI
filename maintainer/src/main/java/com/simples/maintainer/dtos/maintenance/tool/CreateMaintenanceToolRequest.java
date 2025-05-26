package com.simples.maintainer.dtos.maintenance.tool;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateMaintenanceToolRequest(
        @NotNull(message = "{employee.id.not-null}")
        Long maintenanceId,

        @NotNull(message = "{tool.id.not-null}")
        Long toolId,

        @Positive(message = "{maintenance-tool.quantity-used.positive}")
        Integer quantityUsed
) { }

