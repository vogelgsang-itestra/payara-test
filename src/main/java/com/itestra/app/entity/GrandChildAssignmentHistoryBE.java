package com.itestra.app.entity;

import javax.persistence.*;

@Entity
@Table(name = "grand_child_assignment_history")
public class GrandChildAssignmentHistoryBE extends AbstractHistoryBE {

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "grand_child_entity_id")
    private GrandChildEntityBE grandChildEntity;

    @ManyToOne
    @JoinColumn(name = "child_assignment_id")
    private ChildAssignmentHistoryBE childAssignment;

    public ChildAssignmentHistoryBE getChildAssignment() {
        return childAssignment;
    }

    public GrandChildEntityBE getGrandChildEntity() {
        return grandChildEntity;
    }
}
