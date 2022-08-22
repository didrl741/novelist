package toyproject.novelist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import toyproject.novelist.config.auth.LoginUser;
import toyproject.novelist.config.auth.dto.SessionUser;
import toyproject.novelist.domain.Pagination;
import toyproject.novelist.domain.Post;
import toyproject.novelist.domain.user.Role;
import toyproject.novelist.domain.user.User;
import toyproject.novelist.domain.word.TodayWords;
import toyproject.novelist.service.PostService;
import toyproject.novelist.service.TodayWordsService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;
    private final TodayWordsService todayWordsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String home(Model model, @RequestParam(defaultValue = "1") int page, @AuthenticationPrincipal SessionUser user) {

        int totalListCnt = postService.findAllCnt();
        Pagination pagination = new Pagination(totalListCnt, page);

        int startIdx = pagination.getStartIndex();
        int pageSize = pagination.getPageSize();

        List<Post> postList = postService.findByLatestDate(startIdx, pageSize);

        TodayWords todayWords = todayWordsService.findTodayWords();
        String[] wordFive = todayWords.makeArr();


        model.addAttribute("pagination", pagination);
        model.addAttribute("postList", postList);
        model.addAttribute("wordFive", wordFive);

        return "index";
    }

    @GetMapping("/test")
    public String test() {


        for(int i=0; i<30; i++) {
            Post post = new Post();
            User user = new User("testMan", "test@naver.com", Role.USER);
            post.setUser(user);
            post.setContent("테스트내용입니다 " + i);
            post.setPostDate(LocalDateTime.now());
            postService.join(post);
        }

        return "index";
    }
}
