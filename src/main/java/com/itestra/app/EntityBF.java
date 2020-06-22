package com.itestra.app;

import com.itestra.app.entity.EntityBE;
import org.eclipse.persistence.history.AsOfClause;
import org.eclipse.persistence.jpa.JpaHelper;
import org.eclipse.persistence.queries.ReadObjectQuery;
import org.eclipse.persistence.sessions.Session;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.time.ZoneId.systemDefault;
import static java.util.stream.Collectors.toList;

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

    private Session getHistoricalSession(LocalDateTime timestamp) {
        // https://stackoverflow.com/questions/25032275/eclipselink-history-of-related-objects
        final AsOfClause asOfClause = new AsOfClause(Date.from(timestamp.atZone(systemDefault()).toInstant()));
        return JpaHelper.getServerSession(entityManager.getEntityManagerFactory())
                .acquireClientSession()
                .acquireHistoricalSession(asOfClause);
    }

    public void insert(EntityBE entity) {
        entityManager.persist(entity);
    }

    public EntityBE update(EntityBE entity) {
        return entityManager.merge(entity);
    }

    public Optional<EntityBE> getHistoryById(long id, LocalDateTime timestamp) {
        final Session session = getHistoricalSession(timestamp);

        ReadObjectQuery query = new ReadObjectQuery(EntityBE.class);
        query.setSelectionId(id);

        final EntityBE result = (EntityBE) session.executeQuery(query);

        return Optional.ofNullable(result);
    }

    public List<LocalDateTime> getVersions(long id) {
        final List<Timestamp> result = entityManager
                .createNativeQuery("select distinct(valid_from) from entity_history order by valid_from")
                .getResultList();
        return result.stream().map(Timestamp::toLocalDateTime).collect(toList());
    }
}
