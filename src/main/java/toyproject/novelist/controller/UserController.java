package toyproject.novelist.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import toyproject.novelist.config.auth.LoginUser;
import toyproject.novelist.config.auth.dto.SessionUser;
import toyproject.novelist.dto.UserForm;
import toyproject.novelist.repository.UserRepository;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 로그인
    @GetMapping("/login")
    public String loginForm() {
        return "members/login";
    }


    // 회원가입 페이지로 이동
    @GetMapping("/join/member")
    public String createForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "members/createMemberForm";
    }

    // 회원가입
    @PostMapping("/join/member")
    public String create(@Valid UserForm memberForm, BindingResult result) {

        if (memberForm.getLoginId().length() == 0 || memberForm.getPassword().length() == 0) {
            return "members/createMemberForm";
        }

        // 회원가입 로직 추가해야됨

        return "redirect:/";
    }

    // 회원정보 페이지로 이동
    @GetMapping("/userInfo")
    public String userInfo(Model model, @LoginUser SessionUser user) {

        if (user != null) {
            System.out.println("user =====" + user);
            model.addAttribute("user", user);
        }

        return "members/memberInfo";
    }

}
