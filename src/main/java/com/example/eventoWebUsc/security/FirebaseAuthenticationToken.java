package com.example.eventoWebUsc.security;
import com.example.eventoWebUsc.security.FirebaseAuthenticationToken;

import com.google.firebase.auth.FirebaseToken;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class FirebaseAuthenticationToken extends AbstractAuthenticationToken {

    private final FirebaseToken firebaseToken;

    public FirebaseAuthenticationToken(FirebaseToken firebaseToken) {
        super(null);
        this.firebaseToken = firebaseToken;
        setAuthenticated(true); // Marcamos el token como autenticado
    }

    @Override
    public Object getCredentials() {
        return null; // No necesitamos credenciales aquí
    }

    @Override
    public Object getPrincipal() {
        return firebaseToken.getUid(); // Retorna el UID del usuario autenticado
    }

    public FirebaseToken getFirebaseToken() {
        return firebaseToken; // Permite obtener el token completo si es necesario
    }
}
