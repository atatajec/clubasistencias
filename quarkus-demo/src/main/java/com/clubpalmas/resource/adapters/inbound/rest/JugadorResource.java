package com.clubpalmas.resource.adapters.inbound.rest;

import java.util.List;

import com.clubpalmas.resource.adapters.inbound.dto.ApiResponse;
import com.clubpalmas.resource.domain.model.Jugador;
import com.clubpalmas.resource.ports.inbound.JugadorUseCase;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/jugadores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JugadorResource {

    @Inject 
    JugadorUseCase jugadorUseCase;

    @POST
    public Response registrar(Jugador jugador) {
        Jugador nuevo = jugadorUseCase.registrarJugador(jugador);
        return Response.status(Response.Status.CREATED)
                .entity(ApiResponse.success(nuevo, "Jugador registrado con éxito."))
                .build();
    }

    @GET
    @Path("/buscar") // Endpoint para el autocompletado en React (Punto 9)
    public Response buscar(@QueryParam("query") String query) {
        List<Jugador> jugadores = jugadorUseCase.buscarPorNombreOApodo(query);
        return Response.ok(ApiResponse.success(jugadores, "Búsqueda completada.")).build();
    }

    @PUT
    @Path("/{id}/socio")
    public Response cambiarEstadoSocio(@PathParam("id") Long id, @QueryParam("esSocio") boolean esSocio, @QueryParam("motivo") String motivo) {
        jugadorUseCase.cambiarEstadoSocio(id, esSocio, motivo);
        return Response.ok(ApiResponse.success(null, "Estado de socio actualizado.")).build();
    }

    @GET
    @Path("/listar") // Endpoint para el autocompletado en React (Punto 9)
    public Response listarResponse() {
        List<Jugador> jugadores = jugadorUseCase.listarJugadores();
        return Response.ok(ApiResponse.success(jugadores, "Búsqueda completada.")).build();
    }
}
