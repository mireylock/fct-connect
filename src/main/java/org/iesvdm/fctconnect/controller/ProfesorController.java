package org.iesvdm.fctconnect.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.fctconnect.domain.Profesor;
import org.iesvdm.fctconnect.domain.dto.ProfesorDTO;
import org.iesvdm.fctconnect.domain.enums.EInglesSolicitado;
import org.iesvdm.fctconnect.domain.enums.EModalidadTrabajo;
import org.iesvdm.fctconnect.service.ProfesorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/v1/api/profesores")
public class ProfesorController {
    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping(value = {"", "/"}, params = {"!nombre"})
    public List<Profesor> allActivos() {
        log.info("Accediendo a todos los profesores activos");
        return this.profesorService.allActivos();
    }

    @GetMapping(value = {"", "/"})
    public Map<String, Object> buscarProfesorPaginacion(String nombre,
                                                        Optional<Integer> pagina,
                                                        Optional<Integer> tamanio) {
        log.info("Accediendo a profesores con filtros");
        return this.profesorService.buscarProfesorPaginacion(nombre, pagina, tamanio);
    }

    @GetMapping(value = { "/inactivos"})
    public Map<String, Object> buscarProfesorInactivosPaginacion(String nombre,
                                                        Optional<Integer> pagina,
                                                        Optional<Integer> tamanio) {
        log.info("Accediendo a profesores INACTIVOS con filtros");
        return this.profesorService.buscarProfesorInactivoPaginacion(nombre, pagina, tamanio);
    }


    @GetMapping("/{id}")
    public Profesor one(@PathVariable("id") Long id) {
        return this.profesorService.one(id);
    }

    @PutMapping("/{id}")
    public Profesor replaceProfesor(@PathVariable("id") Long id, @RequestBody ProfesorDTO profesorDTO) {
        return this.profesorService.replace(id, profesorDTO);
    }

}
