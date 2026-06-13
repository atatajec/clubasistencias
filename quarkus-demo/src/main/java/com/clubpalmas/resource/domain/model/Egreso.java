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
public class Egreso {
    private Long id;
    private LocalDate fechaEgreso;
    private Double monto;
    private String descripcion;
    private String origenFondo; // Puede ser "SOCIO" o "CLUB" (según el punto 6)
}
