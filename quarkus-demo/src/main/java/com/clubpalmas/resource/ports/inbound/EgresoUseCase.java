package com.clubpalmas.resource.ports.inbound;

import com.clubpalmas.resource.domain.model.Egreso;

public interface EgresoUseCase {
    Egreso registrarEgreso(Egreso egreso);
}
