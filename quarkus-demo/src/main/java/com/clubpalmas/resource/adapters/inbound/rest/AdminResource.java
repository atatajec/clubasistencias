package com.clubpalmas.resource.adapters.inbound.rest;

import java.util.Map;

import com.clubpalmas.resource.adapters.inbound.dto.ApiResponse;
import com.clubpalmas.resource.adapters.inbound.dto.LoginRequest;
import com.clubpalmas.resource.ports.inbound.AdminUseCase;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminResource {
    @Inject
    AdminUseCase adminUseCase;

    @POST
    @Path("/login")
    public Response autenticar(LoginRequest request) {
        try {
            String token = adminUseCase.loginYGenerarToken(request.getEmail(), request.getPassword());
            // Retornamos el token dentro de un mapa en la propiedad 'data'
            return Response.ok(ApiResponse.success(Map.of("token", token), "Autenticación exitosa.")).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(ApiResponse.error(e.getMessage()))
                    .build();
        }
    }
}
