package com.clubpalmas.resource.adapters.outbound.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity 
@Table(name = "egresos")
@Getter 
@Setter
public class EgresoEntity {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_egreso") 
    private LocalDate fechaEgreso;

    private Double monto;

    private String descripcion;
    
    @Column(name = "origen_fondo") 
    private String origenFondo;
}
