package com.simples.maintainer.dtos.tool;

import com.simples.maintainer.validation.annotations.OptionalPastOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateToolRequest(
        @NotNull(message = "id must not be null")
        Long id,

        String name,

        String serialCode,

        @OptionalPastOrPresent(message = "purchase date cannot be in the future")
        LocalDate purchaseDate
) { }

