package org.iesvdm.fctconnect.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.fctconnect.domain.Solicitud;
import org.iesvdm.fctconnect.domain.dto.SolicitudCrearDTO;
import org.iesvdm.fctconnect.domain.dto.SolicitudEstadoDTO;
import org.iesvdm.fctconnect.service.SolicitudService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "https://fctconnect.vercel.app/")
@RequestMapping("/v1/api/solicitudes")
public class SolicitudController {
    private final SolicitudService solicitudService;

    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @GetMapping(value = { "/alu"})
    public List<Solicitud> allSolicitudesAlumno(String estado, String tipo, long id) {
        return this.solicitudService.allAlumno(estado, tipo, id);
    }

    @GetMapping(value = { "/emp"})
    public List<Solicitud> allSolicitudesEmpresa(String estado, String tipo, long id) {
        return this.solicitudService.allEmpresa(estado, tipo, id);
    }

    @PutMapping("/{id}")
    public Solicitud replaceSolicitud(@PathVariable("id") Long id, @RequestBody SolicitudEstadoDTO solicitudEstadoDTO) {
        return this.solicitudService.replace(id, solicitudEstadoDTO);
    }


    @PostMapping({"", "/"})
    public Solicitud newSolicitud(@RequestBody SolicitudCrearDTO solicitudcrearDTO) {
        return this.solicitudService.save(solicitudcrearDTO);
    }

    @GetMapping("/{id}")
    public Solicitud one(@PathVariable("id") Long id) {
        return this.solicitudService.one(id);
    }



    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteSolicitud(@PathVariable("id") Long id) {
        this.solicitudService.delete(id);
    }
}
