package com.simples.maintainer.dtos.maintenance.tool;

import com.simples.maintainer.validation.annotations.OptionalPositive;
import jakarta.validation.constraints.NotNull;

public record UpdateMaintenanceToolRequest(
        @NotNull(message = "{employee.id.not-null}")
        Long maintenanceId,

        @NotNull(message = "{tool.id.not-null}")
        Long toolId,

        @OptionalPositive(message = "{maintenance-tool.quantity-used.positive}")
        Integer quantityUsed
) { }

