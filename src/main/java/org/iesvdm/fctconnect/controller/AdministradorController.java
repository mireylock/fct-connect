package org.iesvdm.fctconnect.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.fctconnect.domain.Administrador;
import org.iesvdm.fctconnect.service.AdministradorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/v1/api/administradores")
public class AdministradorController {
    private final AdministradorService administradorService;

    public AdministradorController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    @GetMapping(value = {"", "/"})
    public List<Administrador> all() {
        log.info("Accediendo a todos los administradores");
        return this.administradorService.all();
    }


    @PostMapping({"", "/"})
    public Administrador newAdministrador(@RequestBody Administrador administrador) {
        return this.administradorService.save(administrador);
    }

    @GetMapping("/{id}")
    public Administrador one(@PathVariable("id") Long id) {
        return this.administradorService.one(id);
    }

    @PutMapping("/{id}")
    public Administrador replaceAdministrador(@PathVariable("id") Long id, @RequestBody Administrador administrador) {
        return this.administradorService.replace(id, administrador);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteAdministrador(@PathVariable("id") Long id) {
        this.administradorService.delete(id);
    }
}

