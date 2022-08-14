package toyproject.novelist.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import toyproject.novelist.dto.MemberForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    // 회원가입 페이지로 이동
    @GetMapping("/join/member")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }


    // 회원가입
    @PostMapping("/join/member")
    public String create(@Valid MemberForm memberForm, BindingResult result) {

        if (memberForm.getLoginId().length() == 0 || memberForm.getPassword().length() == 0) {
            return "members/createMemberForm";
        }

        // 회원가입 로직 추가해야됨

        return "redirect:/";
    }


    // 로그인 페이지로 이동
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/login";
    }

    // 로그인
    @PostMapping("/login")
    public String login(@Valid MemberForm memberForm, BindingResult result, HttpSession session, HttpServletRequest request, Model model) {

        if (memberForm.getLoginId().length() == 0 || memberForm.getPassword().length() == 0) {
            return "/login";
        }

    // 로그인기능 구현해야함

        return "redirect:/";
    }
}
