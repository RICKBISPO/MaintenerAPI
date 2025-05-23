package com.simples.maintainer.models.services.employee;

import com.simples.maintainer.dtos.employee.CreateEmployeeRequest;
import com.simples.maintainer.dtos.employee.UpdateEmployeeRequest;
import com.simples.maintainer.exceptions.notfound.EmployeeNotFoundException;
import com.simples.maintainer.exceptions.notfound.MaintenanceNotFoundException;
import com.simples.maintainer.models.entities.Employee;
import com.simples.maintainer.models.repositories.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ResponseEntity<?> create(CreateEmployeeRequest request) {
        var entity = new Employee();
        entity.setName(request.name());
        entity.setPosition(request.position());
        entity.setHireDate(request.hireDate());

        var response = employeeRepository.save(entity);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> update(UpdateEmployeeRequest request) {
        var entity = employeeRepository.findById(request.id())
                .orElseThrow(EmployeeNotFoundException::new);

        Optional.ofNullable(request.name()).ifPresent(entity::setName);
        Optional.ofNullable(request.position()).ifPresent(entity::setPosition);
        Optional.ofNullable(request.hireDate()).ifPresent(entity::setHireDate);

        var response = employeeRepository.save(entity);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findAll(String name) {
        var response = employeeRepository.findAll();

        if (name != null) {
            response = employeeRepository.findByNameContainingIgnoreCase(name);
        }

        if (response.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        var response = employeeRepository.findById(id)
                .orElseThrow(EmployeeNotFoundException::new);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        var entity = employeeRepository.findById(id)
                .orElseThrow(EmployeeNotFoundException::new);

        employeeRepository.delete(entity);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
