package com.traveljournal.persistence;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import java.util.List;

/**
 * A generic Data Access Object (DAO) that provides common persistence operations
 * for any entity type.
 *
 * @param <T> the type of the entity this DAO will manage
 * @author yyang22
 */
public class GenericDao<T> {
    private Class<T> type;
    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Instantiates a new Generic DAO.
     *
     * @param type the entity type (e.g., User.class, Journal.class)
     */
    public GenericDao(Class<T> type) {
        this.type = type;
    }

    /**
     * Retrieves an entity by its unique integer identifier.
     *
     * @param id the primary key ID
     * @return the entity found, or null if no record exists
     */
    public T getById(int id) {
        Session session = sessionFactory.openSession();
        T entity = session.get(type, id);
        session.close();
        return entity;
    }

    /**
     * Inserts a new entity record into the database.
     *
     * @param entity the entity to be persisted
     */
    public void insert(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        session.close();
    }

    /**
     * Updates an existing entity record using merge.
     *
     * @param entity the entity containing updated data
     */
    public void update(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entity);
        transaction.commit();
        session.close();
    }

    /**
     * Deletes an entity record. To prevent session conflicts, the entity is
     * merged back into the current session context before removal.
     *
     * @param entity the entity to be deleted
     */
    public void delete(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        // 세션 충돌 방지를 위해 merge 후 remove 실행
        session.remove(session.contains(entity) ? entity : session.merge(entity));
        transaction.commit();
        session.close();
    }

    /**
     * Retrieves all records of the managed entity type from the database.
     *
     * @return a list of all entities
     */
    public List<T> getAll() {
        Session session = sessionFactory.openSession();
        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        query.from(type);
        List<T> list = session.createSelectionQuery(query).getResultList();
        session.close();
        return list;
    }

    /**
     * Finds entities where a specific property exactly matches the provided value.
     *
     * @param propertyName the name of the entity attribute/column
     * @param value        the value to match
     * @return a list of matching entities
     */
    public List<T> getByPropertyEqual(String propertyName, Object value) {
        Session session = sessionFactory.openSession();
        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<T> list = session.createSelectionQuery(query).getResultList();
        session.close();
        return list;
    }

    /**
     * Finds entities where a specific string property contains the provided value.
     * This uses a partial match (e.g., WHERE property LIKE '%value%').
     *
     * @param propertyName the name of the entity attribute/column
     * @param value        the string to search for
     * @return a list of matching entities
     */
    public List<T> getByPropertyLike(String propertyName, String value) {
        Session session = sessionFactory.openSession();
        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        Expression<String> propertyPath = root.get(propertyName);
        query.where(builder.like(propertyPath, "%" + value + "%"));
        List<T> list = session.createSelectionQuery(query).getResultList();
        session.close();
        return list;
    }
}