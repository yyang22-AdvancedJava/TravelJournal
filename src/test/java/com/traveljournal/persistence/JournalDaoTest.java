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
        // 매 테스트 전 DB 초기화
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");

        journalDao = new JournalDao();
        userDao = new UserDao();
        locationDao = new LocationDao();
    }

    /**
     * ID로 조회 테스트
     */
    @Test
    void getById() {
        Journal journal = journalDao.getById(1);
        assertNotNull(journal);
        assertEquals("Administrator 1", journal.getTitle());
        // 작성자 정보가 잘 오는지 확인
        assertEquals("admin", journal.getUser().getUserName());
    }

    /**
     * 수정 테스트
     */
    @Test
    void update() {
        String updatedTitle = "New Title for Test";
        Journal journalToUpdate = journalDao.getById(1);
        journalToUpdate.setTitle(updatedTitle);

        journalDao.update(journalToUpdate);

        Journal retrieved = journalDao.getById(1);
        assertEquals(updatedTitle, retrieved.getTitle());
    }

    /**
     * 삽입 테스트 (User와 Location 객체 연결 필수)
     */
    @Test
    void insert() {
        // 1. 기존 유저와 장소 가져오기
        User user = userDao.getById(2); // Joe Coyne
        Location location = locationDao.getById(1); // Paris

        // 2. 새 저널 객체 생성 및 부모 객체들 셋팅
        Journal newJournal = new Journal();
        newJournal.setTitle("New Adventure");
        newJournal.setContent("This is a test content.");
        newJournal.setWeather("Sunny");
        newJournal.setUser(user);
        newJournal.setLocation(location);

        // 3. 실행
        int id = journalDao.insert(newJournal);

        // 4. 검증
        assertNotEquals(0, id);
        Journal insertedJournal = journalDao.getById(id);
        assertNotNull(insertedJournal);
        assertEquals("New Adventure", insertedJournal.getTitle());
        assertEquals(user.getUserName(), insertedJournal.getUser().getUserName());
    }

    /**
     * 삭제 테스트 (User 보존 검증 포함)
     */

    /*
    @Test
    void delete() {
        Journal journalToDelete = journalDao.getById(4);
        assertNotNull(journalToDelete);

        int userId = journalToDelete.getUser().getId();

        // 삭제 실행 (내부적으로 user.removeJournal 호출함)
        journalDao.delete(journalToDelete);

        // 저널은 사라져야 함
        assertNull(journalDao.getById(4));

        // 작성자 유저는 살아있어야 함
        assertNotNull(userDao.getById(userId), "저널을 지워도 유저는 남아있어야 합니다.");
    }
    */
    @Test
    void delete() {
        // 1. 전체 목록에서 하나를 가져와서 ID를 확보 (고정 ID 4번은 위험함)
        List<Journal> allJournals = journalDao.getAll();
        assertFalse(allJournals.isEmpty(), "테스트할 데이터가 없습니다.");
        Journal journal = allJournals.get(0);
        int targetId = journal.getId();
        int userId = journal.getUser().getId();

        // 2. 삭제 실행
        journalDao.delete(journal);

        // 3. 검증 (getById가 null을 반환해야 성공)
        assertNull(journalDao.getById(targetId), "저널이 삭제되지 않았습니다.");

        // 4. 유저 생존 확인
        UserDao userDao = new UserDao(); // UserDao가 필드에 없다면 생성
        assertNotNull(userDao.getById(userId), "저널을 지워도 유저는 남아있어야 합니다.");
    }


    /**
     * 전체 목록 조회 테스트
     */
    @Test
    void getAll() {
        List<Journal> journals = journalDao.getAll();
        // cleanDB.sql에 삽입된 데이터 수에 맞춰 조정 (예: 4개)
        assertEquals(4, journals.size());
    }

    /**
     * 속성 일치 검색 테스트 (예: 날씨가 'Cold'인 것)
     */
    @Test
    void getByPropertyEqual() {
        List<Journal> journals = journalDao.getByPropertyEqual("weather", "Cold");
        assertFalse(journals.isEmpty());
        assertEquals("Cold", journals.get(0).getWeather());
    }

    /**
     * 속성 Like 검색 테스트 (제목 검색)
     */
    @Test
    void getByPropertyLike() {
        List<Journal> journals = journalDao.getByPropertyLike("title", "Admin");
        assertTrue(journals.size() >= 1);
    }

    /**
     * 장소 이름으로 검색 (JOIN 테스트)
     */
    @Test
    void getByLocationName() {
        List<Journal> journals = journalDao.getByLocationName("Paris");
        assertFalse(journals.isEmpty());
        assertEquals("Paris", journals.get(0).getLocation().getName());
    }
}