package toyproject.novelist.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import toyproject.novelist.domain.Post;
import toyproject.novelist.domain.word.TodayWords;
import toyproject.novelist.dto.PostForm;
import toyproject.novelist.service.PostService;
import toyproject.novelist.service.TodayWordsService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    private final TodayWordsService todayWordsService;

    @GetMapping("/write")
    public String createPostForm(Model model) {

        TodayWords todayWords = todayWordsService.findTodayWords();
        String[] wordFive = todayWords.makeArr();

        model.addAttribute("postForm", new PostForm());
        model.addAttribute("wordFive", wordFive);
        return "post/createPostForm";
    }

    @PostMapping("/write")
    public String create(@Valid PostForm postForm, BindingResult result, HttpSession session) throws Exception{

        if (result.hasErrors()) {
            return "post/createPostForm";
        }

        // 글 등록 로직 구현해야함
        Post post = new Post();
        post.setContent(postForm.getContent());
        //post.setMember();
        post.setPostDate(LocalDateTime.now());

        postService.join(post);


        return "redirect:/";
    }
}
