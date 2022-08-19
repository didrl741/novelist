package toyproject.novelist.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import toyproject.novelist.auth.PrincipalDetails;
import toyproject.novelist.domain.user.Member;
import toyproject.novelist.dto.MemberForm;
import toyproject.novelist.repository.MemberRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 로그인
    @GetMapping("/login")
    public String loginForm() {
        return "members/login";
    }


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

}
