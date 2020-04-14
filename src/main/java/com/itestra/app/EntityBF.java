package com.itestra.app;

import com.itestra.app.entity.EntityBE;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
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
}
