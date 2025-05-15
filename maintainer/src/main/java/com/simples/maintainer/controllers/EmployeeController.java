package com.simples.maintainer.controllers;

import com.simples.maintainer.dtos.employee.CreateEmployeeRequest;
import com.simples.maintainer.dtos.employee.UpdateEmployeeRequest;
import com.simples.maintainer.models.services.employee.IEmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "employees")
public class EmployeeController {

    private final IEmployeeService employeeService;

    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(
            summary = "Create a new employee",
            description = "Creates a new employee with the provided request data."
    )
    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody CreateEmployeeRequest request) {
        return employeeService.create(request);
    }

    @Operation(
            summary = "Update an existing employee",
            description = "Updates the details of an existing employee based on the request data."
    )
    @PutMapping
    public ResponseEntity<?> updateEmployee(@RequestBody UpdateEmployeeRequest request) {
        return employeeService.update(request);
    }

    @Operation(
            summary = "Retrieve all employees",
            description = "Returns a list of all employees."
    )
    @GetMapping
    public ResponseEntity<?> findAllEmployees() {
        return employeeService.findAll();
    }

    @Operation(
            summary = "Get employee by ID",
            description = "Retrieves the details of an employee by their unique ID."
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> findEmployeeById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @Operation(
            summary = "Delete employee by ID",
            description = "Deletes an employee from the system using their unique ID."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        return employeeService.delete(id);
    }

}
