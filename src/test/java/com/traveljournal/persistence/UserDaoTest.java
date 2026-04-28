package com.traveljournal.persistence;

import com.traveljournal.entity.Journal;
import com.traveljournal.entity.User;
import com.traveljournal.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class UserDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());


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
        // 새 유저 생성 (엔티티 생성자 확인 필요)
        User newUser = new User("Katie", "Yeoseon", "yeoseon", "pass123", "aws-sub-999");
        int id = dao.insert(newUser);

        // 하이버네이트는 insert 후 엔티티 객체에 id를 자동으로 채워줍니다.
        assertNotEquals(0, newUser.getId());
        User insertedUser = dao.getById(newUser.getId());
        assertNotNull(insertedUser);
        assertEquals("yeoseon", insertedUser.getUserName());
        assertEquals("aws-sub-999", insertedUser.getCognitoId());
    }

    /**
     * Test deleting a user and their journals.
     * Verifies that the user and all associated journals are removed.
     */
    @Test
    void delete() {
        User userToDelete = dao.getById(1);

        // 1. 만약 저널이 있다면 명시적으로 연결을 끊어줍니다.
        if (userToDelete.getJournals() != null) {
            userToDelete.getJournals().clear();
        }

        // 2. 삭제 수행
        dao.delete(userToDelete);

        // 3. 검증
        assertNull(dao.getById(1), "User with ID 1 should be null after deletion.");
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

    // 3. 기존의 특수 메서드들은 getByPropertyEqual로 대체합니다.
    @Test
    void getByCognitoId() {
        // "cognitoId"는 User 엔티티의 변수명과 정확히 일치해야 합니다.
        List<User> users = dao.getByPropertyEqual("cognitoId", "admin-dummy-id");
        assertFalse(users.isEmpty());
        User user = users.get(0);
        assertEquals("admin", user.getUserName());
    }

    @Test
    void getByUserName() {
        // "userName"은 User 엔티티의 변수명과 정확히 일치해야 합니다.
        List<User> users = dao.getByPropertyEqual("userName", "jcoyne");
        assertFalse(users.isEmpty());
        User user = users.get(0);
        assertEquals("Joe", user.getFirstName());
    }
}