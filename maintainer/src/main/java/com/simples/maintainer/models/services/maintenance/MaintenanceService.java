package com.simples.maintainer.models.services.maintenance;

import com.simples.maintainer.dtos.maintenance.CreateMaintenanceRequest;
import com.simples.maintainer.dtos.maintenance.UpdateMaintenanceRequest;
import com.simples.maintainer.exceptions.notfound.EmployeeNotFoundException;
import com.simples.maintainer.exceptions.notfound.MaintenanceNotFoundException;
import com.simples.maintainer.exceptions.notfound.MaintenanceStatusNotFoundException;
import com.simples.maintainer.models.entities.Maintenance;
import com.simples.maintainer.models.repositories.EmployeeRepository;
import com.simples.maintainer.models.repositories.MaintenanceRepository;
import com.simples.maintainer.models.repositories.MaintenanceStatusRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MaintenanceService implements IMaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final EmployeeRepository employeeRepository;
    private final MaintenanceStatusRepository maintenanceStatusRepository;

    public MaintenanceService(
            MaintenanceRepository maintenanceRepository,
            EmployeeRepository employeeRepository,
            MaintenanceStatusRepository maintenanceStatusRepository
    ) {
        this.maintenanceRepository = maintenanceRepository;
        this.employeeRepository = employeeRepository;
        this.maintenanceStatusRepository = maintenanceStatusRepository;
    }

    @Override
    public ResponseEntity<?> create(CreateMaintenanceRequest request) {
        var employee = employeeRepository.findById(request.employeeId())
                .orElseThrow(EmployeeNotFoundException::new);

        var status = maintenanceStatusRepository.findById(request.statusId())
                .orElseThrow(MaintenanceStatusNotFoundException::new);

        var entity = new Maintenance();
        entity.setDescription(request.description());
        entity.setStartDate(request.startDate());
        entity.setEndDate(request.endDate());
        entity.setEmployee(employee);
        entity.setStatus(status);

        var response = maintenanceRepository.save(entity);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> update(UpdateMaintenanceRequest request) {
        var entity = maintenanceRepository.findById(request.id())
                .orElseThrow(MaintenanceNotFoundException::new);

        request.description().ifPresent(entity::setDescription);
        request.startDate().ifPresent(entity::setStartDate);
        request.endDate().ifPresent(entity::setEndDate);
        request.employeeId().ifPresent(id ->
                employeeRepository.findById(id)
                        .orElseThrow(EmployeeNotFoundException::new)
        );
        request.statusId().ifPresent(id ->
                maintenanceStatusRepository.findById(id)
                        .orElseThrow(MaintenanceStatusNotFoundException::new)
        );

        var response = maintenanceRepository.save(entity);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findAll() {
        var response = maintenanceRepository.findAll();

        if (response.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        var response = maintenanceRepository.findById(id)
                .orElseThrow(MaintenanceNotFoundException::new);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        var entity = maintenanceRepository.findById(id)
                .orElseThrow(MaintenanceNotFoundException::new);

        maintenanceRepository.delete(entity);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
