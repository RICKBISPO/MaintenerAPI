package com.simples.maintainer.dtos.maintenance.status;

import java.util.Optional;

public record UpdateMaintenanceStatusRequest(
        Long id,
        Optional<String> description
) { }
