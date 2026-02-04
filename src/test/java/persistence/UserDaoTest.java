package persistence;

import entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    UserDao userDao;

    @Test
    void getById() {
        userDao = new UserDao();
        User retrievedUser = userDao.getById(1);
        assertNotNull(retrievedUser);
        assertEquals("Joe", retrievedUser.getFirstName());
    }

    @Test
    void update() {
    }

    @Test
    void insert() {
    }

    @Test
    void delete() {
    }

    @Test
    void getAll() {
    }

    @Test
    void getByPropertyEqual() {
    }

    @Test
    void getByPropertyLike() {
    }
}