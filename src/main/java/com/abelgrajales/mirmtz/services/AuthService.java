package com.abelgrajales.mirmtz.services;

import com.abelgrajales.mirmtz.dto.request.AuthRequest;
import com.abelgrajales.mirmtz.dto.request.RegistroRequest;

public interface AuthService {

    String register(RegistroRequest request);

    String login(AuthRequest request);
}
