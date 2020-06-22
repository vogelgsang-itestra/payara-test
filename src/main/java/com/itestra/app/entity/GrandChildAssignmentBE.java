package com.itestra.app.entity;

import com.itestra.app.HistoryCustomizer;
import org.eclipse.persistence.annotations.Customizer;

import javax.persistence.*;

@Entity
@Table(name = "grand_child_assignment")
@Customizer(HistoryCustomizer.class)
public class GrandChildAssignmentBE extends AbstractBE {

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "grand_child_entity_id")
    private GrandChildEntityBE grandChildEntity;

    @ManyToOne
    @JoinColumn(name = "child_assignment_id")
    private ChildAssignmentBE childAssignment;

    public GrandChildAssignmentBE() {
    }

    public GrandChildAssignmentBE(GrandChildEntityBE grandChildEntity) {
        this.grandChildEntity = grandChildEntity;
    }

    public GrandChildEntityBE getGrandChildEntity() {
        return grandChildEntity;
    }

    public void setGrandChildEntity(GrandChildEntityBE childEntity) {
        this.grandChildEntity = childEntity;
    }

    public ChildAssignmentBE getChildAssignment() {
        return childAssignment;
    }

    public void setChildAssignment(ChildAssignmentBE childAssignment) {
        this.childAssignment = childAssignment;
        // for this test it is ok to establish the connection only in ChildAssignmentBE
    }
}
