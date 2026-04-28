package com.traveljournal.persistence;

import com.traveljournal.entity.Journal;
import com.traveljournal.entity.User;
import com.traveljournal.persistence.SessionFactoryProvider;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Get user by id
     */
    public User getById(int id) {
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    /**
     * update user
     * @param user User to be updated
     */
    public void update(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(user);
        transaction.commit();
        session.close();
    }

    /**
     * insert a new user
     * @param user User to be inserted
     */
    public int insert(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
        int id = user.getId();
        session.close();
        return id;
    }

    /**
     * Delete a user
     * @param user User to be deleted
     */

    public void delete(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // 1. 모든 저널의 관계를 끊음
        for (Journal journal : new ArrayList<>(user.getJournals())) {
            user.removeJournal(journal);
        }
        // 삭제 직전 딱 한 줄 추가
        user.getJournals().clear();
        // 이렇게 하면 orphanRemoval=true에 의해 Hibernate가 알아서 다 삭제 처리합니다.


        session.remove(user);
        transaction.commit();
        session.close();
    }


    /** * Return a list of all users
     * @return All users
     */
    public List<User> getAll() {
        Session session = sessionFactory.openSession();
        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        query.from(User.class);

        List<User> users = session.createSelectionQuery(query).getResultList();

        logger.debug("The list of users " + users);
        session.close();
        return users;
    }

    /**
     * Get user by property (exact match)
     */
    public List<User> getByPropertyEqual(String propertyName, Object value) {
        Session session = sessionFactory.openSession();
        logger.debug("Searching for user with {} = {}", propertyName, value);

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(builder.equal(root.get(propertyName), value));

        List<User> users = session.createSelectionQuery(query).getResultList();

        session.close();
        return users;
    }

    /**
     * Get user by property (like)
     */
    public List<User> getByPropertyLike(String propertyName, String value) {
        Session session = sessionFactory.openSession();
        logger.debug("Searching for user with {} like {}", propertyName, value);

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<User> users = session.createSelectionQuery(query).getResultList();

        session.close();
        return users;
    }

    /**
     * [추가] Cognito ID(sub)로 사용자 조회
     * 새로운 DB 구조에서 필수적인 기능이라 추가했습니다.
     */
    public User getByCognitoId(String cognitoId) {
        List<User> users = getByPropertyEqual("cognitoId", cognitoId);
        return (users != null && !users.isEmpty()) ? users.get(0) : null;
    }

    /**
     * [추가] UserName으로 단일 사용자 조회
     */
    public User getByUserName(String userName) {
        List<User> users = getByPropertyEqual("userName", userName);
        return (users != null && !users.isEmpty()) ? users.get(0) : null;
    }
}