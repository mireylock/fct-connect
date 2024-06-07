package org.iesvdm.fctconnect.service;

import org.iesvdm.fctconnect.domain.Alumno;
import org.iesvdm.fctconnect.domain.Profesor;
import org.iesvdm.fctconnect.domain.ProfesorTutorizaAlumno;
import org.iesvdm.fctconnect.domain.dto.ProfesorTutorizaAlumnoDTO;
import org.iesvdm.fctconnect.exception.EntityNotFoundException;
import org.iesvdm.fctconnect.repository.ProfesorTutorizaAlumnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorTutorizaAlumnoService {
    private final ProfesorTutorizaAlumnoRepository profesorTutorizaAlumnoRepository;
    private final ProfesorService profesorService;
    private final AlumnoService alumnoService;

    public ProfesorTutorizaAlumnoService(ProfesorTutorizaAlumnoRepository profesorTutorizaAlumnoRepository, ProfesorService profesorService, AlumnoService alumnoService) {
        this.profesorTutorizaAlumnoRepository = profesorTutorizaAlumnoRepository;
        this.profesorService = profesorService;
        this.alumnoService = alumnoService;
    }

    public List<ProfesorTutorizaAlumno> all() {
        return this.profesorTutorizaAlumnoRepository.findAll();
    }


    public ProfesorTutorizaAlumno save(ProfesorTutorizaAlumnoDTO profesorTutorizaAlumnoDTO) {
        Alumno alumno = alumnoService.one(profesorTutorizaAlumnoDTO.getIdAlumno());
        Profesor profesor = profesorService.one(profesorTutorizaAlumnoDTO.getIdProfesor());

        ProfesorTutorizaAlumno tutoria = ProfesorTutorizaAlumno.builder()
                .alumno(alumno)
                .profesor(profesor)
                .tipoTutoria(profesorTutorizaAlumnoDTO.getTipoTutoria())
                .build();
        return profesorTutorizaAlumnoRepository.save(tutoria);
    }

    public void delete(Long id) {
        this.profesorTutorizaAlumnoRepository.findById(id).map(p -> {
                    this.profesorTutorizaAlumnoRepository.delete(p);
                    return p;
                })
                .orElseThrow(() -> new EntityNotFoundException(id, ProfesorTutorizaAlumno.class));
    }

}
