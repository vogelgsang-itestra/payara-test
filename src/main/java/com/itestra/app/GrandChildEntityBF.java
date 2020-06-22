package com.itestra.app;

import com.itestra.app.entity.GrandChildEntityBE;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class GrandChildEntityBF {
    @PersistenceContext
    private EntityManager entityManager;

    public Optional<GrandChildEntityBE> getById(long id) {
        try {
            final GrandChildEntityBE entity = entityManager
                    .createQuery("select e from GrandChildEntityBE e where e.id = :id", GrandChildEntityBE.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(entity);
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    public void insert(GrandChildEntityBE entity) {
        entityManager.persist(entity);
    }

    public GrandChildEntityBE update(GrandChildEntityBE entity) {
        return entityManager.merge(entity);
    }
}
