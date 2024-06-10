package org.iesvdm.fctconnect.service;

import org.iesvdm.fctconnect.domain.Alumno;
import org.iesvdm.fctconnect.domain.Profesor;
import org.iesvdm.fctconnect.domain.ProfesorTutorizaAlumno;
import org.iesvdm.fctconnect.domain.dto.ProfesorTutorizaAlumnoDTO;
import org.iesvdm.fctconnect.exception.EntityNotFoundException;
import org.iesvdm.fctconnect.repository.ProfesorTutorizaAlumnoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public List<Alumno> allFromProfesor(Long idProfesor) {
        return this.profesorTutorizaAlumnoRepository.findAlumnosByProfesorId(idProfesor);
    }

    public Map<String, Object> buscarAlumnosTutoriaPaginacion(Long idProfesor, String nombre, Optional<Integer> pagina, Optional<Integer> tamanio) {
        int page = pagina.orElse(0);
        int size = tamanio.orElse(6);

        Pageable pageable = PageRequest.of(page, size);
        Page<Alumno> alumnosTutoria = this.profesorTutorizaAlumnoRepository.findAlumnoTutoriaByNombreCompleto(idProfesor, nombre, pageable);

        long totalItems = alumnosTutoria.getTotalElements();
        long totalPages = alumnosTutoria.getTotalPages();

        Map<String, Object> response = new HashMap<>();
        response.put("alumnos", alumnosTutoria.getContent());
        response.put("currentPage", page);
        response.put("totalItems", totalItems);
        response.put("totalPages", totalPages);

        return response;
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
