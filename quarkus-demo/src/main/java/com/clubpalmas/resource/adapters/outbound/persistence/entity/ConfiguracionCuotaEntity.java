package com.clubpalmas.resource.adapters.outbound.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "configuracion_cuotas")
@Getter 
@Setter
public class ConfiguracionCuotaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "monto_total", nullable = false)
    private Double montoTotal;

    @Column(name = "monto_socio", nullable = false)
    private Double montoSocio;

    @Column(name = "monto_club", nullable = false)
    private Double montoClub;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(nullable = false)
    private boolean activo;
}
