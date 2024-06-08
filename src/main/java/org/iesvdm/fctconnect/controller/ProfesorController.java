package org.iesvdm.fctconnect.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.fctconnect.domain.Profesor;
import org.iesvdm.fctconnect.service.ProfesorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/v1/api/profesores")
public class ProfesorController {
    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping(value = {"", "/"}, params = {"!buscar-por-nombre"})
    public List<Profesor> allActivos() {
        log.info("Accediendo a todos los profesores activos");
        return this.profesorService.allActivos();
    }

    @GetMapping(value = {"/inactivos"}, params = {"!buscar-por-nombre"})
    public List<Profesor> allInactivos() {
        log.info("Accediendo a todos los profesores inactivos");
        return this.profesorService.allInactivos();
    }

    @GetMapping(value = {"","/"})
    public Page<Profesor> filtradoPorNombreConPaginacion(@RequestParam("buscar-por-nombre") String buscarPorNombre, Pageable pageable) {
        log.info("Accediendo a todas las categorías por buscar-por-nombre:" + buscarPorNombre);
        return this.profesorService.filtradoPorNombreConPaginacion(Optional.of(buscarPorNombre), pageable);
    }

//    @GetMapping(value = "/alu/{idAlumno}")
//    public List<Profesor> profesorDeUnAlumno (@PathVariable("idAlumno") Long idAlumno) {
//        log.info("Accediendo al profesor del alumno con id "+idAlumno);
//        return this.profesorService.profesoresDeUnAlumno(idAlumno);
//    }

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
