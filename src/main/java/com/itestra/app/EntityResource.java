package com.itestra.app;

import com.itestra.app.dataobject.EntityDO;
import com.itestra.app.entity.ChildAssignmentBE;
import com.itestra.app.entity.ChildEntityBE;
import com.itestra.app.entity.EntityBE;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Path("/entity")
public class EntityResource {
    @Inject
    private EntityBF entityBF;

    @Inject
    private ChildEntityBF childEntityBF;

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") final Long id) {
        return entityBF.getById(id)
                .map(entity -> Response.ok(new EntityDO(entity)))
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    @Path("/create")
    public Response create(final CreateRequest createRequest) {
        final EntityBE entity = new EntityBE(createRequest.getValue());
        createRequest.getAssignments().forEach(a -> {
            final ChildEntityBE childEntity = new ChildEntityBE(a);
            childEntityBF.insert(childEntity);
            final ChildAssignmentBE assignment = new ChildAssignmentBE(childEntity);
            entity.addAssignment(assignment);
        });
        entityBF.insert(entity);

        return Response.ok(new EntityDO(entity)).build();
    }

    static class CreateRequest {
        private final List<String> assignments = new ArrayList<>();
        private String value;

        public String getValue() {
            return value;
        }

        public List<String> getAssignments() {
            return assignments;
        }
    }
}
