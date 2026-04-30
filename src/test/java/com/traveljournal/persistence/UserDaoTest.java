package com.traveljournal.persistence;

import com.traveljournal.entity.User;
import com.traveljournal.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    UserDao dao;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
        dao = new UserDao();
    }

    @Test
    void getById() {
        User user = dao.getById(1);
        assertNotNull(user);
        assertEquals("Admin", user.getFirstName());
    }

    @Test
    void update() {
        String newFirstName = "UpdatedName";
        User userToUpdate = dao.getById(2);
        userToUpdate.setFirstName(newFirstName);

        dao.update(userToUpdate);

        User retrievedUser = dao.getById(2);
        assertEquals(newFirstName, retrievedUser.getFirstName());
    }

    @Test
    void insert() {
        User newUser = new User("Katie", "Yeoseon", "yeoseon", "pass123", "aws-sub-999");
        dao.insert(newUser);

        assertNotEquals(0, newUser.getId());
        User insertedUser = dao.getById(newUser.getId());
        assertNotNull(insertedUser);
        assertEquals("yeoseon", insertedUser.getUserName());
    }

    @Test
    void delete() {
        User userToDelete = dao.getById(1);
        assertNotNull(userToDelete);

        dao.delete(userToDelete);
        assertNull(dao.getById(1));
    }

    @Test
    void getAll() {
        List<User> users = dao.getAll();
        assertEquals(7, users.size());
    }

    @Test
    void getByPropertyEqual() {
        List<User> users = dao.getByPropertyEqual("lastName", "Coyne");
        assertEquals(1, users.size());
        assertEquals("Joe", users.get(0).getFirstName());
    }

    @Test
    void getByPropertyLike() {
        List<User> users = dao.getByPropertyLike("lastName", "man");
        assertTrue(users.size() >= 1);
    }

    @Test
    void getByCognitoId() {
        // UserDao에 정의한 특수 메서드 사용
        User user = dao.getByCognitoId("admin-dummy-id");
        assertNotNull(user);
        assertEquals("admin", user.getUserName());
    }

    @Test
    void getByUserName() {
        // UserDao에 정의한 특수 메서드 사용
        User user = dao.getByUserName("jcoyne");
        assertNotNull(user);
        assertEquals("Joe", user.getFirstName());
    }
}