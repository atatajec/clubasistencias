package com.clubpalmas.resource.adapters.inbound.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ApiResponse<T> {
    
    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    // CORRECCIÓN: Ajustamos la sintaxis del inicializador del Builder genérico
    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder() // Colocando el <T> antes de builder()
                .success(true)
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> ApiResponse<T> error(String message) {
        return ApiResponse.<T>builder() // Colocando el <T> antes de builder()
                .success(false)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
