package com.simples.maintainer.models.services.maintenance.tool;

import com.simples.maintainer.dtos.maintenance.tool.CreateMaintenanceToolRequest;
import com.simples.maintainer.dtos.maintenance.tool.UpdateMaintenanceToolRequest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface IMaintenanceToolService {

    ResponseEntity<?> create(CreateMaintenanceToolRequest request);
    ResponseEntity<?> update(UpdateMaintenanceToolRequest request);
    ResponseEntity<?> findAll(Long maintenanceId, Long toolId);
    ResponseEntity<?> findById(Long maintenanceId, Long toolId);
    ResponseEntity<?> delete(Long maintenanceId, Long toolId);

}
