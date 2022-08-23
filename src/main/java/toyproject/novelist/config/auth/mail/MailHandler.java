package toyproject.novelist.config.auth.mail;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

public class MailHandler {

    private JavaMailSender javaMailSender;
    private MimeMessage message;
    private MimeMessageHelper messageHelper;

    public MailHandler(JavaMailSender javaMailSender) throws MessagingException {

        this.javaMailSender = javaMailSender;
        message = javaMailSender.createMimeMessage();
        messageHelper = new MimeMessageHelper(message, true, "UTF-8");
    }

    public void setFrom(String email, String name) throws UnsupportedEncodingException, MessagingException {
        messageHelper.setFrom(email, name);
    }

    public void setTo(String email) throws MessagingException {
        messageHelper.setTo(email);
    }

    public void setSubject(String subject) throws MessagingException {
        messageHelper.setSubject(subject);
    }

    public void setText(String text) throws MessagingException {
        messageHelper.setText(text, true);
    }

    public void send() {
        try {
            javaMailSender.send(message);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
