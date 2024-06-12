package org.iesvdm.fctconnect.controller;

import org.iesvdm.fctconnect.domain.Alumno;
import org.iesvdm.fctconnect.domain.Usuario;
import org.iesvdm.fctconnect.domain.dto.UsuarioDTO;
import org.iesvdm.fctconnect.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "https://fctconnect.vercel.app/")
@RequestMapping("/v1/api/users")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}")
    public Boolean getActivacionUsuario(@PathVariable("id") Long id) {
        return this.usuarioService.estadoActivacionUsuario(id);
    }

    @PutMapping("/{id}")
    public Usuario inactivateUser(@PathVariable("id") Long id, @RequestBody UsuarioDTO usuarioDTO) {
        return this.usuarioService.inactivateUser(id, usuarioDTO);
    }
}
