package org.iesvdm.fctconnect.service;

import org.iesvdm.fctconnect.domain.EEstadoSolicitud;
import org.iesvdm.fctconnect.domain.ETipoSolicitud;
import org.iesvdm.fctconnect.domain.Solicitud;
import org.iesvdm.fctconnect.domain.dto.SolicitudDTO;
import org.iesvdm.fctconnect.exception.EntityNotFoundException;
import org.iesvdm.fctconnect.repository.SolicitudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SolicitudService {
    private final SolicitudRepository solicitudRepository;

    public SolicitudService(SolicitudRepository solicitudRepository) {
        this.solicitudRepository = solicitudRepository;
    }

    public List<Solicitud> all() {
        return this.solicitudRepository.findAll();
    }

    public List<Solicitud> allAlumno(String estado, String tipo, long id) {
        return this.solicitudRepository.findByEstadoAndTipoAndAlumno_Id(EEstadoSolicitud.valueOf(estado), ETipoSolicitud.valueOf(tipo), id);
    }

    public List<Solicitud> allEmpresa(String estado, String tipo, long id) {
        return this.solicitudRepository.findByEstadoAndTipoAndEmpresa_Id(EEstadoSolicitud.valueOf(estado), ETipoSolicitud.valueOf(tipo), id);
    }

//    public List<Solicitud> all(Optional<String> buscarOpt, Optional<String> ordenarOpt) {
//        if (buscarOpt.isPresent() && ordenarOpt.isPresent()) {
//            if (ordenarOpt.get().equals("asc")) {
//                return this.solicitudRepository.findSolicitudByNombreContainingIgnoreCaseOrderByNombreAsc(buscarOpt.get());
//            } else {
//                return this.solicitudRepository.findSolicitudByNombreContainingIgnoreCaseOrderByNombreDesc(buscarOpt.get());
//            }
//        } else if (!buscarOpt.isPresent() && ordenarOpt.isPresent()) {
//            if (ordenarOpt.get().equals("asc")) {
//                return this.solicitudRepository.findAllByOrderByNombreAsc();
//            } else {
//                return this.solicitudRepository.findAllByOrderByNombreDesc();
//            }
//        } else if (buscarOpt.isPresent() && !ordenarOpt.isPresent()) {
//            return this.solicitudRepository.findSolicitudByNombreContainingIgnoreCase(buscarOpt.get());
//        } else {
//            return this.solicitudRepository.findAll();
//        }
//    }

    public Map<String, Object> all(int pagina, int tamanio) {
        Pageable paginado = PageRequest.of(pagina, tamanio, Sort.by("id").ascending());
        Page<Solicitud> pageAll = this.solicitudRepository.findAll(paginado);

        Map<String, Object> response = new HashMap<>();

        response.put("solicitudes", pageAll.getContent());
        response.put("currentPage", pageAll.getNumber());
        response.put("totalItems", pageAll.getTotalElements());
        response.put("totalPages", pageAll.getTotalPages());

        return response;
    }

    public Solicitud save(Solicitud solicitud) {
        return this.solicitudRepository.save(solicitud);
    }

    public Solicitud one(Long id) {
        return this.solicitudRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Solicitud.class));
    }

    public Solicitud replace(Long id, SolicitudDTO solicitudDTO) {

        return this.solicitudRepository.findById(id)
                .map(solicitud -> {
                    solicitud.setEstado(EEstadoSolicitud.valueOf(solicitudDTO.getEstado()));
                    return this.solicitudRepository.save(solicitud);
                })
                .orElseThrow(() -> new EntityNotFoundException(id, Solicitud.class));
    }

    public void delete(Long id) {
        this.solicitudRepository.findById(id).map(p -> {
                    this.solicitudRepository.delete(p);
                    return p;
                })
                .orElseThrow(() -> new EntityNotFoundException(id, Solicitud.class));
    }
}

