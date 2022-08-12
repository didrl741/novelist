package toyproject.novelist.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import toyproject.novelist.domain.PostForm;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    @GetMapping("/write")
    public String createPostForm(Model model) {
        model.addAttribute("postForm", new PostForm());
        return "post/createPostForm";
    }

    @PostMapping("/write")
    public String create(@Valid PostForm postForm, BindingResult result, HttpSession session) throws Exception{

        if (postForm.getContent().length() == 0) {
            return "post/createPostForm";
        }

        // 글 등록 로직 구현해야함


        return "redirect:/";
    }
}
