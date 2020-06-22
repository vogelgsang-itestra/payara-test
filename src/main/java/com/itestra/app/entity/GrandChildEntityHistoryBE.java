package com.itestra.app.entity;

import com.itestra.app.HistoryCustomizer;
import org.eclipse.persistence.annotations.Customizer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "grand_child_entity_history")
@Customizer(HistoryCustomizer.class)
public class GrandChildEntityHistoryBE extends AbstractHistoryBE {
    @Column(name = "value")
    private String value;

    public String getValue() {
        return value;
    }
}
