package com.clubpalmas.resource.adapters.outbound.persistence.repository;

import java.util.Optional;

import com.clubpalmas.resource.adapters.outbound.persistence.entity.ConfiguracionCuotaEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PanacheCuotaRepository implements PanacheRepository<ConfiguracionCuotaEntity> {
    public Optional<ConfiguracionCuotaEntity> buscarActiva() {
        return find("activo = true").firstResultOptional();
    }
}
