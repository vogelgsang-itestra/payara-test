package com.itestra.app.entity;

import com.itestra.app.HistoryCustomizer;
import org.eclipse.persistence.annotations.Customizer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

@Entity
@Table(name = "entity")
@Customizer(HistoryCustomizer.class)
public class EntityBE extends AbstractBE {

    @Column(name = "value")
    private String value;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "entity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AssignmentBE> assignments = new ArrayList<>();

    public EntityBE() {
    }

    public EntityBE(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<AssignmentBE> getAssignments() {
        return unmodifiableList(assignments);
    }

    public void setAssignments(List<AssignmentBE> assignments) {
        this.assignments.clear();
        this.assignments.addAll(assignments);
        assignments.forEach(a -> a.setEntity(this));
    }

    public void addAssignment(AssignmentBE assignment) {
        this.assignments.add(assignment);
        assignment.setEntity(this);
    }
}
