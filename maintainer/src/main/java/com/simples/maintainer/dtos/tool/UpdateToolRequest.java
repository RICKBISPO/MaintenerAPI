package com.simples.maintainer.dtos.tool;

import java.time.LocalDate;
import java.util.Optional;

public record UpdateToolRequest(
        Long id,
        Optional<String> name,
        Optional<String> serialCode,
        Optional<LocalDate> purchaseDate
) { }
