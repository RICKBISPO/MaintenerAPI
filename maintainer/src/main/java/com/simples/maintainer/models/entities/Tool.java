package com.simples.maintainer.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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
@Table(name = "tool")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tool {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tool_id")
    @SequenceGenerator(name = "seq_tool_id", sequenceName = "seq_tool_id", allocationSize = 1)
    @NotNull(message = "id must not be null")
    @Column(name = "tool_id", nullable = false)
    private Long id;

    @NotBlank(message = "name must not be blank")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "serial code must not be blank")
    @Column(name = "serial_code", nullable = false)
    private String serialCode;

    @Column(name = "purchase_date", nullable = false)
    private LocalDate purchaseDate;

    @OneToMany(mappedBy = "tool", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<MaintenanceTool> maintenances = new ArrayList<>();

}
