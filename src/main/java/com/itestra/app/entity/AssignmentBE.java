package com.itestra.app.entity;

import com.itestra.app.HistoryCustomizer;
import org.eclipse.persistence.annotations.Customizer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "assignment")
@Customizer(HistoryCustomizer.class)
public class AssignmentBE extends AbstractBE {

    @Column(name = "value")
    private String value;

    public AssignmentBE() {
    }

    public AssignmentBE(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
