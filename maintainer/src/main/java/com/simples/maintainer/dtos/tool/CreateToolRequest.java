package com.simples.maintainer.dtos.tool;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record CreateToolRequest(
        @NotBlank(message = "{entity.name.not-blank}")
        String name,

        @NotBlank(message = "{tool.serial-code.not-blank}")
        String serialCode,

        @PastOrPresent(message = "{tool.purchase-date.past-or-present}")
        LocalDate purchaseDate
) { }
