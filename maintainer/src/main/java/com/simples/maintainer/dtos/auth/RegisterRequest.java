package com.simples.maintainer.dtos.auth;

import com.simples.maintainer.models.enums.UserRole;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank
        String login,

        @NotBlank
        String password,

        @NotBlank
        UserRole role
) { }
