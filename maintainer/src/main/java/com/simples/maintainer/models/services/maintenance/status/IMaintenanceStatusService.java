package com.simples.maintainer.models.services.maintenance.status;

import com.simples.maintainer.dtos.maintenance.status.CreateMaintenanceStatusRequest;
import com.simples.maintainer.dtos.maintenance.status.UpdateMaintenanceStatusRequest;
import org.springframework.http.ResponseEntity;

public interface IMaintenanceStatusService {

    ResponseEntity<?> create(CreateMaintenanceStatusRequest request);
    ResponseEntity<?> update(UpdateMaintenanceStatusRequest request);
    ResponseEntity<?> findAll();
    ResponseEntity<?> findById(Long id);
    ResponseEntity<?> delete(Long id);

}
