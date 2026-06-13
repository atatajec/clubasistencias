package com.clubpalmas.resource.adapters.config;

import com.clubpalmas.resource.domain.service.AdminService;
import com.clubpalmas.resource.domain.service.AsistenciaService;
import com.clubpalmas.resource.domain.service.EgresoService;
import com.clubpalmas.resource.domain.service.JugadorService;
import com.clubpalmas.resource.ports.inbound.AdminUseCase;
import com.clubpalmas.resource.ports.inbound.AsistenciaUseCase;
import com.clubpalmas.resource.ports.inbound.EgresoUseCase;
import com.clubpalmas.resource.ports.inbound.JugadorUseCase;
import com.clubpalmas.resource.ports.outbound.AdminRepositoryPort;
import com.clubpalmas.resource.ports.outbound.AsistenciaRepositoryPort;
import com.clubpalmas.resource.ports.outbound.CuotaRepositoryPort;
import com.clubpalmas.resource.ports.outbound.EgresoRepositoryPort;
import com.clubpalmas.resource.ports.outbound.HistorialSocioRepositoryPort;
import com.clubpalmas.resource.ports.outbound.JugadorRepositoryPort;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class BeanConfiguration {
    @Produces 
    @ApplicationScoped
    public JugadorUseCase jugadorUseCase(JugadorRepositoryPort jRepo, HistorialSocioRepositoryPort hRepo) {
        return new JugadorService(jRepo, hRepo);
    }

    @Produces 
    @ApplicationScoped
    public AsistenciaUseCase asistenciaUseCase(AsistenciaRepositoryPort aRepo, CuotaRepositoryPort cRepo) {
        return new AsistenciaService(aRepo, cRepo);
    }

    @Produces @ApplicationScoped
    public AdminUseCase adminUseCase(AdminRepositoryPort adminRepo) {
        return new AdminService(adminRepo);
    }

    @Produces @ApplicationScoped
    public EgresoUseCase egresoUseCase(EgresoRepositoryPort egresoRepo) {
        return new EgresoService(egresoRepo);
    }
}
