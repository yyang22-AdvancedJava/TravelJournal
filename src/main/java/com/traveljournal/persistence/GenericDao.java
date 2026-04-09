package com.traveljournal.persistence;

import jakarta.persistence.criteria.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import java.util.List;

/**
 * 모든 DAO의 공통 기능을 담은 단일 GenericDao 클래스
 */
public class GenericDao<T> {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private Class<T> type;
    private SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public GenericDao(Class<T> type) {
        this.type = type;
    }

    private Session getSession() {
        return sessionFactory.openSession();
    }

    public T getById(int id) {
        try (Session session = getSession()) {
            return session.get(type, id);
        }
    }

    public int insert(T entity) {
        int id = 0;
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
            // 하이버네이트가 엔티티 객체에 ID를 자동으로 채워줍니다.
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("Insert error", e);
        }
        return id; // 필요시 객체에서 직접 id를 꺼내 쓰면 됩니다.
    }

    public void update(T entity) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("Update error", e);
        }
    }

    public void delete(T entity) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.remove(session.contains(entity) ? entity : session.merge(entity));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("Delete error", e);
        }
    }

    public List<T> getAll() {
        try (Session session = getSession()) {
            HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> query = builder.createQuery(type);
            query.from(type);
            return session.createSelectionQuery(query).getResultList();
        }
    }

    public List<T> getByPropertyEqual(String propertyName, Object value) {
        try (Session session = getSession()) {
            HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> query = builder.createQuery(type);
            Root<T> root = query.from(type);
            query.select(root).where(builder.equal(root.get(propertyName), value));
            return session.createSelectionQuery(query).getResultList();
        }
    }

    public List<T> getByPropertyLike(String propertyName, String value) {
        try (Session session = getSession()) {
            HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> query = builder.createQuery(type);
            Root<T> root = query.from(type);
            query.where(builder.like(root.get(propertyName), "%" + value + "%"));
            return session.createSelectionQuery(query).getResultList();
        }
    }
}