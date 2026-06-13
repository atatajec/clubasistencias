package com.clubpalmas.resource.ports.outbound;

import java.util.Optional;

import com.clubpalmas.resource.domain.model.ConfiguracionCuota;

public interface CuotaRepositoryPort {
    Optional<ConfiguracionCuota> buscarVigente();
}
