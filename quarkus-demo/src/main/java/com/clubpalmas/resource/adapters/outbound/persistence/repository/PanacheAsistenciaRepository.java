package com.clubpalmas.resource.adapters.outbound.persistence.repository;

import java.time.LocalDate;
import java.util.List;

import com.clubpalmas.resource.adapters.outbound.persistence.entity.AsistenciaEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PanacheAsistenciaRepository implements PanacheRepository<AsistenciaEntity> {
    public boolean existeRegistro(Long jugadorId, LocalDate fecha) {
        return count("jugadorId = ?1 and fechaAsistencia = ?2", jugadorId, fecha) > 0;
    }
    
    public List<AsistenciaEntity> buscarPorNombreJugador(String filtro) {

        String match = "%" + filtro.toLowerCase().trim() + "%";

        return getEntityManager()
                .createQuery("""
                    SELECT a
                    FROM AsistenciaEntity a
                    JOIN JugadorEntity j ON a.jugadorId = j.id
                    WHERE lower(j.nombre) LIKE :match
                    OR lower(coalesce(j.apodo, '')) LIKE :match
                    """, AsistenciaEntity.class)
                .setParameter("match", match)
                .getResultList();
    }
}
