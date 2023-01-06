package toyproject.novelist.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toyproject.novelist.domain.Love;
import toyproject.novelist.domain.Post;
import toyproject.novelist.domain.user.Member;
import toyproject.novelist.domain.user.Role;

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
        Member member = new Member();
        Love love = new Love(member, post);

        //then
        System.out.println("loveCount == " + post.getLoves());
    }

    @Test
    public void findByUserAndPost테스트() throws Exception {
        //given
        Post post = new Post();
        postService.join(post);

        Member member = new Member("song", "didrl741@naver.com", Role.USER);
        userService.join(member);
        Love love = new Love(member, post);
        loveService.join(love);

        //when
        Love findLove = loveService.findByUserAndPost(member.getId(), post.getId());

        //then
        assertEquals(love, findLove);
    }
}