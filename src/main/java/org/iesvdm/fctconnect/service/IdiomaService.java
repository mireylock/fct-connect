package org.iesvdm.fctconnect.service;

import org.iesvdm.fctconnect.domain.Idioma;
import org.iesvdm.fctconnect.exception.EntityNotFoundException;
import org.iesvdm.fctconnect.repository.IdiomaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdiomaService {
    private final IdiomaRepository idiomaRepository;

    public IdiomaService(IdiomaRepository idiomaRepository) {
        this.idiomaRepository = idiomaRepository;
    }

    public List<Idioma> all() {
        return this.idiomaRepository.findAll();
    }

    public Idioma save(Idioma idioma) {
        return this.idiomaRepository.save(idioma);
    }

    public Idioma one(Long id) {
        return this.idiomaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Idioma.class));
    }

    public Idioma replace(Long id, Idioma idioma) {
        return this.idiomaRepository.findById(id)
                .map(c -> {
                    idioma.setId(id);
                    return this.idiomaRepository.save(idioma);
                })
                .orElseThrow(() -> new EntityNotFoundException(id, Idioma.class));
    }

    public void delete(Long id) {
        this.idiomaRepository.findById(id).map(p -> {
                    this.idiomaRepository.delete(p);
                    return p;
                })
                .orElseThrow(() -> new EntityNotFoundException(id, Idioma.class));
    }
}
