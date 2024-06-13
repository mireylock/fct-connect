package org.iesvdm.fctconnect.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.fctconnect.domain.Alumno;
import org.iesvdm.fctconnect.domain.Profesor;
import org.iesvdm.fctconnect.domain.ProfesorTutorizaAlumno;
import org.iesvdm.fctconnect.domain.dto.ProfesorTutorizaAlumnoDTO;
import org.iesvdm.fctconnect.service.ProfesorTutorizaAlumnoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/api/tutorias")
public class ProfesorTutorizaAlumnoController {

    private final ProfesorTutorizaAlumnoService profesorTutorizaAlumnoService;

    public ProfesorTutorizaAlumnoController(ProfesorTutorizaAlumnoService profesorTutorizaAlumnoService) {
        this.profesorTutorizaAlumnoService = profesorTutorizaAlumnoService;
    }

    @GetMapping("/{idProfesor}")
    public List<Alumno> allFromProfesor(@PathVariable Long idProfesor) {
        log.info("Accediendo a todas las tutorias");
        return this.profesorTutorizaAlumnoService.allFromProfesor(idProfesor);
    }

    @GetMapping(value = {"/search/{idProfesor}"})
    public Map<String, Object> buscarAlumnosTutoriaPaginacion(@PathVariable Long idProfesor,
                                                              String nombre,
                                                        Optional<Integer> pagina,
                                                        Optional<Integer> tamanio) {
        log.info("Accediendo a tutorias con filtros");
        return this.profesorTutorizaAlumnoService.buscarAlumnosTutoriaPaginacion(idProfesor, nombre, pagina, tamanio);
    }


    @PostMapping({"", "/"})
    public ProfesorTutorizaAlumno newProfesorTutorizaAlumno(@RequestBody ProfesorTutorizaAlumnoDTO profesorTutorizaAlumnoDTO) {
        return this.profesorTutorizaAlumnoService.save(profesorTutorizaAlumnoDTO);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteProfesorTutorizaAlumno(@PathVariable("id") Long id) {
        this.profesorTutorizaAlumnoService.delete(id);
    }

}
