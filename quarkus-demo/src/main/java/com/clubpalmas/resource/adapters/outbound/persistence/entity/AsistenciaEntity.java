package com.clubpalmas.resource.adapters.outbound.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "asistencias")
@Getter 
@Setter
public class AsistenciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jugador_id", nullable = false)
    private Long jugadorId;

    @Column(name = "config_cuota_id", nullable = false)
    private Long configCuotaId;

    @Column(name = "fecha_asistencia", nullable = false)
    private LocalDate fechaAsistencia;

    @Column(name = "metodo_pago", nullable = false)
    private String metodoPago;

    @Column(name = "monto_pagado", nullable = false)
    private Double montoPagado;
}
