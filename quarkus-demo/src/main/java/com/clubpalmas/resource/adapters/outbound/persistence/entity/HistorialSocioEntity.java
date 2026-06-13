package com.clubpalmas.resource.adapters.outbound.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity 
@Table(name = "historial_socios")
@Getter 
@Setter
public class HistorialSocioEntity {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jugador_id") 
    private Long jugadorId;

    @Column(name = "tipo_evento") 
    private String tipoEvento;

    @Column(name = "fecha_evento") 
    private LocalDate fechaEvento;
    private String motivo;
}