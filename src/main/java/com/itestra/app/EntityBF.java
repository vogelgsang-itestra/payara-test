package com.itestra.app;

import com.itestra.app.entity.EntityBE;
import org.eclipse.persistence.history.AsOfClause;
import org.eclipse.persistence.queries.ReadObjectQuery;
import org.eclipse.persistence.sessions.Session;
import org.eclipse.persistence.sessions.factories.SessionManager;
import org.eclipse.persistence.sessions.server.ServerSession;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import static java.time.ZoneId.systemDefault;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
@NamedQuery(name = "entity.getHistory", query = "select e from EntityBE e where e.id = :id")
public class EntityBF {
    @PersistenceContext
    private EntityManager entityManager;

    public Optional<EntityBE> getById(long id) {
        try {
            final EntityBE entity = entityManager
                    .createQuery("select e from EntityBE e where e.id = :id", EntityBE.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(entity);
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    public void insert(EntityBE entity) {
        entityManager.persist(entity);
    }

    public EntityBE update(EntityBE entity) {
        return entityManager.merge(entity);
    }

    Optional<EntityBE> getHistoryById(long id, LocalDateTime timepoint) {
        final AsOfClause clause = new AsOfClause(Date.from(timepoint.atZone(systemDefault()).toInstant()));
        final Session session = ((ServerSession) (SessionManager.getManager()
                .getSession("/file:/C:/no_backup/boa/payara/glassfish/domains/payara_test/applications/payara_test-1.0-SNAPSHOT/WEB-INF/classes/_payara_test")))
                .acquireClientSession()
                .acquireHistoricalSession(clause);

        ReadObjectQuery query = new ReadObjectQuery(EntityBE.class);
        query.setSelectionId(id);

        final EntityBE result = (EntityBE) session.executeQuery(query);

        return Optional.ofNullable(result);
    }
}
