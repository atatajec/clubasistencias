package com.clubpalmas.resource.ports.outbound;

import com.clubpalmas.resource.domain.model.HistorialSocio;

public interface HistorialSocioRepositoryPort {
    void guardar(HistorialSocio historial);
}
