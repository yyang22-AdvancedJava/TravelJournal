package com.traveljournal.persistence;

import com.traveljournal.entity.Journal;
import com.traveljournal.entity.Location;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import java.util.List;

/**
 * Data Access Object for Journal entities.
 *
 * @author yyang22
 */
public class JournalDao extends GenericDao<Journal> {

    /**
     * Instantiates a new Journal DAO.
     */
    public JournalDao() {
        super(Journal.class);
    }

    /**
     * Deletes a journal entry while maintaining bidirectional integrity.
     * This override ensures the journal is manually removed from the parent
     * User's collection before deletion to prevent "re-saved by cascade"
     * errors or Hibernate synchronization issues.
     *
     * @param journal the journal entity to delete
     */
    @Override
    public void delete(Journal journal) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Journal journalToDelete = session.get(Journal.class, journal.getId());
            if (journalToDelete != null) {
                // 부모 User의 리스트에서 자신을 제거하여 Re-save 에러 방지
                if (journalToDelete.getUser() != null) {
                    journalToDelete.getUser().getJournals().remove(journalToDelete);
                }
                session.remove(journalToDelete);
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
     * Retrieves all journal entries that match a specific location name.
     * Performs an inner join with the Location entity.
     *
     * @param locationName the name (or partial name) of the location
     * @return a list of matching journals
     */
    public List<Journal> getByLocationName(String locationName) {
        Session session = sessionFactory.openSession();
        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Journal> query = builder.createQuery(Journal.class);
        Root<Journal> root = query.from(Journal.class);

        Join<Journal, Location> locationJoin = root.join("location");
        query.select(root).where(builder.like(locationJoin.get("name"), "%" + locationName + "%"));

        List<Journal> journals = session.createSelectionQuery(query).getResultList();
        session.close();
        return journals;
    }

    /**
     * Retrieves journals for a specific user filtered by location name.
     *
     * @param locationName the name of the location
     * @param userId       the ID of the owner user
     * @return a list of matching journals belonging to the user
     */
    public List<Journal> getByLocationNameAndUser(String locationName, int userId) {
        Session session = sessionFactory.openSession();
        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Journal> query = builder.createQuery(Journal.class);
        Root<Journal> root = query.from(Journal.class);

        Join<Journal, Location> locationJoin = root.join("location");
        query.select(root).where(
                builder.and(
                        builder.like(locationJoin.get("name"), "%" + locationName + "%"),
                        builder.equal(root.get("user").get("id"), userId)
                )
        );

        List<Journal> journals = session.createSelectionQuery(query).getResultList();
        session.close();
        return journals;
    }

    /**
     * Retrieves journals for a specific user filtered by weather conditions.
     *
     * @param weather the weather description to search for
     * @param userId  the ID of the owner user
     * @return a list of journals matching the weather criteria
     */
    public List<Journal> getByWeatherAndUser(String weather, int userId) {
        Session session = sessionFactory.openSession();
        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Journal> query = builder.createQuery(Journal.class);
        Root<Journal> root = query.from(Journal.class);

        query.select(root).where(
                builder.and(
                        builder.like(root.get("weather"), "%" + weather + "%"),
                        builder.equal(root.get("user").get("id"), userId)
                )
        );

        List<Journal> journals = session.createSelectionQuery(query).getResultList();
        session.close();
        return journals;
    }

    /**
     * Retrieves all journals associated with a specific username.
     *
     * @param userName the username to filter by
     * @return a list of journals authored by the specified user
     */
    public List<Journal> getJournalsByUserName(String userName) {
        Session session = sessionFactory.openSession();
        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Journal> query = builder.createQuery(Journal.class);
        Root<Journal> root = query.from(Journal.class);

        query.select(root).where(builder.like(root.get("user").get("userName"), "%" + userName + "%"));

        List<Journal> journals = session.createSelectionQuery(query).getResultList();
        session.close();
        return journals;
    }
}