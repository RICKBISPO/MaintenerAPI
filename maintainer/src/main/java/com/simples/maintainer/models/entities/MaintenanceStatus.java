package com.simples.maintainer.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "maintenance_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_maintenance_status_id")
    @SequenceGenerator(name = "seq_maintenance_status_id", sequenceName = "seq_maintenance_status_id", allocationSize = 1)
    @NotNull(message = "id must not be null")
    @Column(name = "status_id", nullable = false)
    private Long id;

    @NotBlank(message = "description must not be blank")
    @Column(name = "description", nullable = false)
    private String description;

}
