package org.iesvdm.fctconnect.service;

import org.iesvdm.fctconnect.domain.Profesor;
import org.iesvdm.fctconnect.exception.ProfesorNotFoundException;
import org.iesvdm.fctconnect.repository.ProfesorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProfesorService {
    private final ProfesorRepository profesorRepository;

    public ProfesorService(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    public List<Profesor> all() {
        return this.profesorRepository.findAll();
    }

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

    public Map<String, Object> all(int pagina, int tamanio) {
        Pageable paginado = PageRequest.of(pagina, tamanio, Sort.by("id").ascending());
        Page<Profesor> pageAll = this.profesorRepository.findAll(paginado);

        Map<String, Object> response = new HashMap<>();

        response.put("profesores", pageAll.getContent());
        response.put("currentPage", pageAll.getNumber());
        response.put("totalItems", pageAll.getTotalElements());
        response.put("totalPages", pageAll.getTotalPages());

        return response;
    }

    public Profesor save(Profesor profesor) {
        return this.profesorRepository.save(profesor);
    }

    public Profesor one(Long id) {
        return this.profesorRepository.findById(id)
                .orElseThrow(() -> new ProfesorNotFoundException(id));
    }

    public Profesor replace(Long id, Profesor profesor) {

        return this.profesorRepository.findById(id)
                .map(c -> {
                    profesor.setId(id); // si no se setea el id no lo guarda
                    return this.profesorRepository.save(profesor);
                })
                .orElseThrow(() -> new ProfesorNotFoundException(id));
    }

    public void delete(Long id) {
        this.profesorRepository.findById(id).map(p -> {
                    this.profesorRepository.delete(p);
                    return p;
                })
                .orElseThrow(() -> new ProfesorNotFoundException(id));
    }
}
