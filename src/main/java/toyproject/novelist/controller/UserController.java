package toyproject.novelist.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import toyproject.novelist.config.auth.LoginUser;
import toyproject.novelist.config.auth.dto.SessionUser;
import toyproject.novelist.domain.user.Role;
import toyproject.novelist.domain.user.User;
import toyproject.novelist.dto.UserForm;
import toyproject.novelist.service.UserService;

@Controller
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserController {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;

    // 로그인
    @GetMapping("/login")
    public String loginForm(Model model) {

        model.addAttribute("userForm", new UserForm());

        return "members/login";
    }

    @GetMapping("/login?error")
    public String login_errors(Model model) {

        model.addAttribute("userForm", new UserForm());

        return "redirect:/login";
    }

    @ResponseBody
    @GetMapping("/test/login")
    public String login_test(@AuthenticationPrincipal SessionUser user) {

        return user.getEmail();
    }

    // 회원가입 페이지로 이동
    @GetMapping("/join")
    public String createForm(Model model) {

        model.addAttribute("userForm", new UserForm());
        return "members/createMemberForm";
    }

    // 회원가입
    @PostMapping("/join")
    public String create(@Validated @ModelAttribute("userForm") UserForm userForm, BindingResult result) {

        if (!StringUtils.hasText(userForm.getName())) {

            result.rejectValue("name", "required", null, null);
        }

        if (!StringUtils.hasText(userForm.getEmail())) {

            result.rejectValue("email", "required", null, null);
        }

        if (!StringUtils.hasText(userForm.getPassword())) {

            result.rejectValue("password", "required", null, null);
        }

        if (userService.duplicateName(userForm.getName()) != null) {

            result.rejectValue("name", "duplicate", null, null);
        }

        if (userService.duplicateEmail(userForm.getEmail()) != null) {

            result.rejectValue("email", "duplicate", null, null);
        }

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        User user = new User(userForm.getName(), userForm.getEmail(), Role.USER, userForm.getPassword());
        userService.join(user);


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
