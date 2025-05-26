package com.simples.maintainer.dtos.auth;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequest(
        @NotBlank
        String login,

        @NotBlank
        String password
) { }
