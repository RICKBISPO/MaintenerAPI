package com.simples.maintainer.dtos.maintenance;

import java.time.LocalDate;

public record CreateMaintenanceRequest(
        String description,
        LocalDate startDate,
        LocalDate endDate,
        Long employeeId,
        Long statusId
) { }
