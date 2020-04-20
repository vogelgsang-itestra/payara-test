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
}
