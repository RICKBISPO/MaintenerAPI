package com.simples.maintainer.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_employee_id")
    @SequenceGenerator(name = "seq_employee_id", sequenceName = "seq_employee_id", allocationSize = 1)
    @NotNull(message = "{entity.id}")
    @Column(name = "employee_id", nullable = false)
    private Long id;

    @NotBlank(message = "{employee.name.not-blank}")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "{employee.position.not-blank}")
    @Column(name = "position", nullable = false)
    private String position;

    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

}
