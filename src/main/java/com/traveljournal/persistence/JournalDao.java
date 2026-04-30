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

public class JournalDao extends GenericDao<Journal> {

    public JournalDao() {
        super(Journal.class);
    }

    /**
     * [특수 로직] 삭제 시 양방향 관계 정리 로직 오버라이드
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