package com.simples.maintainer.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Set<String> WHITELIST = new HashSet<>();

    static {
        WHITELIST.add("/swagger-ui/**");
        WHITELIST.add("/v3/api-docs/**");
        WHITELIST.add("/api/auth/**");
    }

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        String requestURI = request.getRequestURI();

        if (WHITELIST.stream().anyMatch(requestURI::startsWith)) {
            return;
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        body.put("error", "Unauthorized");
        body.put("message", "Authentication is required to access this resource.");
        body.put("path", request.getRequestURI());

        ObjectMapper mapper = new ObjectMapper();
        response.getOutputStream().println(mapper.writeValueAsString(body));
    }

}
