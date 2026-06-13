package com.clubpalmas.resource.adapters.outbound.mapper;

import com.clubpalmas.resource.adapters.outbound.persistence.entity.AsistenciaEntity;
import com.clubpalmas.resource.domain.model.Asistencia;
import com.clubpalmas.resource.domain.model.MetodoPago;

public class AsistenciaMapper {
    /**
     * Convierte una Entidad de Base de Datos (JPA) al Modelo de Dominio Puro.
     */
    public static Asistencia toDomain(AsistenciaEntity entity) {
        if (entity == null) {
            return null;
        }

        return Asistencia.builder()
                .id(entity.getId())
                .jugadorId(entity.getJugadorId())
                .configCuotaId(entity.getConfigCuotaId())
                .fechaAsistencia(entity.getFechaAsistencia())
                // Convertimos el String de la BD al Enum del Dominio de forma segura
                .metodoPago(MetodoPago.valueOf(entity.getMetodoPago().toUpperCase()))
                .montoPagado(entity.getMontoPagado())
                .build();
    }

    /**
     * Convierte el Modelo de Dominio Puro a una Entidad de Base de Datos (JPA).
     */
    public static AsistenciaEntity toEntity(Asistencia domain) {
        if (domain == null) {
            return null;
        }

        AsistenciaEntity entity = new AsistenciaEntity();
        entity.setId(domain.getId());
        entity.setJugadorId(domain.getJugadorId());
        entity.setConfigCuotaId(domain.getConfigCuotaId());
        entity.setFechaAsistencia(domain.getFechaAsistencia());
        // Guardamos el nombre del Enum como un String en MariaDB (EFECTIVO o YAPE)
        entity.setMetodoPago(domain.getMetodoPago().name());
        entity.setMontoPagado(domain.getMontoPagado());
        
        return entity;
    }
}
