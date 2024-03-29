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
import toyproject.novelist.config.auth.dto.SessionUser;
import toyproject.novelist.domain.Love;
import toyproject.novelist.domain.Post;
import toyproject.novelist.domain.user.Member;
import toyproject.novelist.domain.word.TodayWords;
import toyproject.novelist.dto.PostForm;
import toyproject.novelist.service.LoveService;
import toyproject.novelist.service.PostService;
import toyproject.novelist.service.TodayWordsService;
import toyproject.novelist.service.UserService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
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

    @GetMapping("/nonMember/write")
    public String createPostFormByNonMember(Model model) {

        TodayWords todayWords = todayWordsService.findTodayWords();
        String[] wordFive = todayWords.makeArr();

        model.addAttribute("postForm", new PostForm());
        model.addAttribute("wordFive", wordFive);
        return "post/createPostFormByNonMember";
    }

    // 글 제출
    @PostMapping("/write")
    public String create(@Valid PostForm postForm, BindingResult result, @AuthenticationPrincipal SessionUser user) throws Exception {

        if (result.hasErrors()) {
            return "post/createPostForm";
        }

        Member logInedMember = userService.findByEmail(user.getEmail()).get();

        TodayWords todayWords = todayWordsService.findTodayWords();

        Post post = new Post(postForm.getContent(), LocalDateTime.now(), logInedMember, todayWords);

        postService.join(post);

        return "redirect:/";
    }

    // 글 제출
    @PostMapping("/nonMember/write")
    public String createByNonMember(@Valid PostForm postForm, BindingResult result) throws Exception {

        if (result.hasErrors()) {
            return "post/createPostForm";
        }

        TodayWords todayWords = todayWordsService.findTodayWords();

        Post post = new Post(postForm.getContent(), LocalDateTime.now(), todayWords);

        postService.join(post);

        return "redirect:/";
    }


     //좋아요기능 (비동기)
    @ResponseBody
    @PostMapping("/{postId}/likeAndHateByAjax")
    public Map<String, Object> loveByAjax(@PathVariable("postId") Long postId, @AuthenticationPrincipal SessionUser user) {

        Map<String, Object> returnMap = new HashMap<>();

        String logInedUserEmail = user.getEmail();

        if (userService.findByEmail(logInedUserEmail).isPresent()) {
            Member logInedMember = userService.findByEmail(logInedUserEmail).get();

            Long userId = logInedMember.getId();

            Post findPost = postService.findOne(postId);

            if (findPost.getMember() == logInedMember) {
                returnMap.put("myPost", "yes");
                return returnMap;
            }

            Love findLove = loveService.findByUserAndPost(userId, postId);

            if (findLove == null) {
                Love love = new Love(logInedMember, findPost);

                // setPost 안해도 연관관계 편의메서드 실행되나??

                loveService.join(love);

                returnMap.put("check", "loved");


            } else {
                loveService.deleteLove(findLove.getId());

                returnMap.put("check", "canceled");
            }


            // 방법 1. 엔티티 필드에서 수정 -> 이것만 있으면 db에 반영이 안 된다.
            // findPost.changeLoveCount(findPost.getLoves().size());

            // 이것도 써줘야 한다.
            //postService.join(findPost);

            // 방법 2. 서비스 계층에서 처리
            postService.setLoveCnt(postId,findPost.getLoves().size());

            returnMap.put("count", findPost.getLoveCount());
        }

        return returnMap;
    }



}
