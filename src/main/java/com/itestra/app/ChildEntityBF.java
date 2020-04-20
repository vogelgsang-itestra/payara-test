package com.itestra.app;

import com.itestra.app.entity.ChildEntityBE;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class ChildEntityBF {
    @PersistenceContext
    private EntityManager entityManager;

    public Optional<ChildEntityBE> getById(long id) {
        try {
            final ChildEntityBE entity = entityManager
                    .createQuery("select e from ChildEntityBE e where e.id = :id", ChildEntityBE.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(entity);
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    public void insert(ChildEntityBE entity) {
        entityManager.persist(entity);
    }

    public ChildEntityBE update(ChildEntityBE entity) {
        return entityManager.merge(entity);
    }
}
