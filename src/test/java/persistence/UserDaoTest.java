package persistence;

import entity.Journal;
import entity.User;

import util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    UserDao userDao;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
    }

    @Test
    void getById() {
        userDao = new UserDao();
        User retrievedUser = userDao.getById(1);
        assertNotNull(retrievedUser);
        assertEquals("admin", retrievedUser.getUserName());
    }

    @Test
    void update() {
        userDao = new UserDao();
        User userToUpdate = userDao.getById(1);
        userToUpdate.setLastName("Administrator");
        userDao.update(userToUpdate);

        // retrieve the user and check that the name change worked
        User actualUser = userDao.getById(1);
        assertEquals("Administrator", actualUser.getLastName());

    }

    @Test
    void insert() {
        userDao = new UserDao();
        User userToInsert = new User("Kia", "Yang", "ky001", "hy001pw");
        int insertedUserId = userDao.insert(userToInsert);
        assertNotEquals(0, insertedUserId);
        User insertedUser = userDao.getById(insertedUserId);
        assertEquals("Kia", insertedUser.getFirstName());
    }

    @Test
    void delete() {
        userDao = new UserDao();
        userDao.delete(userDao.getById(2));
        assertNull(userDao.getById(2));

    }

    @Test
    void deleteWithJournals() {
        // create the userDao
        userDao = new UserDao();

        // get the user we want to delete that has 2 orders associated
        User userToBeDeleted = userDao.getById(1);
        List<Journal> orders = userToBeDeleted.getJournals();

        // get the associated order numbers
        int orderNumber1 = orders.get(0).getId();
        int orderNumber2 = orders.get(1).getId();

        // delte the user
        userDao.delete(userToBeDeleted);
        assertNull(userDao.getById(1));

        // verify the user was deleted
        JournalDao journalDao = new JournalDao();

        // verify the orders were also deleted
        assertNull(journalDao.getById(orderNumber1));
        assertNull(journalDao.getById(orderNumber2));
    }

    @Test
    void getAll() {
        userDao = new UserDao();
        List<User> users = userDao.getAll();
        assertEquals(7, users.size());

    }

    @Test
    void getByPropertyEqual() {
        userDao = new UserDao();
        List<User> users = userDao.getByPropertyLike("firstName", "Admin");
        assertEquals(1, users.size());
        assertEquals(1, users.get(0).getId());

    }

    @Test
    void getByPropertyLike() {
        userDao = new UserDao();
        List<User> users = userDao.getByPropertyLike("lastName", "c");
        assertEquals(3, users.size());

    }
}