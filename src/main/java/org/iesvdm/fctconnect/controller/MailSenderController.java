package org.iesvdm.fctconnect.controller;

import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.iesvdm.fctconnect.domain.*;
import org.iesvdm.fctconnect.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
// @CrossOrigin(origins = "https://fctconnect.vercel.app")
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/v1/api/mail")
public class MailSenderController {
    @Autowired
    private MailSenderService mailSenderService;

    @PostMapping("/send-mail")
    public MensajeRespuesta sendMail(@RequestBody RegisterRequestEmpresa registerRequestEmpresa) {

        this.mailSenderService.notificarPorEmail(registerRequestEmpresa);
        return new MensajeRespuesta("Mensaje Enviado!");
    }

}