package org.hibernate.bugs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.entity.TestEmbeddable;
import org.hibernate.entity.TestEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
class JPAUnitTestCase {

    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("templatePU");
    }

    @AfterEach
    void destroy() {
        entityManagerFactory.close();
    }

    // Entities are auto-discovered, so just add them anywhere on class-path
    // Add your tests, using standard JUnit.
    @Test
    void hhh_code_setSameEmbeddable() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        try {
            TestEntity testEntity = new TestEntity();
            TestEmbeddable testEmbeddable = new TestEmbeddable();
            testEmbeddable.setVal("abc");
            testEntity.setEmbeddable(testEmbeddable);

            entityManager.persist(testEntity);
            entityManager.flush();

            testEntity.setEmbeddable(testEmbeddable);
            Session session = entityManager.unwrap(Session.class);
            assertFalse(session.isDirty(),
                    "set the same embeddable instance, should not be marked as dirty");
        } finally {
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }

    @Test
    void hhh_code_setEquivalentEmbeddable() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        try {
            TestEntity testEntity = new TestEntity();
            TestEmbeddable testEmbeddable = new TestEmbeddable();
            String val = "abc";
            testEmbeddable.setVal(val);
            testEntity.setEmbeddable(testEmbeddable);

            entityManager.persist(testEntity);
            entityManager.flush();

            TestEmbeddable equivalentEmbeddable = new TestEmbeddable();
            equivalentEmbeddable.setVal(val);
            testEntity.setEmbeddable(equivalentEmbeddable);
            Session session = entityManager.unwrap(Session.class);
            assertFalse(session.isDirty(),
                    "set an equivalent embeddable, should not be marked as dirty");
        } finally {
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }

    @Test
    void hhh_code_setEquivalentValueOnEmbeddable() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        try {
            TestEntity testEntity = new TestEntity();
            TestEmbeddable testEmbeddable = new TestEmbeddable();
            String val = "abc";
            testEmbeddable.setVal(val);
            testEntity.setEmbeddable(testEmbeddable);

            entityManager.persist(testEntity);
            entityManager.flush();

            testEmbeddable.setVal(val);
            Session session = entityManager.unwrap(Session.class);
            assertFalse(session.isDirty(),
                    "set same value on embeddable field, should not be marked as dirty");
        } finally {
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }
}