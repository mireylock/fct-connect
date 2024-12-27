package org.iesvdm.fctconnect.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.fctconnect.domain.Alumno;
import org.iesvdm.fctconnect.domain.AlumnoHablaIdioma;
import org.iesvdm.fctconnect.domain.Idioma;
import org.iesvdm.fctconnect.domain.dto.AlumnoHablaIdiomaDTO;
import org.iesvdm.fctconnect.service.AlumnoService;
import org.iesvdm.fctconnect.service.IdiomaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
// @CrossOrigin(origins = "https://fctconnect.vercel.app")
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/v1/api/idiomas")
public class IdiomaController {
    private final IdiomaService idiomaService;

    public IdiomaController(IdiomaService idiomaService) {
        this.idiomaService = idiomaService;
    }

    // IDIOMAS DEL ALUMNO
    @PostMapping({ "", "/" })
    public AlumnoHablaIdioma newALumnoIdioma(@RequestBody AlumnoHablaIdiomaDTO alumnoHablaIdiomaDTO) {
        return idiomaService.saveAlumnoHablaIdioma(alumnoHablaIdiomaDTO);
    }

    @DeleteMapping({ "/aluIdioma/{id}" })
    public void deleteAlumnoHablaIdioma(@PathVariable("id") Long id) {
        idiomaService.deleteAlumnoHablaIdioma(id);
    }

    // IDIOMAS
    @GetMapping(value = { "", "/" })
    public List<Idioma> all() {
        log.info("Accediendo a todos los idiomas");
        return this.idiomaService.all();
    }

    @PostMapping({ "/idioma" })
    public Idioma newIdioma(@RequestBody Idioma idioma) {
        return this.idiomaService.save(idioma);
    }

    @GetMapping("/idioma/{id}")
    public Idioma one(@PathVariable("id") Long id) {
        return this.idiomaService.one(id);
    }

    @PutMapping("/idioma/{id}")
    public Idioma replaceIdioma(@PathVariable("id") Long id, @RequestBody Idioma idioma) {
        return this.idiomaService.replace(id, idioma);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/idioma/{id}")
    public void deleteIdioma(@PathVariable("id") Long id) {
        this.idiomaService.delete(id);
    }
}
