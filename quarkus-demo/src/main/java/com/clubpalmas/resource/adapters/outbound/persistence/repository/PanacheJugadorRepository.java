package com.clubpalmas.resource.adapters.outbound.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

import com.clubpalmas.resource.adapters.outbound.persistence.entity.JugadorEntity;

@ApplicationScoped
public class PanacheJugadorRepository implements PanacheRepository<JugadorEntity> {
    public List<JugadorEntity> buscarPorFiltro(String query) {
        String match = "%" + query.toLowerCase() + "%";
        return list("lower(nombre) like ?1 or lower(apodo) like ?1", match);
    }
}