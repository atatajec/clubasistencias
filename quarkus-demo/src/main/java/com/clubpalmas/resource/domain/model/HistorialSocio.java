package com.clubpalmas.resource.domain.model;

import lombok.*;
import java.time.LocalDate;

@Getter 
@Setter 
@Builder 
@NoArgsConstructor 
@AllArgsConstructor
public class HistorialSocio {
    private Long id;
    private Long jugadorId;
    private String tipoEvento; // ALTA o BAJA
    private LocalDate fechaEvento;
    private String motivo;
}
