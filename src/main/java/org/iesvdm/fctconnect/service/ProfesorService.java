package org.iesvdm.fctconnect.service;

import org.iesvdm.fctconnect.domain.Empresa;
import org.iesvdm.fctconnect.domain.Profesor;
import org.iesvdm.fctconnect.domain.dto.ProfesorDTO;
import org.iesvdm.fctconnect.domain.enums.EInglesSolicitado;
import org.iesvdm.fctconnect.domain.enums.EModalidadTrabajo;
import org.iesvdm.fctconnect.exception.EntityNotFoundException;
import org.iesvdm.fctconnect.repository.ProfesorRepository;
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
public class ProfesorService {
    private final ProfesorRepository profesorRepository;

    public ProfesorService(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    public List<Profesor> allActivos() {
        return this.profesorRepository.findAllByActivoIsTrue();
    }


    public Map<String, Object> buscarProfesorPaginacion(String nombre, Optional<Integer> pagina, Optional<Integer> tamanio) {
        int page = pagina.orElse(0);
        int size = tamanio.orElse(6);

        Pageable pageable = PageRequest.of(page, size);
        Page<Profesor> profesores = this.profesorRepository.findProfesorByNombreCompleto(nombre, pageable);
       
        long totalItems = profesores.getTotalElements();
        long totalPages = profesores.getTotalPages();

        Map<String, Object> response = new HashMap<>();
        response.put("profesores", profesores.getContent());
        response.put("currentPage", page);
        response.put("totalItems", totalItems);
        response.put("totalPages", totalPages);

        return response;
    }

    public Map<String, Object> buscarProfesorInactivoPaginacion(String nombre, Optional<Integer> pagina, Optional<Integer> tamanio) {
        int page = pagina.orElse(0);
        int size = tamanio.orElse(6);

        Pageable pageable = PageRequest.of(page, size);
        Page<Profesor> profesores = this.profesorRepository.findProfesorByNombreCompletoInactivo(nombre, pageable);

        long totalItems = profesores.getTotalElements();
        long totalPages = profesores.getTotalPages();

        Map<String, Object> response = new HashMap<>();
        response.put("profesores", profesores.getContent());
        response.put("currentPage", page);
        response.put("totalItems", totalItems);
        response.put("totalPages", totalPages);

        return response;
    }

//    public List<Profesor> profesoresDeUnAlumno (long idAlumno) {
//        return this.profesorRepository.profesoresDeUnAlumno(idAlumno);
//    }



//    public List<Profesor> all(Optional<String> buscarOpt, Optional<String> ordenarOpt) {
//        if (buscarOpt.isPresent() && ordenarOpt.isPresent()) {
//            if (ordenarOpt.get().equals("asc")) {
//                return this.profesorRepository.findProfesorByNombreContainingIgnoreCaseOrderByNombreAsc(buscarOpt.get());
//            } else {
//                return this.profesorRepository.findProfesorByNombreContainingIgnoreCaseOrderByNombreDesc(buscarOpt.get());
//            }
//        } else if (!buscarOpt.isPresent() && ordenarOpt.isPresent()) {
//            if (ordenarOpt.get().equals("asc")) {
//                return this.profesorRepository.findAllByOrderByNombreAsc();
//            } else {
//                return this.profesorRepository.findAllByOrderByNombreDesc();
//            }
//        } else if (buscarOpt.isPresent() && !ordenarOpt.isPresent()) {
//            return this.profesorRepository.findProfesorByNombreContainingIgnoreCase(buscarOpt.get());
//        } else {
//            return this.profesorRepository.findAll();
//        }
//    }
//
//    public Map<String, Object> all(int pagina, int tamanio) {
//        Pageable paginado = PageRequest.of(pagina, tamanio, Sort.by("id").ascending());
//        Page<Profesor> pageAll = this.profesorRepository.findAll(paginado);
//
//        Map<String, Object> response = new HashMap<>();
//
//        response.put("profesores", pageAll.getContent());
//        response.put("currentPage", pageAll.getNumber());
//        response.put("totalItems", pageAll.getTotalElements());
//        response.put("totalPages", pageAll.getTotalPages());
//
//        return response;
//    }

    public Profesor save(Profesor profesor) {
        return this.profesorRepository.save(profesor);
    }

    public Profesor one(Long id) {
        return this.profesorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Profesor.class));
    }

    public Profesor replace(Long id, ProfesorDTO profesorDTO) {

        return this.profesorRepository.findById(id)
                .map(profesor -> {
                    profesor.setDireccion(profesorDTO.getDireccion());
                    profesor.setTelefono(profesorDTO.getTelefono());
                    profesor.setPathFoto(profesorDTO.getPathFoto());
                    return this.profesorRepository.save(profesor);
                })
                .orElseThrow(() -> new EntityNotFoundException(id, Profesor.class));
    }

    public void delete(Long id) {
        this.profesorRepository.findById(id).map(p -> {
                    this.profesorRepository.delete(p);
                    return p;
                })
                .orElseThrow(() -> new EntityNotFoundException(id, Profesor.class));
    }
}
