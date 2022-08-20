package toyproject.novelist.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import toyproject.novelist.config.auth.LoginUser;
import toyproject.novelist.config.auth.dto.SessionUser;
import toyproject.novelist.domain.Post;
import toyproject.novelist.domain.user.User;
import toyproject.novelist.domain.word.TodayWords;
import toyproject.novelist.dto.PostForm;
import toyproject.novelist.service.PostService;
import toyproject.novelist.service.TodayWordsService;
import toyproject.novelist.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    private final TodayWordsService todayWordsService;
    private final UserService userService;

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
    public String create(@Valid PostForm postForm, BindingResult result, @LoginUser SessionUser user) throws Exception{

        if (result.hasErrors()) {
            return "post/createPostForm";
        }

        User user1 =userService.findByEmail(user.getEmail()).get();

        // 글 등록 로직 구현해야함
        Post post = new Post();
        post.setContent(postForm.getContent());
        post.setUser(user1);
        post.setPostDate(LocalDateTime.now());

        postService.join(post);


        return "redirect:/";
    }
}
