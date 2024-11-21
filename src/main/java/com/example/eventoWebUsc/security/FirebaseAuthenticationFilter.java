package com.example.eventoWebUsc.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;

public class FirebaseAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public FirebaseAuthenticationFilter() {
        super("/api/**"); // Aplica el filtro a rutas específicas
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            throw new AuthenticationException("Missing or invalid Authorization header") {};
        }

        try {
            FirebaseToken firebaseToken = FirebaseAuth.getInstance().verifyIdToken(token.substring(7));
            return new FirebaseAuthenticationToken(firebaseToken);
        } catch (Exception e) {
            throw new AuthenticationException("Invalid Firebase Token", e) {};
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException { // Agregar ServletException
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response); // Continúa con el siguiente filtro
    }

}
