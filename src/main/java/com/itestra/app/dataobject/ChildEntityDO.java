package com.itestra.app.dataobject;

import com.itestra.app.entity.ChildEntityBE;

public class ChildEntityDO {
    private final Long id;
    private final String value;

    public ChildEntityDO(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public ChildEntityDO(ChildEntityBE childEntity) {
        this(childEntity.getId(), childEntity.getValue());
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
