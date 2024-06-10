package org.iesvdm.fctconnect.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.fctconnect.domain.Empresa;
import org.iesvdm.fctconnect.domain.dto.EmpresaDTO;
import org.iesvdm.fctconnect.domain.enums.EInglesSolicitado;
import org.iesvdm.fctconnect.domain.enums.EModalidadTrabajo;
import org.iesvdm.fctconnect.service.EmpresaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/v1/api/empresas")
public class EmpresaController {
    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }


    @GetMapping(value = {"", "/"}, params = {"!nombre", "!modalidadTrabajo", "!inglesSolicitado", "!tecnologia", "!pagina", "!tamanio"})
    public List<Empresa> allActivas() {
        log.info("Accediendo a todas las empresas activas");
        return this.empresaService.allActivos();
    }


//    @GetMapping(value = {"", "/"}, params = {"!nombre", "!modalidadTrabajo", "!inglesSolicitado", "!tecnologia"})
//    public Map<String, Object> all(int pagina, int tamanio) {
//        log.info("Accediendo a empresas con paginacion");
//        return this.empresaService.all(pagina, tamanio);
//    }
//
//    @GetMapping(value = { "/inactivas"}, params = {"!nombre", "!modalidadTrabajo", "!inglesSolicitado", "!tecnologia"})
//    public Map<String, Object> allInactivas(int pagina, int tamanio) {
//        log.info("Accediendo a empresas INACTIVAS con paginacion");
//        return this.empresaService.allInactivos(pagina, tamanio);
//    }

    @GetMapping(value = {"", "/"})
    public Map<String, Object> buscarEmpresaPaginacion (String nombre,
                                                        EModalidadTrabajo modalidadTrabajo,
                                                        EInglesSolicitado inglesSolicitado,
                                                        String tecnologia,
                                                        Optional<Integer> pagina,
                                                        Optional<Integer> tamanio) {
        log.info("Accediendo a empresas con filtros");
        return this.empresaService.buscarEmpresaPaginacion(nombre, inglesSolicitado, modalidadTrabajo, tecnologia, pagina, tamanio);
    }

    @GetMapping(value = {"/inactivas"})
    public Map<String, Object> buscarEmpresaInactivasPaginacion (String nombre,
                                                        EModalidadTrabajo modalidadTrabajo,
                                                        EInglesSolicitado inglesSolicitado,
                                                        String tecnologia,
                                                        Optional<Integer> pagina,
                                                        Optional<Integer> tamanio) {
        log.info("Accediendo a empresas INACTIVAS con filtros");
        return this.empresaService.buscarEmpresaInactivasPaginacion(nombre, inglesSolicitado, modalidadTrabajo, tecnologia, pagina, tamanio);
    }

    @PostMapping({"", "/"})
    public Empresa newEmpresa(@RequestBody Empresa empresa) {
        return this.empresaService.save(empresa);
    }

    @GetMapping("/{id}")
    public Empresa one(@PathVariable("id") Long id) {
        return this.empresaService.one(id);
    }

    @PutMapping("/{id}")
    public Empresa replaceEmpresa(@PathVariable("id") Long id, @RequestBody EmpresaDTO empresaDTO) {
        return this.empresaService.replace(id, empresaDTO);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteEmpresa(@PathVariable("id") Long id) {
        this.empresaService.delete(id);
    }
}