package com.itestra.app.entity;

import javax.persistence.*;

@Entity
@Table(name = "assignment_history")
public class AssignmentHistoryBE extends AbstractHistoryBE {

    @ManyToOne
    @JoinColumn(name = "entity_id")
    private EntityBE entity;

    @Column(name = "value")
    private String value;

    public String getValue() {
        return value;
    }
}
