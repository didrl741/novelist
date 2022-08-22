package toyproject.novelist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.novelist.domain.Love;
import toyproject.novelist.repository.LoveRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoveService {

    private final LoveRepository loveRepository;

    @Transactional
    public Long join(Love love) {
        loveRepository.save(love);
        return love.getId();
    }

    public Love findOne(Long loveId) {
        return loveRepository.findOne(loveId);
    }

    @Transactional
    public void deleteLove(Long loveId) {
        loveRepository.deleteOne(loveId);
    }

    public Love findByUserAndPost(Long userId, Long postId) {
        return loveRepository.findByUserAndPost(userId, postId);
    }
}