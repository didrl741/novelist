package toyproject.novelist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import toyproject.novelist.config.auth.dto.SessionUser;
import toyproject.novelist.domain.Pagination;
import toyproject.novelist.domain.Post;
import toyproject.novelist.domain.user.Member;
import toyproject.novelist.domain.word.TodayWords;
import toyproject.novelist.service.LoveService;
import toyproject.novelist.service.PostService;
import toyproject.novelist.service.TodayWordsService;
import toyproject.novelist.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;
    private final TodayWordsService todayWordsService;
    private final LoveService loveService;
    private final UserService userService;

    @GetMapping("/")
    public String home(Model model, @RequestParam(defaultValue = "1") int page, @AuthenticationPrincipal SessionUser user) throws Exception {

        int totalListCnt = postService.findAllCnt();
        Pagination pagination = new Pagination(totalListCnt, page);

        int startIdx = pagination.getStartIndex();
        int pageSize = pagination.getPageSize();

        List<Post> postListByDate = postService.findByLatestDate(startIdx, pageSize);
        List<Post> postListByLoveCount = postService.findByLoveCount(startIdx, pageSize);

        if (user != null) {
            if (userService.findByEmail(user.getEmail()).isPresent()) {

                Member logInedMember = userService.findByEmail(user.getEmail()).get();
                Long logInedUserId = logInedMember.getId();

                for (Post post : postListByDate) {
                    if (loveService.findByUserAndPost(logInedUserId, post.getId()) != null) {
                        post.setLovedByLogInedUser(true);
                    }
                }

                for (Post post : postListByLoveCount) {
                    if (loveService.findByUserAndPost(logInedUserId, post.getId()) != null) {
                        post.setLovedByLogInedUser(true);
                    }
                }

            }
        }

        TodayWords todayWords = todayWordsService.findTodayWords();
        String[] wordFive = todayWords.makeArr();

        model.addAttribute("pagination", pagination);
        model.addAttribute("postListByDate", postListByDate);
        model.addAttribute("postListByLoveCount", postListByLoveCount);
        model.addAttribute("wordFive", wordFive);

        return "index";
    }

}
