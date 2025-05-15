package com.simples.maintainer.dtos.maintenance;

import java.time.LocalDate;
import java.util.Optional;

public record UpdateMaintenanceRequest(
        Long id,
        Optional<String> description,
        Optional<LocalDate> startDate,
        Optional<LocalDate> endDate,
        Optional<Long> employeeId,
        Optional<Long> statusId
) { }
