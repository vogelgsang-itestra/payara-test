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
        entityManager.createNativeQuery("delete from grand_child_assignment_history");
        entityManager.createNativeQuery("delete from child_assignment_history");
        entityManager.createNativeQuery("delete from grand_child_entity_history");
        entityManager.createNativeQuery("delete from child_entity_history");
        entityManager.createNativeQuery("delete from entity_history");

        entityManager.createQuery("delete from GrandChildAssignmentBE").executeUpdate();
        entityManager.createQuery("delete from ChildAssignmentBE").executeUpdate();
        entityManager.createQuery("delete from GrandChildEntityBE ").executeUpdate();
        entityManager.createQuery("delete from ChildEntityBE ").executeUpdate();
        entityManager.createQuery("delete from EntityBE ").executeUpdate();
    }
}
