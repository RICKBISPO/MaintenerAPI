package com.simples.maintainer.models.services.auth.token;

import com.simples.maintainer.models.entities.User;

public interface ITokenService {

    String generateToken(User user);
    String validateToken(String token);

}
