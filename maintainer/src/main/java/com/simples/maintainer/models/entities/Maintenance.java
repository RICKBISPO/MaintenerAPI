package com.simples.maintainer.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "maintenance")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_maintenance_id")
    @SequenceGenerator(name = "seq_maintenance_id", sequenceName = "seq_maintenance_id", allocationSize = 1)
    @NotNull(message = "id must not be null")
    @Column(name = "maintenance_id", nullable = false)
    private Long id;

    @NotBlank(message = "description must not be blank")
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @NotNull(message = "employee must not be null")
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @NotNull(message = "status must not be null")
    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private MaintenanceStatus status;

    @OneToMany(mappedBy = "maintenance", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<MaintenanceTool> tools = new ArrayList<>();

}
