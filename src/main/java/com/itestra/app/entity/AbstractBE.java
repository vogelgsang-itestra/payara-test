package com.itestra.app.entity;

import com.itestra.app.CustomRequestContext;

import javax.enterprise.inject.spi.CDI;
import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AbstractBE {
    @Id
    @GeneratedValue(generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "SEQ", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;

    @PrePersist
    public void prePersist() {
        final CustomRequestContext context = CDI.current().select(CustomRequestContext.class).get();
        setCreated(context.getCurrentTimestamp());
        setModified(context.getCurrentTimestamp());
    }

    @PreUpdate
    public void preUpdate() {
        final CustomRequestContext context = CDI.current().select(CustomRequestContext.class).get();
        setModified(context.getCurrentTimestamp());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
}
