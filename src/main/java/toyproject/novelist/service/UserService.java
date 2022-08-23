package toyproject.novelist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.novelist.config.auth.mail.MailHandler;
import toyproject.novelist.domain.user.User;
import toyproject.novelist.dto.MailForm;
import toyproject.novelist.repository.UserRepository;

import java.util.Optional;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private MailHandler mailHandler;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User duplicateName(String name) {

        return userRepository.findByName(name).orElse(null);
    }

    public User duplicateEmail(String email) {

        return userRepository.findByEmail(email).orElse(null);
    }

    public void join(User user) {

        userRepository.save(user);
    }

    public MailForm setPWMessage(User user) {
        MailForm mail = new MailForm();
        int leftLimit = 48;
        int rightLimit = 90;
        int targetLength = 8;
        Random random = new Random();
        String password = random.ints(leftLimit, rightLimit + 1)
                .filter(n -> (n <= 57 || n >= 65))
                .limit(targetLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        changePassword(user, password);

        mail.setAddress(user.getAuth_email());
        mail.setTitle("NOVELIST 임시 비밀번호 안내 메일입니다.");
        mail.setMessage("안녕하세요. " + user.getName() + "님의 임시 비밀번호 안내 메일입니다. "
                + user.getName() + "님의 임시 비밀번호는 " + password + "입니다.");

        return mail;
    }

    public void changePassword(User user, String password) {

        user.changePW(password);
        userRepository.save(user);
    }
}
