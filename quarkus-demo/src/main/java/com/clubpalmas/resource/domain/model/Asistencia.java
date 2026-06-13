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
public class Asistencia {
    private Long id;
    private Long jugadorId;
    private Long configCuotaId;
    private LocalDate fechaAsistencia;
    private MetodoPago metodoPago;
    private Double montoPagado;
}
