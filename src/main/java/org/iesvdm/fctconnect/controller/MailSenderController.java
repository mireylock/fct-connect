package org.iesvdm.fctconnect.controller;

import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.iesvdm.fctconnect.domain.*;
import org.iesvdm.fctconnect.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/v1/api/mail")
public class MailSenderController {
    @Autowired
    private MailSenderService mailSenderService;

    @PostMapping("/send-mail")
    public MensajeRespuesta sendMail(@RequestBody Alumno alummno) {

        this.mailSenderService.notificarPorEmail(alummno);
        return new MensajeRespuesta("Mensaje Enviado!");
    }

//    @PostMapping("/send-mail")
//    public MensajeRespuesta sendMail(@RequestParam("from") String from,
//                                      @RequestParam("to") String to,
//                                      @RequestParam("subject") String subject,
//                                      @RequestParam("text") String text) throws MessagingException {
//
//        this.mailSenderService.sendWithoutAttach(from, to, subject, text);
//        return new MensajeRespuesta("Mensaje Enviado!");
//    }




}