package org.iesvdm.fctconnect.service;

import org.iesvdm.fctconnect.domain.Alumno;
import org.iesvdm.fctconnect.exception.EntityNotFoundException;
import org.iesvdm.fctconnect.repository.AlumnoRepository;
import org.iesvdm.fctconnect.repository.CustomQueryImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AlumnoService {
    private final AlumnoRepository alumnoRepository;
    private final CustomQueryImpl customQuery;

    public AlumnoService(AlumnoRepository alumnoRepository, CustomQueryImpl empresaRepositoryCustomQuery) {
        this.alumnoRepository = alumnoRepository;
        this.customQuery = empresaRepositoryCustomQuery;
    }

    public List<Alumno> all() {
        return this.alumnoRepository.findAll();
    }

    public Map<String, Object> buscarAlumnoPaginacion(Optional<Boolean> carnetConducirOpt, Optional<Boolean> vehiculoPropioOpt, Optional<String> idiomaOpt, Optional<String> orderOpt, Optional<Integer> paginaOpt, Optional<Integer> tamanio) {
        return this.customQuery.buscarAlumnoPaginacion(carnetConducirOpt, vehiculoPropioOpt, idiomaOpt, orderOpt, paginaOpt, tamanio);
    }


//    public List<Alumno> all(Optional<String> buscarOpt, Optional<String> ordenarOpt) {
//        if (buscarOpt.isPresent() && ordenarOpt.isPresent()) {
//            if (ordenarOpt.get().equals("asc")) {
//                return this.alumnoRepository.findAlumnoByNombreContainingIgnoreCaseOrderByNombreAsc(buscarOpt.get());
//            } else {
//                return this.alumnoRepository.findAlumnoByNombreContainingIgnoreCaseOrderByNombreDesc(buscarOpt.get());
//            }
//        } else if (!buscarOpt.isPresent() && ordenarOpt.isPresent()) {
//            if (ordenarOpt.get().equals("asc")) {
//                return this.alumnoRepository.findAllByOrderByNombreAsc();
//            } else {
//                return this.alumnoRepository.findAllByOrderByNombreDesc();
//            }
//        } else if (buscarOpt.isPresent() && !ordenarOpt.isPresent()) {
//            return this.alumnoRepository.findAlumnoByNombreContainingIgnoreCase(buscarOpt.get());
//        } else {
//            return this.alumnoRepository.findAll();
//        }
//    }

    public Map<String, Object> all(int pagina, int tamanio) {
        Pageable paginado = PageRequest.of(pagina, tamanio, Sort.by("id").ascending());
        Page<Alumno> pageAll = this.alumnoRepository.findAll(paginado);

        Map<String, Object> response = new HashMap<>();

        response.put("alumnos", pageAll.getContent());
        response.put("currentPage", pageAll.getNumber());
        response.put("totalItems", pageAll.getTotalElements());
        response.put("totalPages", pageAll.getTotalPages());

        return response;
    }

    public Alumno save(Alumno alumno) {
        return this.alumnoRepository.save(alumno);
    }

    public Alumno one(Long id) {
        return this.alumnoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Alumno.class));
    }

    public Alumno replace(Long id, Alumno alumno) {

        return this.alumnoRepository.findById(id)
                .map(c -> {
                    alumno.setId(id); // si no se setea el id no lo guarda
                    return this.alumnoRepository.save(alumno);
                })
                .orElseThrow(() -> new EntityNotFoundException(id, Alumno.class));
    }

    public void delete(Long id) {
        this.alumnoRepository.findById(id).map(p -> {
                    this.alumnoRepository.delete(p);
                    return p;
                })
                .orElseThrow(() -> new EntityNotFoundException(id, Alumno.class));
    }
}



