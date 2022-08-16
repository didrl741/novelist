package toyproject.novelist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import toyproject.novelist.domain.Post;
import toyproject.novelist.domain.word.TodayWords;
import toyproject.novelist.service.PostService;
import toyproject.novelist.service.TodayWordsService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;
    private final TodayWordsService todayWordsService;

    @GetMapping("/")
    public String home(Model model) {


        TodayWords todayWords = todayWordsService.findTodayWords();
        String[] wordFive = todayWords.makeArr();

        List<Post> postList = postService.findByLatest();

        model.addAttribute("postList", postList);
        model.addAttribute("wordFive", wordFive);

        return "index";
    }
}
