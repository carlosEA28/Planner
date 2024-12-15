package br.com.plann.er.Plann.er.domains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String email, String subject, String body) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setFrom("app_login@gmail.com");
        mail.setSubject(subject);
        mail.setText(body);

        javaMailSender.send(mail);
    }
}
