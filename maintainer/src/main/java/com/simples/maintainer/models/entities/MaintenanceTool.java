package com.simples.maintainer.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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
    private MaintenanceToolId id = new MaintenanceToolId();

    @Column(name = "quantity_used", nullable = false)
    private Integer quantityUsed;

    @ManyToOne
    @MapsId("maintenanceId")
    @JoinColumn(name = "maintenance_id", nullable = false)
    @JsonBackReference
    private Maintenance maintenance;

    @ManyToOne
    @MapsId("toolId")
    @JoinColumn(name = "tool_id", nullable = false)
    @JsonBackReference
    private Tool tool;

}
