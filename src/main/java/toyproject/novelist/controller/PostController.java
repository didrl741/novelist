package toyproject.novelist.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import toyproject.novelist.config.auth.LoginUser;
import toyproject.novelist.config.auth.dto.SessionUser;
import toyproject.novelist.domain.Love;
import toyproject.novelist.domain.Post;
import toyproject.novelist.domain.user.User;
import toyproject.novelist.domain.word.TodayWords;
import toyproject.novelist.dto.PostForm;
import toyproject.novelist.service.LoveService;
import toyproject.novelist.service.PostService;
import toyproject.novelist.service.TodayWordsService;
import toyproject.novelist.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    private final TodayWordsService todayWordsService;
    private final UserService userService;
    private final LoveService loveService;

    @GetMapping("/write")

    public String createPostForm(Model model) {

        TodayWords todayWords = todayWordsService.findTodayWords();
        String[] wordFive = todayWords.makeArr();

        model.addAttribute("postForm", new PostForm());
        model.addAttribute("wordFive", wordFive);
        return "post/createPostForm";
    }

    // 글 제출
    @PostMapping("/write")
    public String create(@Valid PostForm postForm, BindingResult result, @AuthenticationPrincipal SessionUser user) throws Exception {

        if (result.hasErrors()) {
            return "post/createPostForm";
        }

        User logInedUser = userService.findByEmail(user.getEmail()).get();

        TodayWords todayWords = todayWordsService.findTodayWords();
        String [] words = todayWords.makeArr();

        Post post = new Post(postForm.getContent(), LocalDateTime.now(), logInedUser, todayWords);

        postService.join(post);

        return "redirect:/";
    }


    // 좋아요기능 (비동기)
    @ResponseBody
    @PostMapping("/{postId}/likeAndHateByAjax")
    public Map<String, Object> loveByAjax(@PathVariable("postId") Long postId, @AuthenticationPrincipal SessionUser user) {

        Map<String, Object> returnMap = new HashMap<>();
        System.out.println("check=====" + user);

        String logInedUserEmail = user.getEmail();
        User logInedUser = userService.findByEmail(logInedUserEmail).get();
        Long userId = logInedUser.getId();

        Post findPost = postService.findOne(postId);

        Love findLove = loveService.findByUserAndPost(userId, postId);



        if (findLove == null) {
            Love love = new Love(logInedUser, findPost);

            // setPost 안해도 연관관계 편의메서드 실행되나??

            loveService.join(love);

            returnMap.put("check", "loved");


        } else {
            loveService.deleteLove(findLove.getId());

            returnMap.put("check", "canceled");
        }

        findPost.setLoveCount(findPost.getLoves().size());

        // 왜 이걸 안쓰면 db에 반영이 안되지?????????????
        postService.join(findPost);

        returnMap.put("count", findPost.getLoveCount());

        return returnMap;
    }
}
