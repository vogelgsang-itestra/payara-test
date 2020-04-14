package com.itestra.app.dataobject;

import com.itestra.app.entity.AssignmentBE;

public class AssignmentDO {
    private final Long id;
    private final String value;

    public AssignmentDO(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public AssignmentDO(AssignmentBE assignment) {
        this(assignment.getId(), assignment.getValue());
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
