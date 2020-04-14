package com.itestra.app.entity;

import com.itestra.app.HistoryCustomizer;
import org.eclipse.persistence.annotations.Customizer;

import javax.persistence.*;

@Entity
@Table(name = "assignment")
@Customizer(HistoryCustomizer.class)
public class AssignmentBE extends AbstractBE {

    @ManyToOne
    @JoinColumn(name = "entity_id")
    private EntityBE entity;

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

    public EntityBE getEntity() {
        return entity;
    }

    public void setEntity(EntityBE entity) {
        this.entity = entity;
        // for this testit is ok to establish the connection only in EntityBE
    }
}
