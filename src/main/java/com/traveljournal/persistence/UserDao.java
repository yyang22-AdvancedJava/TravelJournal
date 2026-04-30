package com.traveljournal.persistence;

import com.traveljournal.entity.Journal;
import com.traveljournal.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for User entities.
 *
 * @author yyang22
 */
public class UserDao extends GenericDao<User> {

    /**
     * Instantiates a new User DAO.
     */
    public UserDao() {
        super(User.class);
    }

    /**
     * Deletes a user and ensures all associated journal entries are also removed.
     * This method overrides the generic delete to explicitly clear the
     * user's journal collection, which triggers the 'orphanRemoval' mechanism
     * defined in the {@link User} entity to ensure database consistency.
     *
     * @param user the user entity to be deleted
     * @throws Exception if a database transaction error occurs
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

    /**
     * Retrieves a user based on their unique AWS Cognito ID.
     * This is primarily used for authenticating users via external identity providers.
     *
     * @param cognitoId the unique identifier from AWS Cognito
     * @return the matching user, or null if no user is found
     */
    public User getByCognitoId(String cognitoId) {
        List<User> users = getByPropertyEqual("cognitoId", cognitoId);
        return (users != null && !users.isEmpty()) ? users.get(0) : null;
    }

    /**
     * Retrieves a user based on their unique local username.
     *
     * @param userName the unique username to search for
     * @return the matching user, or null if no user is found
     */
    public User getByUserName(String userName) {
        List<User> users = getByPropertyEqual("userName", userName);
        return (users != null && !users.isEmpty()) ? users.get(0) : null;
    }
}