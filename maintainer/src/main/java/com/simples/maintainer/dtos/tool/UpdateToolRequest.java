package com.simples.maintainer.dtos.tool;

import com.simples.maintainer.validation.annotations.OptionalPastOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateToolRequest(
        @NotNull(message = "{entity.id.not-null}")
        Long id,

        String name,

        String serialCode,

        @OptionalPastOrPresent(message = "{tool.purchase-date.past-or-present}")
        LocalDate purchaseDate
) { }

