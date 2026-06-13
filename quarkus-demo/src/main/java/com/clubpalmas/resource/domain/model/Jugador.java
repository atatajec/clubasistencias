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
public class Jugador {
    private Long id;
    private String nombre;
    private String apodo;
    private LocalDate fechaNacimiento;
    private boolean esSocio;
    private LocalDate fechaIngresoSocio;
}
