package com.itestra.app;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class TestBF {
    @PersistenceContext
    private EntityManager entityManager;

    public void resetDb() {
        entityManager.createQuery("delete from AssignmentHistoryBE").executeUpdate();
        entityManager.createQuery("delete from ChildEntityHistoryBE ").executeUpdate();
        entityManager.createQuery("delete from EntityHistoryBE ").executeUpdate();

        entityManager.createQuery("delete from AssignmentBE").executeUpdate();
        entityManager.createQuery("delete from ChildEntityBE ").executeUpdate();
        entityManager.createQuery("delete from EntityBE ").executeUpdate();
    }
}
