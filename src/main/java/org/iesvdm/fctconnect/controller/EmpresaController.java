package org.iesvdm.fctconnect.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.fctconnect.domain.Empresa;
import org.iesvdm.fctconnect.domain.enums.EInglesSolicitado;
import org.iesvdm.fctconnect.domain.enums.EModalidadTrabajo;
import org.iesvdm.fctconnect.service.EmpresaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<Empresa> all() {
        log.info("Accediendo a todas las empresas");
        return this.empresaService.all();
    }

    @GetMapping(value = {"", "/"}, params = {"!nombre", "!modalidadTrabajo", "!inglesSolicitado", "!tecnologia"})
    public Map<String, Object> all(int pagina, int tamanio) {
        log.info("Accediendo a empresas con paginacion");
        return this.empresaService.all(pagina, tamanio);
    }

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

    // http://localhost:8080/v1/api/empresas?nombre=nombre&modalidadTrabajo=online&inglesSolicitado=importante&order=asc&pagina=0&tamanio=2
//    @GetMapping(value = {"", "/"})
//    public Map<String, Object> buscarEmpresaPaginacion (Optional<String> nombre,
//                                                        Optional<String> modalidadTrabajo,
//                                                        Optional<String> inglesSolicitado,
//                                                        Optional<String> order,
//                                                        Optional<Integer> pagina,
//                                                        Optional<Integer> tamanio) {
//        log.info("Accediendo a empresas con filtros");
//        return this.empresaService.buscarEmpresaPaginacion(nombre, modalidadTrabajo, inglesSolicitado, order, pagina, tamanio);
//    }



    // Búsquedas por nombre por ingles+modalidad de trabajo y paginación

//    @GetMapping(value = {"", "/"}, params = {"!inglesSolicitado", "!modalidadTrabajo", "!buscar", "!order", "!paginacion"})
//    public List<Empresa> all() {
//        log.info("Accediendo a todas las empresas");
//        return this.empresaService.all();
//    }

    // http://localhost:8080/v1/api/empresas?buscar=nombre&order=desc
//    @GetMapping(value = {"", "/"}, params = {"!inglesSolicitado", "!modalidadTrabajo", "!paginacion"})
//    public List<Empresa> buscarPorNombre (Optional<String> buscar, Optional<String> order) {
//        log.info("Accediendo a todas las categorías con filtro buscar por nombre y ordenar");
//        return this.empresaService.buscarPorNombre(buscar, order);
//    }

    // http://localhost:8080/v1/api/empresas?inglesSolicitado=ingles&modalidadTrabajo=modalidad
    // http://localhost:8080/v1/api/empresas?inglesSolicitado=ingles
    // http://localhost:8080/v1/api/empresas?modalidadTrabajo=modalidad
//    @GetMapping(value = {"", "/"}, params = {"!buscar", "!order", "!paginacion"})
//    public List<Empresa> buscarPorInglesYModalidad (Optional<String> inglesSolicitado, Optional<String> modalidadTrabajo) {
//        log.info("Accediendo a todas las categorías con filtro por inglés solicitado y modalidad de trabajo");
//        return this.empresaService.buscarPorInglesYModalidad(inglesSolicitado, modalidadTrabajo);
//    }

    // http://localhost:8080/v1/api/empresas?paginacion=0,1
//    @GetMapping(value = {"", "/"})
//    public ResponseEntity<Map<String, Object>> paginacion (@RequestParam(value="paginacion", defaultValue = "0") String[] paginado) {
//        log.info("Accediendo a empresas con paginación");
//        Map<String, Object> responseAll = this.empresaService.paginacion(paginado);
//        return ResponseEntity.ok(responseAll);
//    }

    @PostMapping({"", "/"})
    public Empresa newEmpresa(@RequestBody Empresa empresa) {
        return this.empresaService.save(empresa);
    }

    @GetMapping("/{id}")
    public Empresa one(@PathVariable("id") Long id) {
        return this.empresaService.one(id);
    }

    @PutMapping("/{id}")
    public Empresa replaceEmpresa(@PathVariable("id") Long id, @RequestBody Empresa empresa) {
        return this.empresaService.replace(id, empresa);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteEmpresa(@PathVariable("id") Long id) {
        this.empresaService.delete(id);
    }
}