package persistence;



import entity.Journal;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JournalDaoTest {

    JournalDao journalDao;

    @Test
    void getByIdSuccess() {
        journalDao = new JournalDao();
        Journal retrievedJournal = journalDao.getById(1);
        assertNotNull(retrievedJournal);
        assertEquals("Administrator", retrievedJournal.getTitle());
    }

    @Test
    void updateSuccess() {
        journalDao = new JournalDao();
        Journal journalToUpdate = journalDao.getById(1);
        journalToUpdate.setTitle("Administrator");
        journalDao.update(journalToUpdate);

        // retrieve the user and check that the name change worked
        Journal actualJournal = journalDao.getById(1);
        assertEquals("Administrator", actualJournal.getTitle());
    }


    @Test
    void insertSuccess() {

        journalDao = new JournalDao();
        Journal journalToInsert = new Journal(
                1, "Journal Test 1", "test", LocalDate.now(),
                LocalDate.now(), "Madison", "Cold"
        );
        int insertedJournalId = journalDao.insert(journalToInsert);
        assertNotEquals(0, insertedJournalId);
        Journal insertedJournal = journalDao.getById(insertedJournalId);
        assertEquals("Journal Test 1", insertedJournal.getTitle());
    }


    @Test
    void deleteSuccess() {
        journalDao = new JournalDao();
        journalDao.delete(journalDao.getById(2));
        assertNull(journalDao.getById(2));
    }

    @Test
    void getAllSuccess() {
        journalDao = new JournalDao();
        List<Journal> journals = journalDao.getAll();
        assertEquals(0, journals.size());
    }

    @Test
    void getByPropertyEqualSuccess() {
        journalDao = new JournalDao();
        List<Journal> journals = journalDao.getByPropertyLike("location", "Madison");
        assertEquals(4, journals.size());
        assertEquals(1, journals.get(0).getId());
    }

    @Test
    void getByPropertyLikeSuccess() {
        journalDao = new JournalDao();
        List<Journal> journals = journalDao.getByPropertyLike("location", "M");
        assertEquals(4, journals.size());
    }
}