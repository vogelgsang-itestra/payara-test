package com.itestra.app.entity;

import com.itestra.app.HistoryCustomizer;
import org.eclipse.persistence.annotations.Customizer;

import javax.persistence.*;

@Entity
@Table(name = "assignment")
@Customizer(HistoryCustomizer.class)
public class AssignmentBE extends AbstractBE {

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "child_entity_id")
    private ChildEntityBE childEntity;

    @ManyToOne
    @JoinColumn(name = "entity_id")
    private EntityBE entity;

    public AssignmentBE() {
    }

    public AssignmentBE(ChildEntityBE childEntity) {
        this.childEntity = childEntity;
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
