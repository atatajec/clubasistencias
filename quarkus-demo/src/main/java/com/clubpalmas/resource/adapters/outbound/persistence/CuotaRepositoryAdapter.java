package com.clubpalmas.resource.adapters.outbound.persistence;

import java.util.Optional;

import com.clubpalmas.resource.adapters.outbound.persistence.repository.PanacheCuotaRepository;
import com.clubpalmas.resource.domain.model.ConfiguracionCuota;
import com.clubpalmas.resource.ports.outbound.CuotaRepositoryPort;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CuotaRepositoryAdapter implements CuotaRepositoryPort {
    private final PanacheCuotaRepository repository;

    public CuotaRepositoryAdapter(PanacheCuotaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<ConfiguracionCuota> buscarVigente() {
        return repository.buscarActiva()
                .map(entity -> ConfiguracionCuota.builder()
                        .id(entity.getId())
                        .montoTotal(entity.getMontoTotal())
                        .montoSocio(entity.getMontoSocio())
                        .montoClub(entity.getMontoClub())
                        .activo(entity.isActivo())
                        .build());
    }
}
