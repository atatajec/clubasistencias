package com.clubpalmas.resource.ports.inbound;

import java.time.LocalDate;
import java.util.List;

import com.clubpalmas.resource.domain.model.Asistencia;

public interface AsistenciaUseCase {
    Asistencia registrarAsistencia(Long jugadorId, String metodoPago);
    List<Asistencia> obtenerAsistenciasPorFecha(LocalDate fecha);
    List<Asistencia> obtenerAsistenciasPorJugador(String nombre);
}
