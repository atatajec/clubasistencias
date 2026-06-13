package com.clubpalmas.resource.adapters.outbound.persistence.repository;

import java.util.Optional;

import com.clubpalmas.resource.adapters.outbound.persistence.entity.AdminEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PanacheAdminRepository implements PanacheRepository<AdminEntity> {
    public Optional<AdminEntity> buscarPorEmail(String email) {
        return find("email = ?1 and activo = true", email).firstResultOptional();
    }
}
