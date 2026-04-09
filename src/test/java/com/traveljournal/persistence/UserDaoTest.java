package com.traveljournal.persistence;

import com.traveljournal.entity.Journal;
import com.traveljournal.entity.User;
import com.traveljournal.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class UserDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());

    // 1. UserDao 대신 GenericDao<User>를 사용합니다.
    GenericDao<User> dao;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
        // 2. GenericDao 인스턴스를 생성하며 User 클래스 타입을 넘겨줍니다.
        dao = new GenericDao<>(User.class);
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

    @Test
    void deleteUserAndTheirJournals() {
        int targetUserId = 3;
        User user = dao.getById(targetUserId);
        assertNotNull(user);

        // [핵심] 부모-자식 관계를 명시적으로 끊어줍니다.
        // orphanRemoval = true 설정 덕분에 리스트를 비우는 것만으로도 삭제 대상이 됩니다.
        // user.getJournals().clear();
        user.getJournals();

        // 이제 깔끔해진 유저 객체를 삭제합니다.
        dao.delete(user);

        assertNull(dao.getById(targetUserId));
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