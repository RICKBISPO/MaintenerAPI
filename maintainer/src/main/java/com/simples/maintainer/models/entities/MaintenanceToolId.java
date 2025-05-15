package com.simples.maintainer.models.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceToolId {

    private Long maintenanceId;
    private Long toolId;

}
