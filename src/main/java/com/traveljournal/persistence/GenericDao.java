package com.traveljournal.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public class GenericDao<T> {
    private Class<T> type;
    private final Logger logger = LogManager.getLogger(this.getClass());
    private SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    // 생성자에서 다룰 엔티티 타입을 받습니다 (예: User.class)
    public GenericDao(Class<T> type) {
        this.type = type;
    }

    /**
     * ID로 조회
     */
    public T getById(int id) {
        Session session = sessionFactory.openSession();
        T entity = session.get(type, id);
        session.close();
        return entity;
    }

    /**
     * 삽입 (Insert)
     */
    public int insert(T entity) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        // 실제 ID값을 리턴하기 위해 리플렉션이나 다른 방법을 쓸 수도 있지만,
        // 하이버네이트가 자동으로 entity 객체에 ID를 채워줍니다.
        session.close();
        return id; // 필요하다면 엔티티 객체에서 getId()로 꺼내 쓰면 됩니다.
    }

    /**
     * 수정 (Update)
     */
    public void update(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entity);
        transaction.commit();
        session.close();
    }

    /**
     * 삭제 (Delete)
     */
    /*
    public void delete(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(session.contains(entity) ? entity : session.merge(entity));
        transaction.commit();
        session.close();
    }
    */
    public void delete(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            // 1. 객체에서 ID를 추출 (하이버네이트 내부 기능을 쓰거나 getIdentifier 사용)
            Object id = sessionFactory.getPersistenceUnitUtil().getIdentifier(entity);

            // 2. 현재 세션에서 깨끗한 상태의 객체를 다시 가져옵니다.
            T entityToDelete = session.get(type, (Serializable) id);

            if (entityToDelete != null) {
                // 3. 현재 세션에 로드된 객체를 삭제 (이러면 연관 관계 충돌이 최소화됩니다)
                session.remove(entityToDelete);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    /**
     * 전체 조회
     */
    public List<T> getAll() {
        Session session = sessionFactory.openSession();
        List<T> list = session.createSelectionQuery("from " + type.getSimpleName(), type).getResultList();
        session.close();
        return list;
    }

    /**
     * 속성 일치 검색 (getByPropertyEqual)
     */
    public List<T> getByPropertyEqual(String propertyName, Object value) {
        Session session = sessionFactory.openSession();
        String queryString = "from " + type.getSimpleName() + " where " + propertyName + " = :value";
        List<T> list = session.createSelectionQuery(queryString, type)
                .setParameter("value", value)
                .getResultList();
        session.close();
        return list;
    }

    /**
     * 특정 속성 포함 검색 (Like) - 위와 동일한 구조
     */
    public List<T> getByPropertyLike(String propertyName, String value) {
        Session session = sessionFactory.openSession();
        // HQL: from Entity where property like :value
        String queryString = "from " + type.getSimpleName() + " where " + propertyName + " like :value";
        List<T> list = session.createSelectionQuery(queryString, type)
                .setParameter("value", "%" + value + "%") // 여기에 와일드카드(%) 추가
                .getResultList();
        session.close();
        return list;
    }
}