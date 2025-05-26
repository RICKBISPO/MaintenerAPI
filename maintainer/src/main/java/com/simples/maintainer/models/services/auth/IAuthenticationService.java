package com.simples.maintainer.models.services.auth;

import com.simples.maintainer.dtos.auth.AuthenticationRequest;
import com.simples.maintainer.dtos.auth.RegisterRequest;
import com.simples.maintainer.dtos.auth.RoleRequest;
import org.springframework.http.ResponseEntity;

public interface IAuthenticationService {

    ResponseEntity<?> login(AuthenticationRequest request);
    ResponseEntity<?> register(RegisterRequest request);
    ResponseEntity<?> updateRole(RoleRequest request);

}
