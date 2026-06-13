package com.clubpalmas.resource.adapters.outbound.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "jugadores")
@Getter @Setter
public class JugadorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String apodo;
    
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    
    @Column(name = "es_socio")
    private boolean esSocio;
    
    @Column(name = "fecha_ingreso_socio")
    private LocalDate fechaIngresoSocio;
}
