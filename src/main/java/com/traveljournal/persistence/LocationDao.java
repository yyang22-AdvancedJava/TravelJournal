package com.traveljournal.persistence;

import com.traveljournal.entity.Location;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class LocationDao {

    private SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public Location getById(int id) {
        Session session = sessionFactory.openSession();
        Location location = session.get(Location.class, id);
        session.close();
        return location;
    }

    public int insert(Location location) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(location);
        transaction.commit();
        int id = location.getId();
        session.close();
        return id;
    }

    public void update(Location location) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(location);
        transaction.commit();
        session.close();
    }

    public void delete(Location location) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(session.contains(location) ? location : session.merge(location));
        transaction.commit();
        session.close();
    }

    public List<Location> getAll() {
        Session session = sessionFactory.openSession();
        List<Location> locations = session.createSelectionQuery("from Location", Location.class).getResultList();
        session.close();
        return locations;
    }
}