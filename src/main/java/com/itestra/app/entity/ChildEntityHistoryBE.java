package com.itestra.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "child_entity_history")
public class ChildEntityHistoryBE extends AbstractHistoryBE {
    @Column(name = "value")
    private String value;

    public String getValue() {
        return value;
    }
}
