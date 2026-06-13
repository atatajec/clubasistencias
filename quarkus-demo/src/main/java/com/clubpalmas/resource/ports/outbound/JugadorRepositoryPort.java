package com.clubpalmas.resource.ports.outbound;

import java.util.List;
import java.util.Optional;

import com.clubpalmas.resource.domain.model.Jugador;

public interface JugadorRepositoryPort {
    Jugador guardar(Jugador jugador);
    Optional<Jugador> buscarPorId(Long id);
    List<Jugador> buscarPorNombreOApodo(String query);
    List<Jugador> listarTodos();
}
