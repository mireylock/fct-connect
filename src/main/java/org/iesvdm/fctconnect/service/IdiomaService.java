package org.iesvdm.fctconnect.service;

import org.iesvdm.fctconnect.domain.Alumno;
import org.iesvdm.fctconnect.domain.AlumnoHablaIdioma;
import org.iesvdm.fctconnect.domain.Idioma;
import org.iesvdm.fctconnect.domain.dto.AlumnoHablaIdiomaDTO;
import org.iesvdm.fctconnect.exception.EntityNotFoundException;
import org.iesvdm.fctconnect.repository.AlumnoHablaIdiomaRepository;
import org.iesvdm.fctconnect.repository.IdiomaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdiomaService {
    private final IdiomaRepository idiomaRepository;
    private final AlumnoHablaIdiomaRepository alumnoHablaIdiomaRepository;
    private final AlumnoService alumnoService;

    public IdiomaService(IdiomaRepository idiomaRepository, AlumnoHablaIdiomaRepository alumnoHablaIdiomaRepository, AlumnoService alumnoService) {
        this.idiomaRepository = idiomaRepository;
        this.alumnoHablaIdiomaRepository = alumnoHablaIdiomaRepository;
        this.alumnoService = alumnoService;
    }

    public List<Idioma> all() {
        return this.idiomaRepository.findAll();
    }

    public AlumnoHablaIdioma saveAlumnoHablaIdioma (AlumnoHablaIdiomaDTO alumnoHablaIdiomaDTO) {
        Alumno alumno = alumnoService.one(alumnoHablaIdiomaDTO.getAlumnoId());
        Idioma idioma = one(alumnoHablaIdiomaDTO.getIdiomaId());

        AlumnoHablaIdioma alumnoHablaIdioma = AlumnoHablaIdioma.builder()
                .pathDiploma(alumnoHablaIdiomaDTO.getPathDiploma())
                .descripcion(alumnoHablaIdiomaDTO.getDescripcion())
                .nivel(alumnoHablaIdiomaDTO.getNivel())
                .alumno(alumno)
                .idioma(idioma)
                .build();

        return alumnoHablaIdiomaRepository.save(alumnoHablaIdioma);
    }

    public void deleteAlumnoHablaIdioma(Long id) {
        this.alumnoHablaIdiomaRepository.findById(id).map(p -> {
                    this.alumnoHablaIdiomaRepository.delete(p);
                    return p;
                })
                .orElseThrow(() -> new EntityNotFoundException(id, AlumnoHablaIdioma.class));
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
