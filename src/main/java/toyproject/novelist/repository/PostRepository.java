package toyproject.novelist.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import toyproject.novelist.domain.Post;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {
    private final EntityManager em;

    public void save(Post post) {
        em.persist(post);
    }


    public int findAllCnt() {
        return ((Number) em.createQuery("select count(p) from Post p")
                .getSingleResult()).intValue();
    }

    public List<Post> findByLatestDate(int startIdx, int pageSize) {
        return em.createQuery("select p from Post p order by p.postDate desc", Post.class)
                .setFirstResult(startIdx)
                .setMaxResults(pageSize)
                .getResultList();
    }
}
