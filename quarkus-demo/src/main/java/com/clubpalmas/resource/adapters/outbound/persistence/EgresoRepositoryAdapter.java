package com.clubpalmas.resource.adapters.outbound.persistence;

import com.clubpalmas.resource.adapters.outbound.persistence.entity.EgresoEntity;
import com.clubpalmas.resource.adapters.outbound.persistence.repository.PanacheEgresoRepository;
import com.clubpalmas.resource.domain.model.Egreso;
import com.clubpalmas.resource.ports.outbound.EgresoRepositoryPort;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EgresoRepositoryAdapter implements EgresoRepositoryPort{
    private final PanacheEgresoRepository repository;

    public EgresoRepositoryAdapter(PanacheEgresoRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Egreso guardar(Egreso egreso) {
        EgresoEntity entity = new EgresoEntity();
        entity.setFechaEgreso(egreso.getFechaEgreso());
        entity.setMonto(egreso.getMonto());
        entity.setDescripcion(egreso.getDescripcion().toUpperCase());
        entity.setOrigenFondo(egreso.getOrigenFondo());
        
        repository.persist(entity);
        egreso.setId(entity.getId());
        return egreso;
    }
}
