package com.itestra.app;

import com.itestra.app.entity.EntityBE;
import org.eclipse.persistence.history.AsOfClause;
import org.eclipse.persistence.jpa.JpaHelper;
import org.eclipse.persistence.queries.ReadAllQuery;
import org.eclipse.persistence.queries.ReadObjectQuery;
import org.eclipse.persistence.sessions.Session;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.time.ZoneId.systemDefault;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class EntityBF {
    @PersistenceContext
    private EntityManager entityManager;

    public Optional<EntityBE> getById(long id) {
        try {
            final EntityBE entity = getByIdQuery(id).getSingleResult();
            return Optional.of(entity);
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    private TypedQuery<EntityBE> getByIdQuery(long id) {
        return entityManager
                .createNamedQuery("entity.getById", EntityBE.class)
                .setParameter("id", id);
    }

    public void insert(EntityBE entity) {
        entityManager.persist(entity);
    }

    public EntityBE update(EntityBE entity) {
        return entityManager.merge(entity);
    }

    public Optional<EntityBE> getHistoryById(long id, LocalDateTime timePoint) {
        // https://stackoverflow.com/questions/25032275/eclipselink-history-of-related-objects
        final AsOfClause asOfClause = new AsOfClause(Date.from(timePoint.atZone(systemDefault()).toInstant()));
        final Session session = JpaHelper.getServerSession(entityManager.getEntityManagerFactory())
                .acquireClientSession()
                .acquireHistoricalSession(asOfClause);

        ReadObjectQuery query = new ReadObjectQuery(EntityBE.class);
        query.setSelectionId(id);

        final EntityBE result = (EntityBE) session.executeQuery(query);

        return Optional.ofNullable(result);
    }
}
