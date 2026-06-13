package com.clubpalmas.resource.adapters.outbound.persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.clubpalmas.resource.adapters.outbound.mapper.AsistenciaMapper;
import com.clubpalmas.resource.adapters.outbound.persistence.entity.AsistenciaEntity;
import com.clubpalmas.resource.adapters.outbound.persistence.repository.PanacheAsistenciaRepository;
import com.clubpalmas.resource.domain.model.Asistencia;
import com.clubpalmas.resource.ports.outbound.AsistenciaRepositoryPort;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AsistenciaRepositoryAdapter implements AsistenciaRepositoryPort {

    private final PanacheAsistenciaRepository repository;

    // Inyección por constructor limpia y óptima para pruebas
    public AsistenciaRepositoryAdapter(PanacheAsistenciaRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional // Obligatorio en Quarkus para operaciones de escritura (INSERT)
    public Asistencia guardar(Asistencia asistencia) {
        // Convertimos el modelo de dominio puro a una entidad de JPA/MariaDB
        AsistenciaEntity entity = AsistenciaMapper.toEntity(asistencia);
        
        // Panache se encarga de guardarlo en MariaDB
        repository.persist(entity);
        
        // Retornamos el dominio actualizado con el ID generado por la base de datos
        return AsistenciaMapper.toDomain(entity);
    }

    @Override
    public boolean existeAsistenciaEnFecha(Long jugadorId, LocalDate fecha) {
        // Llama al método personalizado que creamos en el PanacheAsistenciaRepository
        return repository.existeRegistro(jugadorId, fecha);
    }

    @Override
    public List<Asistencia> buscarPorFecha(LocalDate fecha) {
        // Busca todas las asistencias de una fecha específica y las mapea de vuelta a objetos de Dominio
        return repository.list("fechaAsistencia", fecha)
                .stream()
                .map(AsistenciaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Asistencia> buscarPorNombreJugador(String nombre) {
        // Busca asistencias por nombre de jugador usando una consulta personalizada
        return repository.buscarPorNombreJugador(nombre)
                .stream()
                .map(AsistenciaMapper::toDomain)
                .collect(Collectors.toList());
    }
}
