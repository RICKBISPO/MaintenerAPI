package com.simples.maintainer.dtos.employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record CreateEmployeeRequest(
        @NotBlank(message = "{employee.name.not-blank}")
        String name,

        @NotBlank(message = "{employee.position.not-blank}")
        String position,

        @PastOrPresent(message = "{employee.hire-date.past-or-present}")
        LocalDate hireDate
) { }

