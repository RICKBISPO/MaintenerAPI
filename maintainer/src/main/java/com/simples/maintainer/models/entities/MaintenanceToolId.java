package com.simples.maintainer.models.entities;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceToolId {

    @NotNull(message = "maintenanceId must not be null")
    private Long maintenanceId;

    @NotNull(message = "toolId must not be null")
    private Long toolId;

}
