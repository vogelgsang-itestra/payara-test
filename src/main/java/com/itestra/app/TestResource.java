package com.itestra.app;

import com.itestra.app.entity.AssignmentBE;
import com.itestra.app.entity.ChildEntityBE;
import com.itestra.app.entity.EntityBE;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.time.Month;

import static java.util.Arrays.asList;

@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Path("/test")
public class TestResource {
    @Inject
    private EntityBF entityBF;

    @Inject
    private ChildEntityBF childEntityBF;

    @Inject
    private TestBF testBF;
    @Inject
    private CustomRequestContext customRequestContext;

    @POST
    @Path("/reset")
    public void reset() {
        testBF.resetDb();
    }

    @POST
    @Path("/init")
    public void init()  {
        customRequestContext.setCurrentTimestamp(LocalDateTime.of(2020, Month.JUNE, 18, 10, 0));

        EntityBE parent = new EntityBE("parent");
        entityBF.insert(parent);
        ChildEntityBE firstChild = new ChildEntityBE("first child");
        childEntityBF.insert(firstChild);
        ChildEntityBE secondChild = new ChildEntityBE("second child");
        childEntityBF.insert(secondChild);

        customRequestContext.setCurrentTimestamp(LocalDateTime.of(2020, Month.JUNE, 19, 10, 0));

        parent.addAssignment(new AssignmentBE(firstChild));
        parent = entityBF.update(parent);

        customRequestContext.setCurrentTimestamp(LocalDateTime.of(2020, Month.JUNE, 20, 10, 0));

        parent.setAssignments(asList(new AssignmentBE(secondChild)));
        parent = entityBF.update(parent);
    }
}
