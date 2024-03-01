package org.iesvdm.fctconnect.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.fctconnect.domain.Profesor;
import org.iesvdm.fctconnect.service.ProfesorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/v1/api/profesores")
public class ProfesorController {
    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }


    @GetMapping(value = {"", "/"})
    public List<Profesor> all() {
        log.info("Accediendo a todos los profesores");
        return this.profesorService.all();
    }

    @PostMapping({"", "/"})
    public Profesor newProfesor(@RequestBody Profesor profesor) {
        return this.profesorService.save(profesor);
    }

    @GetMapping("/{id}")
    public Profesor one(@PathVariable("id") Long id) {
        return this.profesorService.one(id);
    }

    @PutMapping("/{id}")
    public Profesor replaceProfesor(@PathVariable("id") Long id, @RequestBody Profesor profesor) {
        return this.profesorService.replace(id, profesor);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteProfesor(@PathVariable("id") Long id) {
        this.profesorService.delete(id);
    }
}
