package com.simples.maintainer.exceptions.notfound;

public class MaintenanceStatusNotFoundException extends NotFoundException {
    public MaintenanceStatusNotFoundException() {
        super("maintenance status not found");
    }
}
