package com.simples.maintainer.dtos.maintenance.status;

import jakarta.validation.constraints.NotBlank;

public record CreateMaintenanceStatusRequest(
        @NotBlank(message = "{entity.description.not-blank}")
        String description
) { }
