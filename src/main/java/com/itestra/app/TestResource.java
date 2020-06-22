package com.itestra.app;

import com.itestra.app.entity.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

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
    private GrandChildEntityBF grandChildEntityBF;

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
    public void init() {
        LocalDateTime start = LocalDateTime.of(2020, Month.JUNE, 18, 10, 0);

        // first init entities
        customRequestContext.setCurrentTimestamp(start);

        EntityBE parent = new EntityBE("parent");
        entityBF.insert(parent);
        ChildEntityBE firstChild = new ChildEntityBE("first child");
        childEntityBF.insert(firstChild);
        ChildEntityBE secondChild = new ChildEntityBE("second child");
        childEntityBF.insert(secondChild);
        GrandChildEntityBE firstGrandChild = new GrandChildEntityBE("first grand child");
        grandChildEntityBF.insert(firstGrandChild);

        // add add assignment
        customRequestContext.setCurrentTimestamp(start.plusDays(1));

        final ChildAssignmentBE childAssignment = new ChildAssignmentBE(firstChild);
        childAssignment.setGrandChildAssignments(asList(new GrandChildAssignmentBE(firstGrandChild)));
        parent.addAssignment(childAssignment);
        parent = entityBF.update(parent);

        // remove assignment add other
        customRequestContext.setCurrentTimestamp(start.plusDays(2));

        parent.setAssignments(asList(new ChildAssignmentBE(secondChild)));
        parent = entityBF.update(parent);

        // test history

        Optional<EntityBE> history = entityBF.getHistoryById(1, start);
        assertThatEqual(history.get().getAssignments().size(), 0);

        history = entityBF.getHistoryById(1, start.plusDays(1));
        assertThatEqual(history.get().getAssignments().size(), 1);
        assertThatEqual(history.get().getAssignments().get(0).getChildEntity().getValue(), "first child");

        history = entityBF.getHistoryById(1, start.plusDays(2));
        assertThatEqual(history.get().getAssignments().size(), 1);
        assertThatEqual(history.get().getAssignments().get(0).getChildEntity().getValue(), "second child");
    }

    private <T> void assertThatEqual(T actual, T expected) {
        if (!actual.equals(expected)) {
            throw new AssertionError(String.format("expected %s but got %s", expected, actual));
        }
    }
}
