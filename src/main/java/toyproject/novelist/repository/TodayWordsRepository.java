package toyproject.novelist.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import toyproject.novelist.domain.word.TodayWords;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import java.time.LocalDate;

@Repository
@RequiredArgsConstructor
public class TodayWordsRepository {

    private final EntityManager em;

    public void save(TodayWords todayWords) {
        em.persist(todayWords);
    }

    public TodayWords findTodayWords() {

        TodayWords todayWords;

        try {
            todayWords = em.createQuery("select t from TodayWords t", TodayWords.class)
                    .getSingleResult();
        } catch ( NonUniqueResultException e ) { // 엔티티 없으면 null 반환
            return null;
        }
        return todayWords;
    }

    public void changeWords(String[] wordArr, LocalDate localDate) {
        TodayWords todayWords = findTodayWords();
        todayWords.changeWords(wordArr, localDate);
    }
}

