package com.clubpalmas.resource.domain.service;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;

import com.clubpalmas.resource.domain.model.Admin;
import com.clubpalmas.resource.ports.inbound.AdminUseCase;
import com.clubpalmas.resource.ports.outbound.AdminRepositoryPort;

import io.smallrye.jwt.build.Jwt;

public class AdminService implements AdminUseCase{
    private final AdminRepositoryPort adminRepository;

    public AdminService(AdminRepositoryPort adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin login(String email, String password) {
        Admin admin = adminRepository.buscarPorEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Credenciales incorrectas o usuario inactivo."));
        
        // Simulación de verificación de contraseña (Aquí usarías Bcrypt)
        if (!admin.getPasswordHash().equals(password)) {
            throw new IllegalArgumentException("Credenciales incorrectas.");
        }
        
        return admin;
    }

    public String loginYGenerarToken(String email, String password) {
        Admin admin = adminRepository.buscarPorEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Credenciales incorrectas."));
        
        if (!admin.getPasswordHash().equals(password)) {
            throw new IllegalArgumentException("Credenciales incorrectas.");
        }
        
        // Generación del Token JWT usando la API fluida de SmallRye
        return Jwt.issuer("https://club-sabado-api.com")
                .upn(admin.getEmail())
                .groups(new HashSet<>(Arrays.asList("ADMIN"))) // Rol asignado
                .claim("nombre", admin.getNombre())
                .expiresIn(Duration.ofHours(8)) // Duración del token (ideal para la jornada en cancha)
                .sign(); // Firma el token usando la llave privada configurada
    }
}
