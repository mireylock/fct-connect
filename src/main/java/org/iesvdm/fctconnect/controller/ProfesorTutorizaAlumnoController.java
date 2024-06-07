package org.iesvdm.fctconnect.controller;

import org.iesvdm.fctconnect.domain.Profesor;
import org.iesvdm.fctconnect.domain.ProfesorTutorizaAlumno;
import org.iesvdm.fctconnect.domain.dto.ProfesorTutorizaAlumnoDTO;
import org.iesvdm.fctconnect.service.ProfesorTutorizaAlumnoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/v1/api/tutorias")
public class ProfesorTutorizaAlumnoController {

    private final ProfesorTutorizaAlumnoService profesorTutorizaAlumnoService;

    public ProfesorTutorizaAlumnoController(ProfesorTutorizaAlumnoService profesorTutorizaAlumnoService) {
        this.profesorTutorizaAlumnoService = profesorTutorizaAlumnoService;
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
