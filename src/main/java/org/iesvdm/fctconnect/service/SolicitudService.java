package org.iesvdm.fctconnect.service;

import org.iesvdm.fctconnect.domain.Alumno;
import org.iesvdm.fctconnect.domain.Empresa;
import org.iesvdm.fctconnect.domain.dto.SolicitudCrearDTO;
import org.iesvdm.fctconnect.domain.enums.EEstadoSolicitud;
import org.iesvdm.fctconnect.domain.enums.ETipoSolicitud;
import org.iesvdm.fctconnect.domain.Solicitud;
import org.iesvdm.fctconnect.domain.dto.SolicitudEstadoDTO;
import org.iesvdm.fctconnect.exception.EntityNotFoundException;
import org.iesvdm.fctconnect.repository.AlumnoRepository;
import org.iesvdm.fctconnect.repository.EmpresaRepository;
import org.iesvdm.fctconnect.repository.SolicitudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SolicitudService {
    private final SolicitudRepository solicitudRepository;
    private final AlumnoRepository alumnoRepository;
    private final EmpresaRepository empresaRepository;

    public SolicitudService(SolicitudRepository solicitudRepository, AlumnoRepository alumnoRepository, EmpresaRepository empresaRepository) {
        this.solicitudRepository = solicitudRepository;
        this.alumnoRepository = alumnoRepository;
        this.empresaRepository = empresaRepository;
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

    public Solicitud save(SolicitudCrearDTO solicitudCrearDTO) {
        Optional<Alumno> alumno = this.alumnoRepository.findById(solicitudCrearDTO.getIdAlumno());
        Optional<Empresa> empresa = this.empresaRepository.findById(solicitudCrearDTO.getIdEmpresa());

        if (alumno.isPresent() && empresa.isPresent()) {
            Solicitud solicitud = Solicitud.builder()
                    .alumno(alumno.get())
                    .empresa(empresa.get())
                    .tipo(solicitudCrearDTO.getTipo())
                    .estado(solicitudCrearDTO.getEstado())
                    .descripcion(solicitudCrearDTO.getDescripcion())
                    .build();

            return this.solicitudRepository.save(solicitud);
        } else {
            throw new ResourceNotFoundException("Alumno o Empresa no encontrado");
        }
    }

    public Solicitud one(Long id) {
        return this.solicitudRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Solicitud.class));
    }

    public Solicitud replace(Long id, SolicitudEstadoDTO solicitudEstadoDTO) {

        return this.solicitudRepository.findById(id)
                .map(solicitud -> {
                    solicitud.setEstado(EEstadoSolicitud.valueOf(solicitudEstadoDTO.getEstado()));
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

