package com.simples.maintainer.models.enums;

public enum UserRole {

    ADMIN("ADMIN"),
    USER("USER");

    private String role;

    UserRole(String role){
        this.role = role;
    }

}
