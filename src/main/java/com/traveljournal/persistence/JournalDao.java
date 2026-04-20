package com.traveljournal.persistence;

import com.traveljournal.entity.Journal;
import com.traveljournal.entity.Location;
import com.traveljournal.entity.User; // 이 줄을 추가하세요!
import com.traveljournal.persistence.SessionFactoryProvider;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import java.util.List;

public class JournalDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * ID로 일기 조회
     */
    public Journal getById(int id) {
        Session session = sessionFactory.openSession();
        Journal journal = session.get(Journal.class, id);
        session.close();
        return journal;
    }

    /**
     * 일기 수정
     */
    public void update(Journal journal) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(journal);
        transaction.commit();
        session.close();
    }

    /**
     * 새로운 일기 삽입
     */
    public int insert(Journal journal) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(journal);
        transaction.commit();
        int id = journal.getId();
        session.close();
        return id;
    }

    /*
    public void delete(Journal journal) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            // 1. 삭제할 저널을 현재 세션으로 가져옴 (merge)
            Journal mergedJournal = session.merge(journal);

            // 2. 연결된 User 객체를 가져옴
            User user = mergedJournal.getUser();

            if (user != null) {
                // 3. 중요: User의 리스트에서 이 Journal을 제거 (User 엔티티에 만든 메서드 활용)
                // 만약 removeJournal 메서드가 없다면 user.getJournals().remove(mergedJournal);
                user.removeJournal(mergedJournal);

                // 4. User의 변경 사항을 세션에 반영 (Cascade 때문에 필요할 수 있음)
                session.merge(user);
            }

            // 5. 이제 Journal 객체를 삭제
            session.remove(mergedJournal);

            // 6. 강제로 DB에 반영 (에러 발생 지점을 명확히 함)
            session.flush();

            transaction.commit();
            logger.info("Journal deleted successfully: ID {}", mergedJournal.getId());
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("Error deleting journal: ", e);
            throw e;
        } finally {
            session.close();
        }
    }
    */
    public void delete(Journal journal) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            // 전달받은 객체의 ID로 세션에서 직접 조회 (가장 확실한 방법)
            Journal journalToDelete = session.get(Journal.class, journal.getId());

            if (journalToDelete != null) {
                // 양방향 관계인 경우 부모의 리스트에서도 제거
                if (journalToDelete.getUser() != null) {
                    journalToDelete.getUser().getJournals().remove(journalToDelete);
                }
                session.remove(journalToDelete);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    /**
     * 모든 일기 목록 반환
     */
    public List<Journal> getAll() {
        Session session = sessionFactory.openSession();
        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Journal> query = builder.createQuery(Journal.class);
        query.from(Journal.class);

        List<Journal> journals = session.createSelectionQuery(query).getResultList();

        logger.debug("The list of journals: " + journals);
        session.close();
        return journals;
    }

    /**
     * 특정 속성 일치 검색
     */
    public List<Journal> getByPropertyEqual(String propertyName, Object value) {
        Session session = sessionFactory.openSession();
        logger.debug("Searching for Journal with {} = {}", propertyName, value);

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Journal> query = builder.createQuery(Journal.class);
        Root<Journal> root = query.from(Journal.class);
        query.select(root).where(builder.equal(root.get(propertyName), value));

        List<Journal> journals = session.createSelectionQuery(query).getResultList();

        session.close();
        return journals;
    }

    /**
     * 특정 속성 Like 검색
     */
    public List<Journal> getByPropertyLike(String propertyName, String value) {
        Session session = sessionFactory.openSession();
        logger.debug("Searching for Journal with {} like {}", propertyName, value);

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Journal> query = builder.createQuery(Journal.class);
        Root<Journal> root = query.from(Journal.class);
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<Journal> journals = session.createSelectionQuery(query).getResultList();

        session.close();
        return journals;
    }

    /**
     * [추가] 장소 이름(Location Name)으로 일기 조회
     * 새로운 DB 구조에서는 Location이 객체이므로 Join이 필요합니다.
     */
    public List<Journal> getByLocationName(String locationName) {
        Session session = sessionFactory.openSession();
        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Journal> query = builder.createQuery(Journal.class);
        Root<Journal> root = query.from(Journal.class);

        // Journal 엔티티의 location 필드와 조인
        Join<Journal, Location> locationJoin = root.join("location");
        query.select(root).where(builder.equal(locationJoin.get("name"), locationName));

        List<Journal> journals = session.createSelectionQuery(query).getResultList();
        session.close();
        return journals;
    }

    public List<Journal> getByLocationNameAndUser(String locationName, int userId) {
        Session session = sessionFactory.openSession();
        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Journal> query = builder.createQuery(Journal.class);
        Root<Journal> root = query.from(Journal.class);

        Join<Journal, Location> locationJoin = root.join("location");

        // 조건 추가: 1. 도시 이름 일치 AND 2. 유저 ID 일치
        query.select(root).where(
                builder.and(
                        builder.equal(locationJoin.get("name"), locationName),
                        builder.equal(root.get("user").get("id"), userId)
                )
        );

        List<Journal> journals = session.createSelectionQuery(query).getResultList();
        session.close();
        return journals;
    }

    public List<Journal> getByWeatherAndUser(String weather, int userId) {
        Session session = sessionFactory.openSession();
        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Journal> query = builder.createQuery(Journal.class);
        Root<Journal> root = query.from(Journal.class);

        // 조건: 1. 날씨 Like 검색 AND 2. 유저 ID 일치
        query.select(root).where(
                builder.and(
                        builder.like(root.get("weather"), "%" + weather + "%"),
                        builder.equal(root.get("user").get("id"), userId)
                )
        );

        List<Journal> journals = session.createSelectionQuery(query).getResultList();
        session.close();
        return journals;
    }
}