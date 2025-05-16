package com.simples.maintainer.dtos.maintenance;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record CreateMaintenanceRequest(
        @NotBlank(message = "description must not be blank")
        String description,

        @PastOrPresent(message = "start date cannot be in the future")
        LocalDate startDate,

        @FutureOrPresent(message = "end date cannot be in the past")
        LocalDate endDate,

        @NotNull(message = "employee id must not be null")
        Long employeeId,

        @NotNull(message = "status id must not be null")
        Long statusId
) { }

