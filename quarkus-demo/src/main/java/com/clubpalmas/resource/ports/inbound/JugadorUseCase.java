package com.clubpalmas.resource.ports.inbound;

import java.util.List;

import com.clubpalmas.resource.domain.model.Jugador;

public interface JugadorUseCase {
    Jugador registrarJugador(Jugador jugador);
    List<Jugador> buscarPorNombreOApodo(String query);
    void cambiarEstadoSocio(Long jugadorId, boolean esSocio, String motivo);
    List<Jugador> listarJugadores();
}
