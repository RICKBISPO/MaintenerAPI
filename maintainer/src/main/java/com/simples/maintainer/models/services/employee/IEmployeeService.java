package com.simples.maintainer.models.services.employee;


import com.simples.maintainer.dtos.employee.CreateEmployeeRequest;
import com.simples.maintainer.dtos.employee.UpdateEmployeeRequest;
import org.springframework.http.ResponseEntity;

public interface IEmployeeService {

    ResponseEntity<?> create(CreateEmployeeRequest request);
    ResponseEntity<?> update(UpdateEmployeeRequest request);
    ResponseEntity<?> findAll();
    ResponseEntity<?> findById(Long id);
    ResponseEntity<?> delete(Long id);

}
