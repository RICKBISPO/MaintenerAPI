package com.simples.maintainer.models.services.maintenance.status;

import com.simples.maintainer.dtos.maintenance.status.CreateMaintenanceStatusRequest;
import com.simples.maintainer.dtos.maintenance.status.UpdateMaintenanceStatusRequest;
import com.simples.maintainer.exceptions.notfound.MaintenanceNotFoundException;
import com.simples.maintainer.exceptions.notfound.MaintenanceStatusNotFoundException;
import com.simples.maintainer.models.entities.MaintenanceStatus;
import com.simples.maintainer.models.repositories.MaintenanceStatusRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MaintenanceStatusService implements IMaintenanceStatusService {

    private final MaintenanceStatusRepository maintenanceStatusRepository;

    public MaintenanceStatusService(MaintenanceStatusRepository maintenanceStatusRepository) {
        this.maintenanceStatusRepository = maintenanceStatusRepository;
    }

    @Override
    public ResponseEntity<?> create(CreateMaintenanceStatusRequest request) {
        var entity = new MaintenanceStatus();
        entity.setDescription(request.description());

        var response = maintenanceStatusRepository.save(entity);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> update(UpdateMaintenanceStatusRequest request) {
        var entity = maintenanceStatusRepository.findById(request.id())
                .orElseThrow(MaintenanceStatusNotFoundException::new);

        Optional.ofNullable(request.description()).ifPresent(entity::setDescription);

        var response = maintenanceStatusRepository.save(entity);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findAll() {
        var response = maintenanceStatusRepository.findAll();

        if (response.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        var response = maintenanceStatusRepository.findById(id)
                .orElseThrow(MaintenanceStatusNotFoundException::new);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        var entity = maintenanceStatusRepository.findById(id)
                .orElseThrow(MaintenanceStatusNotFoundException::new);

        maintenanceStatusRepository.delete(entity);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
