package com.clubpalmas.resource.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguracionCuota {
    private Long id;
    private Double montoTotal;
    private Double montoSocio;
    private Double montoClub;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private boolean activo;
}
