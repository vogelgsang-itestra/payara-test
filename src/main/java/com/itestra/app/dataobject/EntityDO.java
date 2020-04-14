package com.itestra.app.dataobject;

import com.itestra.app.entity.EntityBE;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

public class EntityDO {
    private final Long id;
    private final String value;
    private final List<AssignmentDO> assignments = new ArrayList<>();

    public EntityDO(Long id, String value, List<AssignmentDO> assignments) {
        this.id = id;
        this.value = value;
        this.assignments.addAll(assignments);
    }

    public EntityDO(EntityBE entity) {
        this(
                entity.getId(),
                entity.getValue(),
                entity.getAssignments().stream()
                        .map(AssignmentDO::new)
                        .collect(toList())
        );
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public List<AssignmentDO> getAssignments() {
        return unmodifiableList(assignments);
    }
}
