package com.itestra.app.entity;

import com.itestra.app.HistoryCustomizer;
import org.eclipse.persistence.annotations.Customizer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

@Entity
@Table(name = "child_assignment")
@Customizer(HistoryCustomizer.class)
public class ChildAssignmentBE extends AbstractBE {

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "child_entity_id")
    private ChildEntityBE childEntity;

    @ManyToOne
    @JoinColumn(name = "entity_id")
    private EntityBE entity;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "childAssignment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GrandChildAssignmentBE> grandChildAssignments = new ArrayList<>();

    public ChildAssignmentBE() {
    }

    public ChildAssignmentBE(ChildEntityBE childEntity) {
        this.childEntity = childEntity;
    }

    public List<GrandChildAssignmentBE> getGrandChildAssignments() {
        return unmodifiableList(grandChildAssignments);
    }

    public void setGrandChildAssignments(List<GrandChildAssignmentBE> grandChildAssignments) {
        this.grandChildAssignments.clear();
        this.grandChildAssignments.addAll(grandChildAssignments);
        this.grandChildAssignments.forEach(a -> a.setChildAssignment(this));
    }

    public ChildEntityBE getChildEntity() {
        return childEntity;
    }

    public void setChildEntity(ChildEntityBE childEntity) {
        this.childEntity = childEntity;
    }

    public EntityBE getEntity() {
        return entity;
    }

    public void setEntity(EntityBE entity) {
        this.entity = entity;
        // for this test it is ok to establish the connection only in EntityBE
    }
}
