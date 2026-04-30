package com.traveljournal.persistence;

import com.traveljournal.entity.Journal;
import com.traveljournal.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends GenericDao<User> {

    public UserDao() {
        super(User.class);
    }

    /**
     * [특수 로직] 삭제 시 연관된 저널들과의 관계 정리 오버라이드
     */
    @Override
    public void delete(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            User userToDelete = session.get(User.class, user.getId());
            if (userToDelete != null) {
                // 저널 리스트를 비워서 orphanRemoval 작동 유도
                userToDelete.getJournals().clear();
                session.remove(userToDelete);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public User getByCognitoId(String cognitoId) {
        List<User> users = getByPropertyEqual("cognitoId", cognitoId);
        return (users != null && !users.isEmpty()) ? users.get(0) : null;
    }

    public User getByUserName(String userName) {
        List<User> users = getByPropertyEqual("userName", userName);
        return (users != null && !users.isEmpty()) ? users.get(0) : null;
    }
}