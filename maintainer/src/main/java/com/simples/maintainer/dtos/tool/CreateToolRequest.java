package com.simples.maintainer.dtos.tool;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record CreateToolRequest(
        @NotBlank(message = "name must not be blank")
        String name,

        @NotBlank(message = "serial code must not be blank")
        String serialCode,

        @PastOrPresent(message = "purchase date cannot be in the future")
        LocalDate purchaseDate
) { }
