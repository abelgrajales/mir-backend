package com.abelgrajales.mirmtz.services.impl;

import com.abelgrajales.mirmtz.dto.request.AuthRequest;
import com.abelgrajales.mirmtz.dto.request.RegistroRequest;
import com.abelgrajales.mirmtz.exception.NotFoundException;
import com.abelgrajales.mirmtz.models.CentroGestor;
import com.abelgrajales.mirmtz.models.Usuario;
import com.abelgrajales.mirmtz.repositories.CentroGestorRepository;
import com.abelgrajales.mirmtz.repositories.UsuarioRepository;
import com.abelgrajales.mirmtz.services.AuthService;
import com.abelgrajales.mirmtz.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final CentroGestorRepository centroGestorRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthServiceImpl(UsuarioRepository usuarioRepository, CentroGestorRepository centroGestorRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.usuarioRepository = usuarioRepository;
        this.centroGestorRepository = centroGestorRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String register(RegistroRequest request) {
        CentroGestor centroGestor = centroGestorRepository.findById(request.getCentroGestorId())
                .orElseThrow(() -> new NotFoundException("Centro gestor no encontrado"));
        Usuario usuario = Usuario.builder()
                .correo(request.getCorreo())
                .nombre(request.getNombre())
                .password(passwordEncoder.encode(request.getPassword()))
                .centroGestor(centroGestor)
                .build();
        usuarioRepository.save(usuario);
        return jwtService.generateToken(usuario);
    }

    @Override
    public String login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getCorreo(),
                        request.getPassword())
        );
        Usuario usuario = usuarioRepository.findByCorreo(request.getCorreo())
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        return jwtService.generateToken(usuario);
    }
}
