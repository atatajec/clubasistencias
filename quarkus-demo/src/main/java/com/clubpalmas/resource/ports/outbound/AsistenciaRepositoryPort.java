package com.clubpalmas.resource.ports.outbound;

import java.time.LocalDate;
import java.util.List;

import com.clubpalmas.resource.domain.model.Asistencia;

public interface AsistenciaRepositoryPort {
    Asistencia guardar(Asistencia asistencia);
    boolean existeAsistenciaEnFecha(Long jugadorId, LocalDate fecha);
    List<Asistencia> buscarPorFecha(LocalDate fecha);
    List<Asistencia> buscarPorNombreJugador(String nombre);
}
