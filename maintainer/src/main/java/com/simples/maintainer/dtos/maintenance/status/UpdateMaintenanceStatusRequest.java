package com.simples.maintainer.dtos.maintenance.status;

import jakarta.validation.constraints.NotNull;

public record UpdateMaintenanceStatusRequest(
        @NotNull(message = "id must not be null")
        Long id,

        String description
) { }
