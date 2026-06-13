package com.clubpalmas.resource.domain.service;

import com.clubpalmas.resource.domain.model.Egreso;
import com.clubpalmas.resource.ports.inbound.EgresoUseCase;
import com.clubpalmas.resource.ports.outbound.EgresoRepositoryPort;

public class EgresoService implements EgresoUseCase {
    private final EgresoRepositoryPort egresoRepository;

    public EgresoService(EgresoRepositoryPort egresoRepository) {
        this.egresoRepository = egresoRepository;
    }

    @Override
    public Egreso registrarEgreso(Egreso egreso) {
        return egresoRepository.guardar(egreso);
    }
}
