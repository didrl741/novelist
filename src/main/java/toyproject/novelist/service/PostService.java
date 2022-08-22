package toyproject.novelist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.novelist.domain.Post;
import toyproject.novelist.repository.PostRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post findOne(Long postId) {
        return postRepository.findOne(postId);
    }

    @Transactional
    public Long join(Post post) {
        postRepository.save(post);
        return post.getId();
    }

    public int findAllCnt() {
        return postRepository.findAllCnt();
    }

    public List<Post> findByLatestDate(int startIdx, int pageSize) {
        return postRepository.findByLatestDate(startIdx, pageSize);
    }
}
