package com.simples.maintainer.dtos.maintenance.status;

import jakarta.validation.constraints.NotBlank;

public record CreateMaintenanceStatusRequest(
        @NotBlank(message = "description must not be blank")
        String description
) { }
