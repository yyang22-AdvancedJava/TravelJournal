package persistence;



import entity.Journal;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Database;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JournalDaoTest {

    JournalDao journalDao;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
        journalDao = new JournalDao();
    }

    @Test
    void getById() {

        Journal retrievedJournal = journalDao.getById(1);
        assertNotNull(retrievedJournal);
        assertEquals("Administrator 1", retrievedJournal.getTitle());
    }

    @Test
    void update() {

        Journal journalToUpdate = journalDao.getById(1);
        journalToUpdate.setLocation("Washington D.C.");
        journalDao.update(journalToUpdate);

        // retrieve the user and check that the name change worked
        Journal actualJournal = journalDao.getById(1);
        assertEquals("Washington D.C.", actualJournal.getLocation());
    }


    @Test
    void insert() {
        // get a user
        UserDao userDao = new UserDao();
        User user = userDao.getById(2);

        //create an order with that user
        Journal journalToInsert =
                new Journal(user, "Insert Test", null, LocalDate.now(), LocalDate.now(),
                            "Seoul", "Freezing");

        // insert the order
        int insertedJournalId = journalDao.insert(journalToInsert);

        //retrieve the order
        Journal retrievedJournal = journalDao.getById(insertedJournalId);

        //verify
        assertNotNull(retrievedJournal);
        assertEquals(journalToInsert.getTitle(), retrievedJournal.getTitle());
        assertEquals("Joe", journalToInsert.getUser().getFirstName());
    }


    @Test
    void delete() {

        journalDao.delete(journalDao.getById(4));
        assertNull(journalDao.getById(4));
    }

    @Test
    void getAll() {

        List<Journal> journals = journalDao.getAll();
        assertEquals(4, journals.size());
    }

    @Test
    void getByPropertyEqual() {

        List<Journal> journals = journalDao.getByPropertyLike("location", "Madison");
        //assertEquals(4, journals.size());
        assertEquals(5, journals.get(0).getId());
    }

    @Test
    void getByPropertyLike() {

        List<Journal> journals = journalDao.getByPropertyLike("location", "M");
        assertEquals(2, journals.size());
    }
}