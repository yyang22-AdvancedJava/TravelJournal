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
        // 매 테스트 시작 전 DB를 깨끗하게 초기화합니다.
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
        User userToUpdate = dao.getById(2); // Joe Coyne
        userToUpdate.setFirstName(newFirstName);

        dao.update(userToUpdate);

        User retrievedUser = dao.getById(2);
        assertEquals(newFirstName, retrievedUser.getFirstName());
    }

    @Test
    void insert() {
        // 새 유저 생성 (5개 파라미터 생성자 사용)
        User newUser = new User("Katie", "Yeoseon", "yeoseon", "pass123", "aws-sub-999");
        int id = dao.insert(newUser);

        assertNotEquals(0, id);
        User insertedUser = dao.getById(id);
        assertNotNull(insertedUser);
        assertEquals("yeoseon", insertedUser.getUserName());
        assertEquals("aws-sub-999", insertedUser.getCognitoId());
    }

    @Test
    void delete() {
        User userToDelete = dao.getById(3); // Fred Hensen
        dao.delete(userToDelete);

        assertNull(dao.getById(3));
    }

    @Test
    void getAll() {
        List<User> users = dao.getAll();
        // cleanDB.sql에 기본 7명이 있다고 가정
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
        // 'man'이 들어가는 성씨(Tillman 등) 검색
        List<User> users = dao.getByPropertyLike("lastName", "man");
        assertTrue(users.size() >= 1);
    }

    @Test
    void getByCognitoId() {
        // cleanDB.sql에서 Admin에게 부여한 dummy id로 테스트
        User user = dao.getByCognitoId("admin-dummy-id");
        assertNotNull(user);
        assertEquals("admin", user.getUserName());
    }

    @Test
    void getByUserName() {
        User user = dao.getByUserName("jcoyne");
        assertNotNull(user);
        assertEquals("Joe", user.getFirstName());
    }
}