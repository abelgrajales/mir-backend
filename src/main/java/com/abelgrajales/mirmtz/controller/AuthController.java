package com.abelgrajales.mirmtz.controller;

import com.abelgrajales.mirmtz.dto.request.AuthRequest;
import com.abelgrajales.mirmtz.dto.request.RegistroRequest;
import com.abelgrajales.mirmtz.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(RegistroRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
