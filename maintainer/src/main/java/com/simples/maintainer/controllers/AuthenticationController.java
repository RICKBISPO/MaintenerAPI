package com.simples.maintainer.controllers;

import com.simples.maintainer.dtos.auth.AuthenticationRequest;
import com.simples.maintainer.dtos.auth.RegisterRequest;
import com.simples.maintainer.dtos.auth.RoleRequest;
import com.simples.maintainer.dtos.maintenance.CreateMaintenanceRequest;
import com.simples.maintainer.dtos.maintenance.UpdateMaintenanceRequest;
import com.simples.maintainer.models.services.auth.IAuthenticationService;
import com.simples.maintainer.models.services.maintenance.IMaintenanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auths")
@Tag(name = "authentications", description = "Operations related to authentications")
public class AuthenticationController {

    private final IAuthenticationService authenticationService;

    public AuthenticationController(IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Operation(
            summary = "User login",
            description = "Authenticates a user with provided credentials and returns a JWT token on success."
    )
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        return authenticationService.login(request);
    }

    @Operation(
            summary = "User registration",
            description = "Registers a new user in the system using the provided registration data."
    )
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return authenticationService.register(request);
    }

    @Operation(
            summary = "Update user role",
            description = "Updates the role of an existing user using the provided role change data."
    )
    @PutMapping("/role")
    public ResponseEntity<?> updateRole(@RequestBody RoleRequest request) {
        return authenticationService.updateRole(request);
    }

}
