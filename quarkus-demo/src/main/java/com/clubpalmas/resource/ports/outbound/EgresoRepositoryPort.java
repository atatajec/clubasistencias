package com.clubpalmas.resource.ports.outbound;

import com.clubpalmas.resource.domain.model.Egreso;

public interface EgresoRepositoryPort {
    Egreso guardar(Egreso egreso);
}
