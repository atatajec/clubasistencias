package com.clubpalmas.resource.domain.exception;

public class JugadorNoEncontradoException extends RuntimeException {
    
    public JugadorNoEncontradoException(String mensaje) {
        super(mensaje);
    }

    public JugadorNoEncontradoException(Long id) {
        super("No se encontró al jugador con el ID: " + id);
    }
}
