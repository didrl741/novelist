package toyproject.novelist.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Controller
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserController {

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

        User user = new User(userForm.getName(), userForm.getEmail(), Role.USER, userForm.getPassword(), userForm.getAuth_email());
        userService.join(user);


        return "redirect:/";
    }

    // 회원정보 페이지로 이동
    @GetMapping("/userInfo")
    public String userInfo(Model model, @AuthenticationPrincipal SessionUser user) {

        System.out.println("user =====" + user);
        model.addAttribute("user", user);

        return "members/userInfo";
    }

    // 비밀번호 찾기 페이지로 이동
    @GetMapping("/find/password")
    public String findPWForm(Model model) {

        model.addAttribute("userForm", new UserForm());
        return "members/findPW";
    }

    // 비밀번호 찾기
    @PostMapping("/find/password")
    public String findPW(@Validated @ModelAttribute("userForm") UserForm userForm, BindingResult result)
            throws MessagingException, UnsupportedEncodingException {

        System.out.println("========= findPW Logic START !!! ================");

        User user = userService.findByEmail(userForm.getEmail()).orElse(null);

        System.out.println("======= FIND USER !! =====");
        System.out.println(user);

        if (user == null) {
            System.out.println("====== USER is NULL !!! =======");
            result.reject("wrong", null, null);

            return "members/findPW";
        }

        userService.sendMail(userService.setPWMessage(user));

        return "redirect:/";
    }

    // 회원 정보 수정 페이지 이동
    @GetMapping("/userInfo/update")
    public String updateUserInformationForm(Model model, @AuthenticationPrincipal SessionUser user) {

        model.addAttribute("userForm", new UserForm());
        model.addAttribute("user", user);

        return "members/updateInfo";
    }

    @PostMapping("/userInfo/update")
    public String updateUserInformation(@Validated @ModelAttribute("userForm") UserForm userForm, BindingResult result) {

        User user = userService.findByEmail(userForm.getEmail()).orElse(null);

        userService.updateUserInformation(user, userForm.getName(), userForm.getAuth_email(), userForm.getPassword());

        return "redirect:/";
    }
}
