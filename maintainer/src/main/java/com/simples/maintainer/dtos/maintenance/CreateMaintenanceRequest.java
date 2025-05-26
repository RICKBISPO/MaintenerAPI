package com.simples.maintainer.dtos.maintenance;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record CreateMaintenanceRequest(
        @NotBlank(message = "{entity.description.not-blank}")
        String description,

        @PastOrPresent(message = "{maintenance.start-date.past-or-present}")
        LocalDate startDate,

        @FutureOrPresent(message = "{maintenance.end-date.future-or-present}")
        LocalDate endDate,

        @NotNull(message = "{employee.id.not-null}")
        Long employeeId,

        @NotNull(message = "{maintenance-status.id.not-null}")
        Long statusId
) { }

