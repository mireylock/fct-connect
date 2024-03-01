package org.iesvdm.fctconnect.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.fctconnect.domain.Empresa;
import org.iesvdm.fctconnect.service.EmpresaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/v1/api/empresas")
public class EmpresaController {
    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }


    @GetMapping(value = {"", "/"})
    public List<Empresa> all() {
        log.info("Accediendo a todos los empresas");
        return this.empresaService.all();
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