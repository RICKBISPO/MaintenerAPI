package com.simples.maintainer.dtos.auth;

import com.simples.maintainer.models.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RoleRequest (
        @NotNull
        Long id,

        @NotBlank
        UserRole role
) { }
