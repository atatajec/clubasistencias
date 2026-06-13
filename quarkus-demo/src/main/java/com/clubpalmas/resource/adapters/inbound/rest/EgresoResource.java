package com.clubpalmas.resource.adapters.inbound.rest;

import com.clubpalmas.resource.adapters.inbound.dto.ApiResponse;
import com.clubpalmas.resource.domain.model.Egreso;
import com.clubpalmas.resource.ports.inbound.EgresoUseCase;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/egresos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EgresoResource {

    @Inject
    EgresoUseCase egresoUseCase;

    @POST
    @RolesAllowed("ADMIN")
    public Response registrar(Egreso egreso) {
        Egreso nuevo = egresoUseCase.registrarEgreso(egreso);
        return Response.status(Response.Status.CREATED)
                .entity(ApiResponse.success(nuevo, "Egreso registrado con éxito."))
                .build();
    }


}
