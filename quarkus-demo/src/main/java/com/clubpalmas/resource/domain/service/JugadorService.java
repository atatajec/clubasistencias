package com.clubpalmas.resource.domain.service;

import com.clubpalmas.resource.domain.exception.JugadorNoEncontradoException;
import com.clubpalmas.resource.domain.model.HistorialSocio;
import com.clubpalmas.resource.domain.model.Jugador;
import com.clubpalmas.resource.ports.inbound.JugadorUseCase;
import com.clubpalmas.resource.ports.outbound.HistorialSocioRepositoryPort;
import com.clubpalmas.resource.ports.outbound.JugadorRepositoryPort;
import java.time.LocalDate;
import java.util.List;

public class JugadorService implements JugadorUseCase {
    private final JugadorRepositoryPort jugadorRepository;
    private final HistorialSocioRepositoryPort historialSocioRepository;

    public JugadorService(JugadorRepositoryPort jugadorRepository, HistorialSocioRepositoryPort historialSocioRepository) {
        this.jugadorRepository = jugadorRepository;
        this.historialSocioRepository = historialSocioRepository;
    }

    @Override
    public Jugador registrarJugador(Jugador jugador) {
        if (jugador.isEsSocio() && jugador.getFechaIngresoSocio() == null) {
            jugador.setFechaIngresoSocio(LocalDate.now());
        }
        Jugador nuevoJugador = jugadorRepository.guardar(jugador);
        
        if (nuevoJugador.isEsSocio()) {
            historialSocioRepository.guardar(HistorialSocio.builder()
                .jugadorId(nuevoJugador.getId()).tipoEvento("ALTA")
                .fechaEvento(LocalDate.now()).motivo("Ingreso inicial como socio").build());
        }
        return nuevoJugador;
    }

    @Override
    public List<Jugador> buscarPorNombreOApodo(String query) {
        return jugadorRepository.buscarPorNombreOApodo(query);
    }

    @Override
    public void cambiarEstadoSocio(Long jugadorId, boolean esSocio, String motivo) {
        Jugador jugador = jugadorRepository.buscarPorId(jugadorId)
                .orElseThrow(() -> new JugadorNoEncontradoException(jugadorId));
        
        if (jugador.isEsSocio() == esSocio) return; // No hay cambio

        jugador.setEsSocio(esSocio);
        jugador.setFechaIngresoSocio(esSocio ? LocalDate.now() : null);
        jugadorRepository.guardar(jugador);

        historialSocioRepository.guardar(HistorialSocio.builder()
                .jugadorId(jugadorId)
                .tipoEvento(esSocio ? "ALTA" : "BAJA")
                .fechaEvento(LocalDate.now())
                .motivo(motivo)
                .build());
    }

    @Override
    public List<Jugador> listarJugadores() {
        return jugadorRepository.listarTodos();
    }
}
