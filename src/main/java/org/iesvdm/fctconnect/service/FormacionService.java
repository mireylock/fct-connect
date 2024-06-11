package org.iesvdm.fctconnect.service;

import org.iesvdm.fctconnect.domain.Formacion;
import org.iesvdm.fctconnect.repository.FormacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormacionService {
    private final FormacionRepository  formacionRepository;

    public FormacionService(FormacionRepository formacionRepository) {
        this.formacionRepository = formacionRepository;
    }

    public List<Formacion> getAll() {
       return this.formacionRepository.findAll();
    }

    public Formacion save(Formacion formacion) {
        return this.formacionRepository.save(formacion);
    }
}
