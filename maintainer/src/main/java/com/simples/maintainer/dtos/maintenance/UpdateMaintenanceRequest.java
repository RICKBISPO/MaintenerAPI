package com.simples.maintainer.dtos.maintenance;

import com.simples.maintainer.validation.annotations.OptionalFutureOrPresent;
import com.simples.maintainer.validation.annotations.OptionalPastOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateMaintenanceRequest(
        @NotNull(message = "{entity.id.not-null}")
        Long id,

        String description,

        @OptionalPastOrPresent(message = "{maintenance.start-date.past-or-present}")
        LocalDate startDate,

        @OptionalFutureOrPresent(message = "{maintenance.end-date.future-or-present}")
        LocalDate endDate,

        Long employeeId,

        Long statusId
) { }

