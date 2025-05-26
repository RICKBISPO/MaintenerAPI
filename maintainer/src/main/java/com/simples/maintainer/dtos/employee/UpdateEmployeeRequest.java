package com.simples.maintainer.dtos.employee;

import com.simples.maintainer.validation.annotations.OptionalPastOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


public record UpdateEmployeeRequest(
        @NotNull(message = "{entity.id.not-null}")
        Long id,

        String name,

        String position,

        @OptionalPastOrPresent(message = "{employee.hire-date.past-or-present}")
        LocalDate hireDate
) { }

