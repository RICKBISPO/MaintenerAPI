package com.simples.maintainer.dtos.tool;

import java.time.LocalDate;

public record CreateToolRequest(
        String name,
        String serialCode,
        LocalDate purchaseDate
) { }
