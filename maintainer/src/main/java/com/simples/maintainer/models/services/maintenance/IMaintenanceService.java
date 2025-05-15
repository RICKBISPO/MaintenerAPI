package com.simples.maintainer.models.services.maintenance;

import com.simples.maintainer.dtos.maintenance.CreateMaintenanceRequest;
import com.simples.maintainer.dtos.maintenance.UpdateMaintenanceRequest;
import org.springframework.http.ResponseEntity;

public interface IMaintenanceService {

    ResponseEntity<?> create(CreateMaintenanceRequest request);
    ResponseEntity<?> update(UpdateMaintenanceRequest request);
    ResponseEntity<?> findAll();
    ResponseEntity<?> findById(Long id);
    ResponseEntity<?> delete(Long id);

}
