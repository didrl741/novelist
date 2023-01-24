package toyproject.novelist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.novelist.domain.user.Member;
import toyproject.novelist.dto.MailForm;
import toyproject.novelist.repository.UserRepository;

import javax.mail.MessagingException;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final JavaMailSender javaMailSender;

    public Optional<Member> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Member duplicateName(String name) {

        return userRepository.findByName(name).orElse(null);
    }

    public Member duplicateEmail(String email) {

        return userRepository.findByEmail(email).orElse(null);
    }

    public void join(Member member) {

        userRepository.save(member);
    }

    public MailForm setPWMessage(Member member) {
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

        changePassword(member, password);

        mail.setAddress(member.getAuth_email());
        mail.setTitle("NOVELIST 임시 비밀번호 안내 메일입니다.");
        mail.setMessage("안녕하세요. " + member.getName() + "님의 임시 비밀번호 안내 메일입니다. "
                + member.getName() + "님의 임시 비밀번호는 " + password + "입니다.");

        return mail;
    }

    public void changePassword(Member member, String password) {

        member.changePW(password);
        userRepository.save(member);
    }

    public void updateUserInformation(Member member, String name, String auth_mail, String password) {

        if (name != null) {
            member.update(name);
        }

        if (auth_mail != null) {
            member.changeAuth_Mail(auth_mail);
        }

        if (password != null) {
            member.changePW(password);
        }

        System.out.println("member1234 ===== " + member.toString()); // 변경된 member로 잘 출력 된다.

        userRepository.save(member);
    }

    @Async
    public void sendMail(MailForm mailForm) throws MessagingException {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailForm.getAddress());
        message.setSubject(mailForm.getTitle());
        message.setText(mailForm.getMessage());
        javaMailSender.send(message);

        System.out.println("========= Mail !!! ============");

    }
}
