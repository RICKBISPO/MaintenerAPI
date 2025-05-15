package com.simples.maintainer.exceptions.notfound;

public class MaintenanceNotFoundException extends NotFoundException {
    public MaintenanceNotFoundException() {
        super("maintenance not found");
    }
}
