package toyproject.novelist.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import toyproject.novelist.domain.word.TodayWords;
import toyproject.novelist.domain.word.Words;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;

@Repository
@RequiredArgsConstructor
public class WordsRepository {

    private final EntityManager em;

    public void save(Words words) {
        em.persist(words);
    }

    public Words findWords() {

        Words words;

        try {
            words = em.createQuery("select w from Words w", Words.class)
                    .getSingleResult();
        } catch ( NonUniqueResultException e ) { // 엔티티 없으면 null 반환
            return null;
        }
        return words;
    }
}
