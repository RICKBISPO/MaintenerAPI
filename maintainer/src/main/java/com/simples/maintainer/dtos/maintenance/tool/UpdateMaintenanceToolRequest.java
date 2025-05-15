package com.simples.maintainer.dtos.maintenance.tool;

import java.util.Optional;

public record UpdateMaintenanceToolRequest(
        Long maintenanceId,
        Long toolId,
        Optional<Integer> quantityUsed
) { }
