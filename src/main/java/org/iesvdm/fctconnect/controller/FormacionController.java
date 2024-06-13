package org.iesvdm.fctconnect.controller;

import org.iesvdm.fctconnect.domain.Formacion;
import org.iesvdm.fctconnect.service.FormacionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
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

    @PostMapping(value = {"", "/"})
    public Formacion post(@RequestBody Formacion formacion) {
        return this.formacionService.save(formacion);
    }
}
