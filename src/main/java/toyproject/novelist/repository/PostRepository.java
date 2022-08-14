package toyproject.novelist.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import toyproject.novelist.domain.Post;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class PostRepository {
    private final EntityManager em;

    public void save(Post post) {
        em.persist(post);
    }
}
