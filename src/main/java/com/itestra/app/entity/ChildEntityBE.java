package com.itestra.app.entity;

import com.itestra.app.HistoryCustomizer;
import org.eclipse.persistence.annotations.Customizer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "child_entity")
@Customizer(HistoryCustomizer.class)
public class ChildEntityBE extends AbstractBE {

    @Column(name = "value")
    private String value;

    public ChildEntityBE() {
    }

    public ChildEntityBE(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
