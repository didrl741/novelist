package toyproject.novelist.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import toyproject.novelist.domain.word.Words;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class WordsRepository {

    private final EntityManager em;

    public void save(Words words) {
        em.persist(words);
    }
}
