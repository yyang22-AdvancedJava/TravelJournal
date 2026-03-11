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
 * 모든 DAO의 공통 기능을 담은 제네릭 클래스입니다.
 */
public class GenericDao<T> {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private Class<T> type;
    private SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * @param type 엔티티 클래스 타입 (예: User.class, Journal.class)
     */
    public GenericDao(Class<T> type) {
        this.type = type;
    }

    /** ID로 엔티티 조회 */
    public T getById(int id) {
        Session session = sessionFactory.openSession();
        T entity = session.get(type, id);
        session.close();
        return entity;
    }

    /** 엔티티 업데이트 (merge) */
    public void update(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entity);
        transaction.commit();
        session.close();
    }

    /** 엔티티 삽입 (persist) */
    public int insert(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        session.close();
        return 1; // 성공 시 임의의 값 반환
    }

    /** 엔티티 삭제 (delete) */
    public void delete(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    /** 전체 목록 조회 */
    public List<T> getAll() {
        Session session = sessionFactory.openSession();
        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        query.from(type);
        List<T> list = session.createSelectionQuery(query).getResultList();
        session.close();
        return list;
    }

    /** 강사님이 지적하신 완전 일치 검색 (Equal) */
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

    /** 부분 일치 검색 (Like) */
    public List<T> getByPropertyLike(String propertyName, String value) {
        Session session = sessionFactory.openSession();
        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        Expression<String> propertyPath = root.get(propertyName);
        query.where(builder.like(propertyPath, "%" + value + "%"));
        List<T> list = session.createQuery(query).getResultList();
        session.close();
        return list;
    }
}