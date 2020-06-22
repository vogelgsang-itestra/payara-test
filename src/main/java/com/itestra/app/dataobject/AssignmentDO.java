package com.itestra.app.dataobject;

import com.itestra.app.entity.ChildAssignmentBE;

public class AssignmentDO {
    private final Long id;
    private ChildEntityDO childEntity;

    public AssignmentDO(Long id, ChildEntityDO childEntity) {
        this.id = id;
        this.childEntity = childEntity;
    }

    public AssignmentDO(ChildAssignmentBE assignment) {
        this(assignment.getId(), new ChildEntityDO(assignment.getChildEntity()));
    }

    public Long getId() {
        return id;
    }

    public ChildEntityDO getChildEntity() {
        return childEntity;
    }
}
