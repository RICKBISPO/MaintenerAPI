package com.simples.maintainer.dtos.employee;

import java.time.LocalDate;
import java.util.Optional;

public record UpdateEmployeeRequest(
        Long id,
        Optional<String> name,
        Optional<String> position,
        Optional<LocalDate> hireDate
) { }
