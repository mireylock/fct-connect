package org.iesvdm.fctconnect.controller;


import org.iesvdm.fctconnect.domain.Tecnologia;
import org.iesvdm.fctconnect.domain.dto.TecnologiaDTO;
import org.iesvdm.fctconnect.service.TecnologiaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "https://fctconnect.vercel.app/")
@RequestMapping("/v1/api/tecnologias")
public class TecnologiaController {
    private final TecnologiaService tecnologiaService;

    public TecnologiaController(TecnologiaService tecnologiaService) {
        this.tecnologiaService = tecnologiaService;
    }

    @PostMapping({"", "/"})
    public Tecnologia newTecnologia(@RequestBody TecnologiaDTO tecnologiaDTO) {
        return this.tecnologiaService.save(tecnologiaDTO);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteTecnologia(@PathVariable("id") Long id) {
        this.tecnologiaService.delete(id);
    }
}
