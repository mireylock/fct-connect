package org.iesvdm.fctconnect.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.fctconnect.domain.Alumno;
import org.iesvdm.fctconnect.service.AlumnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/v1/api/alumnos")
public class AlumnoController {
    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }


    @GetMapping(value = {"", "/"})
    public List<Alumno> all() {
        log.info("Accediendo a todos los alumnos");
        return this.alumnoService.all();
    }

    @PostMapping({"", "/"})
    public Alumno newAlumno(@RequestBody Alumno alumno) {
        return this.alumnoService.save(alumno);
    }

    @GetMapping("/{id}")
    public Alumno one(@PathVariable("id") Long id) {
        return this.alumnoService.one(id);
    }

    @PutMapping("/{id}")
    public Alumno replaceAlumno(@PathVariable("id") Long id, @RequestBody Alumno alumno) {
        return this.alumnoService.replace(id, alumno);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteAlumno(@PathVariable("id") Long id) {
        this.alumnoService.delete(id);
    }

//    @GetMapping(value = {"","/"}, params = {"!orden", "!paginado", "!buscarOrdenDosCampos"})
//    public List<Alumno> all() {
//        log.info("Accediendo a todas las películas");
//        return this.alumnoService.all();
//    }
//
//    //Para rutas http://localhost:8080/alumnos?orden=campo1,sentido1
//    @GetMapping(value = {"", "/"}, params = {"!paginado", "!buscarOrdenDosCampos"})
//    public  List<Alumno> all (String[] orden) {
//        log.info("Accediendo a todas las películas con filtro buscarOrder");
//        return this.alumnoService.all(orden);
//    }
//
//    //Para rutas: http://localhost:8080/alumnos?ordenDosCampos=titulo,asc&buscarOrdenDosCampos=descripcion,desc
//    @GetMapping(value = {"", "/"}, params = {"!paginado"})
//    public  List<Alumno> allBuscaDosCampos (String[] buscarOrdenDosCampos) {
//        log.info("Accediendo a todas las películas con filtro buscarOrdenDosCampos");
//        return this.alumnoService.allBuscaDosCampos(buscarOrdenDosCampos);
//    }
//
//    //Para rutas http://localhost:8080/alumnos?paginado=0,1
//    @GetMapping(value = {"", "/"})
//    public ResponseEntity<Map<String, Object>> allPag (@RequestParam(value="paginado", defaultValue = "0") String[] paginado) {
//        log.info("Accediendo a películas con paginación");
//        Map<String, Object> responseAll = this.alumnoService.allPag(paginado);
//        return ResponseEntity.ok(responseAll);
//    }

}



