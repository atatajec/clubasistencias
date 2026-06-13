package com.clubpalmas.resource.adapters.inbound.rest;

import java.time.LocalDate;
import java.util.List;

import com.clubpalmas.resource.adapters.inbound.dto.ApiResponse;
import com.clubpalmas.resource.domain.model.Asistencia;
import com.clubpalmas.resource.ports.inbound.AsistenciaUseCase;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/asistencias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AsistenciaResource {
    @Inject 
    AsistenciaUseCase asistenciaUseCase;

    @POST
    @Path("/marcar")
    public Response marcar(@QueryParam("jugadorId") Long jugadorId, @QueryParam("metodoPago") String metodoPago) {
        try {
            Asistencia res = asistenciaUseCase.registrarAsistencia(jugadorId, metodoPago);
            return Response.status(Response.Status.CREATED).entity(ApiResponse.success(res, "Asistencia grabada.")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ApiResponse.error(e.getMessage())).build();
        }
    }

    @GET
    public Response listarPorFecha(@QueryParam("fecha") String fechaStr) {
        LocalDate fecha = fechaStr != null ? LocalDate.parse(fechaStr) : LocalDate.now();
        List<Asistencia> lista = asistenciaUseCase.obtenerAsistenciasPorFecha(fecha);
        return Response.ok(ApiResponse.success(lista, "Asistencias del día encontradas.")).build();
    }

    @GET
    @Path("/buscar")
    public Response buscar(@QueryParam("nombre") String nombre) {
        List<Asistencia> lista = asistenciaUseCase.obtenerAsistenciasPorJugador(nombre);
        return Response.ok(ApiResponse.success(lista, "Asistencias del jugador encontradas.")).build();
    }
}
