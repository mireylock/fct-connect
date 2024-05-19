package org.iesvdm.fctconnect.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.iesvdm.fctconnect.domain.Alumno;
import org.iesvdm.fctconnect.domain.RegisterRequestEmpresa;
import org.iesvdm.fctconnect.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import org.thymeleaf.spring6.SpringTemplateEngine;


@Service
public class MailSenderService {

    @Value("${spring.mail.username}")
    private String emailSender;

    @Value("${app.ATTACH_PATH}")
    private String ATTACH_PATH;

    @Autowired
    private SpringTemplateEngine thymeleafTemplateEngine;

    private JavaMailSender mailSender;

    public MailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(String from, String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }


    public void sendWithoutAttach(String from, String to, String subject,
                                  String text) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);

        mailSender.send(message);
    }

    @Async
    public void notificarPorEmail(RegisterRequestEmpresa requestEmpresa) {

        this.send(emailSender,
                requestEmpresa.getEmail(),
                "Bienvenido a FCT Connect",
                "¡Bienvenid@, "+requestEmpresa.getNombre()+"!" +
                        "\nNos complace informarte de que tu registro en FCT Connect ha sido exitoso"
        );
    }
}