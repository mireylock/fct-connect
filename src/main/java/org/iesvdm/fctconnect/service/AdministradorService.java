package org.iesvdm.fctconnect.service;

import org.iesvdm.fctconnect.domain.Administrador;
import org.iesvdm.fctconnect.exception.EntityNotFoundException;
import org.iesvdm.fctconnect.repository.AdministradorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorService {
    private final AdministradorRepository administradorRepository;

    public AdministradorService(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    public List<Administrador> all() {
        return this.administradorRepository.findAll();
    }

    public Administrador save(Administrador administrador) {
        return this.administradorRepository.save(administrador);
    }

    public Administrador one(Long id) {
        return this.administradorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Administrador.class));
    }

    public Administrador replace(Long id, Administrador administrador) {
        return this.administradorRepository.findById(id)
                .map(c -> {
                    administrador.setId(id);
                    return this.administradorRepository.save(administrador);
                })
                .orElseThrow(() -> new EntityNotFoundException(id, Administrador.class));
    }

    public void delete(Long id) {
        this.administradorRepository.findById(id).map(p -> {
                    this.administradorRepository.delete(p);
                    return p;
                })
                .orElseThrow(() -> new EntityNotFoundException(id, Administrador.class));
    }
}
