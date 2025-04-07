package com.abelgrajales.mirmtz.services;

import com.abelgrajales.mirmtz.models.Usuario;

public interface JwtService {

    String generateToken(Usuario usuario);
    String extractUsername(String token);
    boolean isTokenValid(String token, Usuario usuario);

}
