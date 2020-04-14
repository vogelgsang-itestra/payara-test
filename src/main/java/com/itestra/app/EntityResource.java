package com.itestra.app;

import com.itestra.app.dataobject.EntityDO;
import com.itestra.app.entity.AssignmentBE;
import com.itestra.app.entity.EntityBE;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Path("/entity")
public class EntityResource {
    @Inject
    private EntityBF entityBF;

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") final Long id) {
        return entityBF.getById(id)
                .map(entity -> Response.ok(new EntityDO(entity)))
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @GET
    @Path("/test")
    public Response test() {
        final EntityBE entity = new EntityBE("Entity");
        entity.addAssignment(new AssignmentBE("Assignment"));
        entityBF.insert(entity);

        return Response.ok(new EntityDO(entity)).build();
    }
}
