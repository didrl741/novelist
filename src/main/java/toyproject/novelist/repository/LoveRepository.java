package toyproject.novelist.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import toyproject.novelist.domain.Love;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class LoveRepository {

    private final EntityManager em;

    public void save(Love love) {
        em.persist(love);
    }

    public Love findOne(Long loveId) {
        return em.find(Love.class, loveId);
    }


    public void deleteOne(Long loveId) {
        Love love = findOne(loveId);
        em.remove(love);
    }

    public Love findByUserAndPost(Long memberId, Long postId) {
        List<Love> findLoves = em.createQuery("select l from Love l where l.member.id = :memberId and l.post.id = : postId", Love.class)
                .setParameter("memberId", memberId)
                .setParameter("postId", postId)
                .getResultList();
        if (findLoves.isEmpty()) return null;

        return findLoves.get(0);
    }

}