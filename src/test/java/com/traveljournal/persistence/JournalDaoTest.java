package com.traveljournal.persistence;

import com.traveljournal.entity.Journal;
import com.traveljournal.entity.Location;
import com.traveljournal.entity.User;
import com.traveljournal.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class JournalDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());

    JournalDao journalDao;
    UserDao userDao;
    LocationDao locationDao;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");

        journalDao = new JournalDao();
        userDao = new UserDao();
        locationDao = new LocationDao();
    }

    @Test
    void getById() {
        Journal journal = journalDao.getById(1);
        assertNotNull(journal);
        assertEquals("Administrator 1", journal.getTitle());
        assertEquals("admin", journal.getUser().getUserName());
    }

    @Test
    void update() {
        String updatedTitle = "New Title for Test";
        Journal journalToUpdate = journalDao.getById(1);
        journalToUpdate.setTitle(updatedTitle);

        journalDao.update(journalToUpdate);

        Journal retrieved = journalDao.getById(1);
        assertEquals(updatedTitle, retrieved.getTitle());
    }

    @Test
    void insert() {
        User user = userDao.getById(2);
        Location location = locationDao.getById(1);

        Journal newJournal = new Journal();
        newJournal.setTitle("New Adventure");
        newJournal.setContent("This is a test content.");
        newJournal.setWeather("Sunny");
        newJournal.setUser(user);
        newJournal.setLocation(location);

        // GenericDao의 insert는 void를 반환하므로 호출 후 객체에서 ID를 꺼냅니다.
        journalDao.insert(newJournal);

        assertNotEquals(0, newJournal.getId());
        Journal insertedJournal = journalDao.getById(newJournal.getId());
        assertNotNull(insertedJournal);
        assertEquals("New Adventure", insertedJournal.getTitle());
        assertEquals(user.getUserName(), insertedJournal.getUser().getUserName());
    }

    @Test
    void delete() {
        List<Journal> allJournals = journalDao.getAll();
        assertFalse(allJournals.isEmpty());
        Journal journal = allJournals.get(0);
        int targetId = journal.getId();
        int userId = journal.getUser().getId();

        journalDao.delete(journal);

        assertNull(journalDao.getById(targetId));
        assertNotNull(userDao.getById(userId), "저널을 지워도 유저는 남아있어야 합니다.");
    }

    @Test
    void getAll() {
        List<Journal> journals = journalDao.getAll();
        assertEquals(4, journals.size());
    }

    @Test
    void getByPropertyEqual() {
        List<Journal> journals = journalDao.getByPropertyEqual("weather", "Cold");
        assertFalse(journals.isEmpty());
        assertEquals("Cold", journals.get(0).getWeather());
    }

    @Test
    void getByPropertyLike() {
        List<Journal> journals = journalDao.getByPropertyLike("title", "Admin");
        assertTrue(journals.size() >= 1);
    }

    @Test
    void getByLocationName() {
        List<Journal> journals = journalDao.getByLocationName("Paris");
        assertFalse(journals.isEmpty());
        assertEquals("Paris", journals.get(0).getLocation().getName());
    }

    @Test
    void getByLocationNameAndUser() {
        List<Journal> journals = journalDao.getByLocationNameAndUser("Amsterdam", 2);
        assertFalse(journals.isEmpty());
        for (Journal journal : journals) {
            assertEquals("Amsterdam", journal.getLocation().getName());
            assertEquals(2, journal.getUser().getId());
        }
    }

    @Test
    void getByWeatherAndUser() {
        List<Journal> journals = journalDao.getByWeatherAndUser("Cold", 1);
        assertFalse(journals.isEmpty());
        for (Journal journal : journals) {
            assertTrue(journal.getWeather().contains("Cold"));
            assertEquals(1, journal.getUser().getId());
        }
    }

    @Test
    void getJournalsByUserName() {
        List<Journal> journals = journalDao.getJournalsByUserName("admin");
        assertFalse(journals.isEmpty());
        for (Journal journal : journals) {
            assertEquals("admin", journal.getUser().getUserName());
        }
    }
}