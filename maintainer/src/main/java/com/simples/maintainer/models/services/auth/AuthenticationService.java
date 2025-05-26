package com.simples.maintainer.models.services.auth;

import com.simples.maintainer.dtos.auth.AuthenticationRequest;
import com.simples.maintainer.dtos.auth.AuthenticationResponse;
import com.simples.maintainer.dtos.auth.RegisterRequest;
import com.simples.maintainer.dtos.auth.RoleRequest;
import com.simples.maintainer.exceptions.AlreadyExistsException;
import com.simples.maintainer.exceptions.notfound.UserNotFoundException;
import com.simples.maintainer.models.entities.User;
import com.simples.maintainer.models.repositories.UserRepository;
import com.simples.maintainer.models.services.auth.token.ITokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final ITokenService tokenService;

    public AuthenticationService(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            ITokenService tokenService
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @Override
    public ResponseEntity<?> login(AuthenticationRequest request) {
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                request.login(),
                request.password()
        );
        var authentication = authenticationManager
                .authenticate(usernamePasswordAuthenticationToken);

        var token = tokenService
                .generateToken((User) authentication.getPrincipal());

        return new  ResponseEntity<>(new AuthenticationResponse(token), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> register(RegisterRequest request) {
        if (userRepository.findByLogin(request.login()) != null) {
            throw new AlreadyExistsException("user already exists");
        }

        var encryptedPassword = new BCryptPasswordEncoder()
                .encode(request.password());

        var user = new User();
        user.setLogin(request.login());
        user.setPassword(encryptedPassword);
        user.setRole(request.role());

        var response = userRepository.save(user);

        return new  ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> updateRole(RoleRequest request) {
        var entity = userRepository.findById(request.id())
                .orElseThrow(UserNotFoundException::new);

        entity.setRole(request.role());

        var response = userRepository.save(entity);

        return new  ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
