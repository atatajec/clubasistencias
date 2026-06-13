package com.clubpalmas.resource.adapters.outbound.persistence;

import java.util.Optional;

import com.clubpalmas.resource.adapters.outbound.persistence.repository.PanacheAdminRepository;
import com.clubpalmas.resource.domain.model.Admin;
import com.clubpalmas.resource.ports.outbound.AdminRepositoryPort;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AdminRepositoryAdapter implements AdminRepositoryPort {
    private final PanacheAdminRepository repository;

    public AdminRepositoryAdapter(PanacheAdminRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Admin> buscarPorEmail(String email) {
        return repository.buscarPorEmail(email)
                .map(e -> Admin.builder()
                        .id(e.getId())
                        .nombre(e.getNombre())
                        .email(e.getEmail())
                        .passwordHash(e.getPasswordHash())
                        .activo(e.isActivo())
                        .createdAt(e.getCreatedAt())
                        .build());
    }
}
