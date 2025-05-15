package com.simples.maintainer.dtos.employee;

import java.time.LocalDate;

public record CreateEmployeeRequest(
        String name,
        String position,
        LocalDate hireDate
) { }
