package com.clubpalmas.resource.adapters.outbound.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.clubpalmas.resource.adapters.outbound.persistence.entity.JugadorEntity;
import com.clubpalmas.resource.adapters.outbound.persistence.repository.PanacheJugadorRepository;
import com.clubpalmas.resource.domain.model.Jugador;
import com.clubpalmas.resource.ports.outbound.JugadorRepositoryPort;

@ApplicationScoped
public class JugadorRepositoryAdapter implements JugadorRepositoryPort {
    private final PanacheJugadorRepository repo;

    public JugadorRepositoryAdapter(PanacheJugadorRepository repo) { this.repo = repo; }

    @Override @Transactional
    public Jugador guardar(Jugador j) {
        JugadorEntity entity = repo.findById(j.getId() != null ? j.getId() : -1L);
        if (entity == null) entity = new JugadorEntity();
        
        entity.setNombre(j.getNombre().toUpperCase());
        entity.setApodo(j.getApodo().toUpperCase());
        entity.setFechaNacimiento(j.getFechaNacimiento());
        entity.setEsSocio(j.isEsSocio());
        entity.setFechaIngresoSocio(j.getFechaIngresoSocio());
        
        repo.persist(entity);
        j.setId(entity.getId());
        return j;
    }

    @Override
    public Optional<Jugador> buscarPorId(Long id) {
        return repo.findByIdOptional(id).map(e -> Jugador.builder().id(e.getId()).nombre(e.getNombre()).apodo(e.getApodo()).esSocio(e.isEsSocio()).fechaIngresoSocio(e.getFechaIngresoSocio()).fechaNacimiento(e.getFechaNacimiento()).build());
    }

    @Override
    public List<Jugador> buscarPorNombreOApodo(String query) {
        return repo.buscarPorFiltro(query).stream().map(e -> Jugador.builder().id(e.getId()).nombre(e.getNombre()).apodo(e.getApodo()).esSocio(e.isEsSocio()).fechaIngresoSocio(e.getFechaIngresoSocio()).fechaNacimiento(e.getFechaNacimiento()).build()).collect(Collectors.toList());
    }

    @Override
    public List<Jugador> listarTodos() {
        return repo.listAll().stream().map(e -> Jugador.builder().id(e.getId()).nombre(e.getNombre()).apodo(e.getApodo()).esSocio(e.isEsSocio()).fechaIngresoSocio(e.getFechaIngresoSocio()).fechaNacimiento(e.getFechaNacimiento()).build()).collect(Collectors.toList());
    }
}
