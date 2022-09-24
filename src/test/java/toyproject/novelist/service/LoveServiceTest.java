package toyproject.novelist.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toyproject.novelist.domain.Love;
import toyproject.novelist.domain.Post;
import toyproject.novelist.domain.user.Role;
import toyproject.novelist.domain.user.User;
import toyproject.novelist.domain.word.TodayWords;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LoveServiceTest {

    @Autowired
    LoveService loveService;

    @Autowired PostService postService;
    @Autowired UserService userService;

    @Test
    public void 좋아요생성테스트() throws Exception {
        //given
        Post post = new Post();
        System.out.println("loveCount == " + post.getLoves());

        //when
        User user = new User();
        Love love = new Love(user, post);

        //then
        System.out.println("loveCount == " + post.getLoves());
    }

    @Test
    public void findByUserAndPost테스트() throws Exception {
        //given
        Post post = new Post();
        postService.join(post);

        User user = new User("song", "didrl741@naver.com", Role.USER);
        userService.join(user);
        Love love = new Love(user, post);
        loveService.join(love);

        //when
        Love findLove = loveService.findByUserAndPost(user.getId(), post.getId());

        //then
        assertEquals(love, findLove);
    }
}