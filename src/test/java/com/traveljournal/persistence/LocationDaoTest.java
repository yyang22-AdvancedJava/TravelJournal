package com.traveljournal.persistence;

import com.traveljournal.entity.Location;
import com.traveljournal.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class LocationDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    LocationDao dao;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
        dao = new LocationDao();
    }

    @Test
    void getById() {
        Location location = dao.getById(1);
        assertNotNull(location);
        assertEquals("Paris", location.getName());
    }

    @Test
    void insert() {
        Location newLocation = new Location("Seoul");
        dao.insert(newLocation); // void 반환으로 변경

        assertNotEquals(0, newLocation.getId());
        Location inserted = dao.getById(newLocation.getId());
        assertNotNull(inserted);
        assertEquals("Seoul", inserted.getName());
    }

    @Test
    void update() {
        Location locationToUpdate = dao.getById(1);
        String newName = "New Paris";
        locationToUpdate.setName(newName);

        dao.update(locationToUpdate);

        Location retrieved = dao.getById(1);
        assertEquals(newName, retrieved.getName());
    }

    @Test
    void getAll() {
        List<Location> locations = dao.getAll();
        assertTrue(locations.size() > 0);
    }

    @Test
    void delete() {
        Location tempLocation = new Location("Delete Me");
        dao.insert(tempLocation);
        int id = tempLocation.getId();

        assertNotNull(dao.getById(id));
        dao.delete(tempLocation);
        assertNull(dao.getById(id));
    }
}