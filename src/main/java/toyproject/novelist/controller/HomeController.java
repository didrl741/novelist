package toyproject.novelist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import toyproject.novelist.domain.Post;
import toyproject.novelist.service.PostService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;

    @GetMapping("/")
    public String home(Model model) {

        List<Post> postList = postService.findByLatest();



        model.addAttribute("postList", postList);

        return "index";
    }
}
