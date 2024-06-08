package org.iesvdm.fctconnect.service;

import org.iesvdm.fctconnect.domain.Alumno;
import org.iesvdm.fctconnect.domain.Idioma;
import org.iesvdm.fctconnect.domain.Usuario;
import org.iesvdm.fctconnect.domain.dto.UsuarioDTO;
import org.iesvdm.fctconnect.exception.EntityNotFoundException;
import org.iesvdm.fctconnect.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Boolean estadoActivacionUsuario(Long id) {
        Optional<Usuario> user = this.usuarioRepository.findById(id);
        if (user.isPresent()) {
            return user.get().getActivo();
        } else {
            throw new EntityNotFoundException (id, Usuario.class);
        }
    }

    public Usuario inactivateUser(Long id, UsuarioDTO usuarioDTO) {
        return this.usuarioRepository.findById(id)
                .map(user -> {
                    user.setActivo(usuarioDTO.getActivo());
                    return this.usuarioRepository.save(user);
                })
                .orElseThrow(() -> new EntityNotFoundException(id, Usuario.class));
    }
}
