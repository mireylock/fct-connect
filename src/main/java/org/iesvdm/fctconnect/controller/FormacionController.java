package org.iesvdm.fctconnect.controller;

import org.iesvdm.fctconnect.domain.Formacion;
import org.iesvdm.fctconnect.service.FormacionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/v1/api/formaciones")
public class FormacionController {
    private final FormacionService formacionService;

    public FormacionController(FormacionService formacionService) {
        this.formacionService = formacionService;
    }

    @GetMapping(value = {"", "/"})
    public List<Formacion> getAll() {
        return this.formacionService.getAll();
    }
}
