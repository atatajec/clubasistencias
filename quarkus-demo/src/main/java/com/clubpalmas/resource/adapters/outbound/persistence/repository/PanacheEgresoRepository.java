package com.clubpalmas.resource.adapters.outbound.persistence.repository;

import com.clubpalmas.resource.adapters.outbound.persistence.entity.EgresoEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PanacheEgresoRepository implements PanacheRepository<EgresoEntity> {

}
