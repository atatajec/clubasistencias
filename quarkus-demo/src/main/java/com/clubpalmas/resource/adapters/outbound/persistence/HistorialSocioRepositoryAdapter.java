package com.clubpalmas.resource.adapters.outbound.persistence;

import com.clubpalmas.resource.adapters.outbound.persistence.entity.HistorialSocioEntity;
import com.clubpalmas.resource.adapters.outbound.persistence.repository.PanacheHistorialSocioRepository;
import com.clubpalmas.resource.domain.model.HistorialSocio;
import com.clubpalmas.resource.ports.outbound.HistorialSocioRepositoryPort;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class HistorialSocioRepositoryAdapter implements HistorialSocioRepositoryPort {
    private final PanacheHistorialSocioRepository repo;
    public HistorialSocioRepositoryAdapter(PanacheHistorialSocioRepository repo) { this.repo = repo; }

    @Override 
    @Transactional
    public void guardar(HistorialSocio h) {
        HistorialSocioEntity entity = new HistorialSocioEntity();
        entity.setJugadorId(h.getJugadorId());
        entity.setTipoEvento(h.getTipoEvento());
        entity.setFechaEvento(h.getFechaEvento());
        entity.setMotivo(h.getMotivo());
        repo.persist(entity);
    }
}