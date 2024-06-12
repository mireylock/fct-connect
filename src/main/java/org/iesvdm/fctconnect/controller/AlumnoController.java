package org.iesvdm.fctconnect.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.fctconnect.domain.Alumno;
import org.iesvdm.fctconnect.domain.dto.AlumnoDTO;
import org.iesvdm.fctconnect.domain.dto.UsuarioDTO;
import org.iesvdm.fctconnect.service.AlumnoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "https://fctconnect.vercel.app")
@RequestMapping("/v1/api/alumnos")
public class AlumnoController {
    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping(value = {"", "/"}, params = {"!pagina", "!tamanio", "!nombre", "!vehiculoPropio", "!idioma"})
    public List<Alumno> allActivos() {
        log.info("Accediendo a todos los alumnos");
        return this.alumnoService.allActivos();
    }

    @GetMapping(value = {"", "/"})
    public Map<String, Object> buscarAlumnosPaginacion(Optional<String> nombre,
                                                       Optional<Boolean> vehiculoPropio,
                                                       Optional<Long> idioma,
                                                       Optional<Integer> pagina,
                                                       Optional<Integer> tamanio) {
        log.info("Accediendo a alumnos con filtros");
        return this.alumnoService.buscarAlumnoPaginacion(nombre, vehiculoPropio, idioma, pagina, tamanio);
    }

    @GetMapping(value = {"/inactivos"})
    public Map<String, Object> buscarAlumnosInactivosPaginacion(Optional<String> nombre,
                                                       Optional<Boolean> vehiculoPropio,
                                                       Optional<Long> idioma,
                                                       Optional<Integer> pagina,
                                                       Optional<Integer> tamanio) {
        log.info("Accediendo a alumnos INACTIVOS con filtros");
        return this.alumnoService.buscarAlumnoInactivoPaginacion(nombre, vehiculoPropio, idioma, pagina, tamanio);
    }

    @GetMapping("/{id}")
    public Alumno one(@PathVariable("id") Long id) {
        return this.alumnoService.one(id);
    }


    @PutMapping("/{id}")
    public Alumno replaceAlumno(@PathVariable("id") Long id, @RequestBody AlumnoDTO alumnoDTO) {
        return this.alumnoService.replaceAlumno(id, alumnoDTO);
    }

}



