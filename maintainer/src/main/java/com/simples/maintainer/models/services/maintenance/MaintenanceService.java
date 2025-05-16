package com.simples.maintainer.models.services.maintenance;

import com.simples.maintainer.dtos.maintenance.CreateMaintenanceRequest;
import com.simples.maintainer.dtos.maintenance.UpdateMaintenanceRequest;
import com.simples.maintainer.exceptions.notfound.EmployeeNotFoundException;
import com.simples.maintainer.exceptions.notfound.MaintenanceNotFoundException;
import com.simples.maintainer.exceptions.notfound.MaintenanceStatusNotFoundException;
import com.simples.maintainer.models.entities.Employee;
import com.simples.maintainer.models.entities.Maintenance;
import com.simples.maintainer.models.entities.MaintenanceStatus;
import com.simples.maintainer.models.repositories.EmployeeRepository;
import com.simples.maintainer.models.repositories.MaintenanceRepository;
import com.simples.maintainer.models.repositories.MaintenanceStatusRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
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

        Optional.ofNullable(request.description()).ifPresent(entity::setDescription);
        Optional.ofNullable(request.startDate()).ifPresent(entity::setStartDate);
        Optional.ofNullable(request.endDate()).ifPresent(entity::setEndDate);
        Optional.ofNullable(request.employeeId()).ifPresent(id -> {
            var employee = employeeRepository.findById(id)
                    .orElseThrow(EmployeeNotFoundException::new);
            entity.setEmployee(employee);
        });
        Optional.ofNullable(request.statusId()).ifPresent(id -> {
            var status = maintenanceStatusRepository.findById(id)
                    .orElseThrow(MaintenanceStatusNotFoundException::new);
            entity.setStatus(status);
        });

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
