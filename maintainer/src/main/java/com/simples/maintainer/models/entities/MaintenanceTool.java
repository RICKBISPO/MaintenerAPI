package com.simples.maintainer.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "maintenance_tool")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceTool {

    @EmbeddedId
    @NotNull(message = "id must not be null")
    private MaintenanceToolId id = new MaintenanceToolId();

    @Positive(message = "quantity used must be greater than zero")
    @Column(name = "quantity_used", nullable = false)
    private Integer quantityUsed;

    @NotNull(message = "maintenance must not be null")
    @ManyToOne
    @MapsId("maintenanceId")
    @JoinColumn(name = "maintenance_id", nullable = false)
    @JsonBackReference
    private Maintenance maintenance;

    @NotNull(message = "tool must not be null")
    @ManyToOne
    @MapsId("toolId")
    @JoinColumn(name = "tool_id", nullable = false)
    @JsonBackReference
    private Tool tool;

}
