package com.itestra.app.entity;

import javax.persistence.*;

@Entity
@Table(name = "child_assignment_history")
public class ChildAssignmentHistoryBE extends AbstractHistoryBE {

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "child_entity_id")
    private ChildEntityBE childEntity;

    @ManyToOne
    @JoinColumn(name = "entity_id")
    private EntityBE entity;

    public EntityBE getEntity() {
        return entity;
    }

    public ChildEntityBE getChildEntity() {
        return childEntity;
    }
}
