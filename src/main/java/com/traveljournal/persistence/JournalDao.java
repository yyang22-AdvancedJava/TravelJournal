package com.traveljournal.persistence;

import com.traveljournal.entity.Journal;
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

public class JournalDao {
    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Get Journal by id
     */
    public Journal getById(int id) {
        Session session = sessionFactory.openSession();
        Journal journal = session.get(Journal.class, id);
        session.close();
        return journal;
    }

    /**
     * update journal
     * @param journal  journal to be updated
     */
    public void update(Journal journal) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(journal);
        transaction.commit();
        session.close();
    }

    /**
     * insert a new journal
     * @param journal  Journal to be inserted
     */
    public int insert(Journal journal) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(journal);
        transaction.commit();
        id = journal.getId();
        session.close();
        return id;
    }

    /**
     * Delete a journal
     * @param journal Journal to be deleted
     */
    public void delete(Journal journal) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(journal);
        transaction.commit();
        session.close();
    }


    /** Return a list of all journals
     *
     * @return All journals
     */
    public List<Journal> getAll() {

        Session session = sessionFactory.openSession();

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Journal> query = builder.createQuery(Journal.class);
        Root<Journal> root = query.from(Journal.class);
        List<Journal> journals = session.createSelectionQuery( query ).getResultList();

        logger.debug("The list of users " + journals);
        session.close();

        return journals;
    }

    /**
     * Get Journal by property (exact match)
     * sample usage: getByPropertyEqual("lastname", "Curry")
     */
    public List<Journal> getByPropertyEqual(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for user with " + propertyName + " = " + value);

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Journal> query = builder.createQuery(Journal.class);
        Root<Journal> root = query.from(Journal.class);
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<Journal> journals = session.createSelectionQuery( query ).getResultList();

        session.close();
        return journals;
    }

    /**
     * Get Journal by property (like)
     * sample usage: getByPropertyLike("lastname", "C")
     */
    public List<Journal> getByPropertyLike(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for Journal with {} = {}",  propertyName, value);

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Journal> query = builder.createQuery(Journal.class);
        Root<Journal> root = query.from(Journal.class);
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<Journal> journals = session.createQuery( query ).getResultList();
        session.close();
        return journals;
    }


}
