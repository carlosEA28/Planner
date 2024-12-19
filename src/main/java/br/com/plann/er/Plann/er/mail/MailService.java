package br.com.plann.er.Plann.er.mail;

import br.com.plann.er.Plann.er.dto.ViagemDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashSet;
import java.util.Set;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;


    @Async
    public void sendConfirmationMail(String to, ViagemDto dto, String confirmationLink) throws MessagingException {

        Context context = new Context();
        context.setVariable("city", dto.city());
        context.setVariable("country", dto.country());
        context.setVariable("departureDate", dto.departureDate());
        context.setVariable("returnDate", dto.returnDate());
        context.setVariable("confirmationLink", confirmationLink);

        String htmlContent = this.templateEngine.process("EmailConvite", context);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(new String[]{to});
        helper.setSubject("Convite para Viagem");
        helper.setText(htmlContent, true);

        javaMailSender.send(message);

//        String confirmationLink COLOCAR DPS NOS PARAMETROS DA FUNC
    }
}
