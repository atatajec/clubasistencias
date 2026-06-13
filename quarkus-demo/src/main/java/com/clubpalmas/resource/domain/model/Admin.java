package com.clubpalmas.resource.domain.model;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    private Long id;
    private String nombre;
    private String email;
    private String passwordHash;
    private boolean activo;
    private LocalDateTime createdAt;
}