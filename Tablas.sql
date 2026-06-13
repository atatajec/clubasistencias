CREATE DATABASE IF NOT EXISTS db_clublaspalmas
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE db_clublaspalmas;

-- 1. CONFIGURACIÓN DE CUOTAS (Soporta el punto 13: montos y distribución variables a futuro)
CREATE TABLE configuracion_cuotas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    monto_total DECIMAL(10,2) NOT NULL,
    monto_socio DECIMAL(10,2) NOT NULL,
    monto_club DECIMAL(10,2) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NULL, -- NULL significa que es la configuración vigente actual
    activo BOOLEAN DEFAULT TRUE,
    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_distribucion CHECK (monto_total = (monto_socio + monto_club))
) ENGINE=INNODB;

-- Insertar la configuración inicial de 7 soles (6 socio, 1 club)
INSERT INTO configuracion_cuotas (monto_total, monto_socio, monto_club, fecha_inicio, fecha_fin, activo)
VALUES (7.00, 6.00, 1.00, '2026-01-01', NULL, TRUE);


-- 2. JUGADORES (Datos principales y estado actual)
CREATE TABLE jugadores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apodo VARCHAR(50) NULL,
    fecha_nacimiento DATE NOT NULL,
    es_socio BOOLEAN DEFAULT FALSE NOT NULL,
    fecha_ingreso_socio DATE NULL, -- Solo si es_socio es TRUE
    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    actualizado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=INNODB;

-- Índices para optimizar la búsqueda autocompletada por nombre o apodo (Punto 9)
CREATE INDEX idx_jugador_busqueda ON jugadores(nombre, apodo);


-- 3. HISTORIAL DE SOCIOS (Soporta el punto 11: registro si deja de ser socio o regresa)
CREATE TABLE historial_socios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    jugador_id INT NOT NULL,
    tipo_evento ENUM('ALTA', 'BAJA') NOT NULL,
    fecha_evento DATE NOT NULL,
    motivo VARCHAR(255) NULL,
    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (jugador_id) REFERENCES jugadores(id) ON DELETE CASCADE
) ENGINE=INNODB;


-- 4. ASISTENCIAS Y PAGOS (Soporta puntos 3, 4, 5 y 10)
CREATE TABLE asistencias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    jugador_id INT NOT NULL,
    config_cuota_id INT NOT NULL, -- Vincula el pago exacto y la distribución de esa fecha concreta
    fecha_asistencia DATE NOT NULL, -- Generalmente sábados
    metodo_pago ENUM('EFECTIVO', 'YAPE', 'TRANSFERENCIA') NOT NULL,
    monto_pagado DECIMAL(10,2) NOT NULL, -- Se guarda para asegurar auditoría histórica
    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- Evita que se registre dos veces al mismo jugador la misma fecha
    UNIQUE KEY uq_jugador_fecha (jugador_id, fecha_asistencia),
    FOREIGN KEY (jugador_id) REFERENCES jugadores(id),
    FOREIGN KEY (config_cuota_id) REFERENCES configuracion_cuotas(id)
) ENGINE=INNODB;

CREATE INDEX idx_fecha_asistencia ON asistencias(fecha_asistencia);


-- 5. EGRESOS (Soporta el punto 6: salida de dinero de fondos específicos)
CREATE TABLE egresos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha_egreso DATE NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    origen_fondo ENUM('SOCIO', 'CLUB') NOT NULL, -- De dónde sale el dinero (6 soles o 1 sol)
    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=INNODB;

-- 6. ADMINS (Usuarios)
CREATE TABLE admins (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);