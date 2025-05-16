package com.simples.maintainer.dtos.maintenance;

import com.simples.maintainer.validation.annotations.OptionalFutureOrPresent;
import com.simples.maintainer.validation.annotations.OptionalPastOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateMaintenanceRequest(
        @NotNull(message = "id must not be null")
        Long id,

        String description,

        @OptionalPastOrPresent(message = "start date cannot be in the future")
        LocalDate startDate,

        @OptionalFutureOrPresent(message = "end date cannot be in the past")
        LocalDate endDate,

        Long employeeId,

        Long statusId
) { }

