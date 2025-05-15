package com.simples.maintainer.exceptions.notfound;

public class EmployeeNotFoundException extends NotFoundException {
    public EmployeeNotFoundException() {
        super("employee not found");
    }
}
