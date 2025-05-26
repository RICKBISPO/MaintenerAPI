package com.simples.maintainer.dtos.maintenance.status;

import jakarta.validation.constraints.NotNull;

public record UpdateMaintenanceStatusRequest(
        @NotNull(message = "{entity.id.not-null}")
        Long id,

        String description
) { }
