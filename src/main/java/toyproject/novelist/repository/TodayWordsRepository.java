package toyproject.novelist.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import toyproject.novelist.domain.word.TodayWords;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;

@Repository
@RequiredArgsConstructor
public class TodayWordsRepository {

    private final EntityManager em;

    public void save(TodayWords todayWords) {
        em.persist(todayWords);
    }

    // 삭제하고 생성할지 아니면 update로 할 지 고민이다. 일단 만들어두자.
    public void delete(TodayWords todayWords) {
        em.remove(todayWords);
    }

    // 테스트메서드 필수.
    public TodayWords findTodayWords() {
        String query = "select t from TodayWords t";

        TodayWords todayWords;

        try {
            todayWords = em.createQuery(query, TodayWords.class)
                    .getSingleResult();
        } catch ( NonUniqueResultException e ) { // 엔티티 없으면 null 반환
            return null;
        }
        return todayWords;
    }

    public void changeWords(Long todayWordsId) {
        // 단어 바꾸는 메소드
    }
}

