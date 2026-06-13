package com.clubpalmas.resource.domain.service;

import com.clubpalmas.resource.domain.model.Asistencia;
import com.clubpalmas.resource.domain.model.MetodoPago;
import com.clubpalmas.resource.ports.inbound.AsistenciaUseCase;
import com.clubpalmas.resource.ports.outbound.AsistenciaRepositoryPort;
import com.clubpalmas.resource.ports.outbound.CuotaRepositoryPort;
import java.time.LocalDate;
import java.util.List;

public class AsistenciaService implements AsistenciaUseCase {
    private final AsistenciaRepositoryPort asistenciaRepository;
    private final CuotaRepositoryPort cuotaRepository;

    public AsistenciaService(AsistenciaRepositoryPort asistenciaRepository, CuotaRepositoryPort cuotaRepository) {
        this.asistenciaRepository = asistenciaRepository;
        this.cuotaRepository = cuotaRepository;
    }

    @Override
    public Asistencia registrarAsistencia(Long jugadorId, String metodoPagoStr) {
        LocalDate hoy = LocalDate.now();
        if (asistenciaRepository.existeAsistenciaEnFecha(jugadorId, hoy)) {
            throw new IllegalStateException("El jugador ya tiene asistencia registrada hoy.");
        }

        var cuota = cuotaRepository.buscarVigente()
                .orElseThrow(() -> new IllegalStateException("No hay configuración de cuota activa."));

        Asistencia asistencia = Asistencia.builder()
                .jugadorId(jugadorId).configCuotaId(cuota.getId())
                .fechaAsistencia(hoy).metodoPago(MetodoPago.valueOf(metodoPagoStr.toUpperCase()))
                .montoPagado(cuota.getMontoTotal()).build();

        return asistenciaRepository.guardar(asistencia);
    }

    @Override
    public List<Asistencia> obtenerAsistenciasPorFecha(LocalDate fecha) {
        return asistenciaRepository.buscarPorFecha(fecha);
    }

    @Override
    public List<Asistencia> obtenerAsistenciasPorJugador(String nombre) {
        return asistenciaRepository.buscarPorNombreJugador(nombre);
    }
}