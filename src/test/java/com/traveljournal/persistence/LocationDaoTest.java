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
        // 매 테스트 시작 전 DB를 깨끗한 상태로 초기화
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
        dao = new LocationDao();
    }

    /**
     * ID로 장소 조회 테스트
     */
    @Test
    void getById() {
        Location location = dao.getById(1);
        assertNotNull(location);
        // cleanDB.sql의 1번 데이터 이름(예: Paris)과 일치하는지 확인
        assertEquals("Paris", location.getName());
        logger.debug("조회된 장소: {}", location.getName());
    }

    /**
     * 새로운 장소 추가 테스트
     */
    @Test
    void insert() {
        Location newLocation = new Location("Seoul");
        int id = dao.insert(newLocation);

        assertNotEquals(0, id);
        Location inserted = dao.getById(id);
        assertNotNull(inserted);
        assertEquals("Seoul", inserted.getName());
    }

    /**
     * 장소 이름 수정 테스트
     */
    @Test
    void update() {
        // 1. ID가 1인 장소를 가져옵니다 (기존 이름: Paris 가정)
        Location locationToUpdate = dao.getById(1);
        assertNotNull(locationToUpdate);

        // 2. 새로운 이름을 기존 DB에 없는 아주 유니크한 이름으로 바꿉니다.
        // 'London'이 이미 DB에 있다면 'London 2' 또는 'New Paris' 등으로 바꿔보세요.
        String newName = "New Paris";
        locationToUpdate.setName(newName);

        // 3. 업데이트 실행
        dao.update(locationToUpdate);

        // 4. 검증
        Location retrieved = dao.getById(1);
        assertEquals(newName, retrieved.getName());
    }

    /**
     * 모든 장소 목록 조회 테스트 (드롭다운 메뉴 등에 활용)
     */
    @Test
    void getAll() {
        List<Location> locations = dao.getAll();
        // cleanDB.sql에 기본으로 들어있는 장소 개수보다 큰지 확인
        assertTrue(locations.size() > 0);
        logger.debug("전체 장소 개수: {}", locations.size());
    }

    /**
     * 장소 삭제 테스트
     */
    @Test
    void delete() {
        // 외래 키 제약 조건(Foreign Key Constraint) 에러를 피하기 위해,
        // Journal이 연결되지 않은 새로운 장소를 만들어 삭제 테스트를 진행합니다.
        Location tempLocation = new Location("Delete Me");
        int id = dao.insert(tempLocation);

        // 삭제 전 존재 확인
        assertNotNull(dao.getById(id));

        // 삭제 실행
        dao.delete(dao.getById(id));

        // 삭제 후 null인지 확인
        assertNull(dao.getById(id));
    }
}