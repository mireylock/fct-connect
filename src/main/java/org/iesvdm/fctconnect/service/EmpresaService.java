package org.iesvdm.fctconnect.service;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.fctconnect.domain.Alumno;
import org.iesvdm.fctconnect.domain.Empresa;
import org.iesvdm.fctconnect.domain.dto.EmpresaDTO;
import org.iesvdm.fctconnect.domain.enums.EInglesSolicitado;
import org.iesvdm.fctconnect.domain.enums.EModalidadTrabajo;
import org.iesvdm.fctconnect.exception.EntityNotFoundException;
import org.iesvdm.fctconnect.repository.EmpresaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public List<Empresa> allActivos() {
        return this.empresaRepository.findAllByActivoIsTrue();
    }



    public Map<String, Object> buscarEmpresaPaginacion(String nombre, EInglesSolicitado inglesSolicitado, EModalidadTrabajo modalidadTrabajo, String tecnologia, Optional<Integer> pagina, Optional<Integer> tamanio) {

        int page = pagina.orElse(0);
        int size = tamanio.orElse(6);

        Pageable pageable = PageRequest.of(page, size);
        Page<Empresa> empresas;

        if (tecnologia.isEmpty()) {
            empresas = empresaRepository.findEmpresasByNombreInglesModalidad(nombre, inglesSolicitado, modalidadTrabajo, pageable);
            log.info("Búsqueda de empresas INACTIVAS sin tecnologia");
        } else {
            empresas = empresaRepository.findEmpresasByNombreInglesModalidadTecnologia(nombre, inglesSolicitado, modalidadTrabajo, tecnologia, pageable);
            log.info("Búsqueda de empresas INACTIVAS con tecnologia");
        }

        long totalItems = empresas.getTotalElements();
        long totalPages = empresas.getTotalPages();

        Map<String, Object> response = new HashMap<>();
        response.put("empresas", empresas.getContent());
        response.put("currentPage", page);
        response.put("totalItems", totalItems);
        response.put("totalPages", totalPages);

        return response;
    }

    public Map<String, Object> buscarEmpresaInactivasPaginacion(String nombre, EInglesSolicitado inglesSolicitado, EModalidadTrabajo modalidadTrabajo, String tecnologia, Optional<Integer> pagina, Optional<Integer> tamanio) {

        int page = pagina.orElse(0);
        int size = tamanio.orElse(6);

        Pageable pageable = PageRequest.of(page, size);
        Page<Empresa> empresas;

        if(tecnologia.isEmpty()) {
            empresas = empresaRepository.findEmpresasInactivasByNombreInglesModalidad(nombre, inglesSolicitado, modalidadTrabajo, pageable);
            log.info("Búsqueda de empresas INACTIVAS sin tecnologia");
        } else {
            empresas = empresaRepository.findEmpresasInactivasByNombreInglesModalidadTecnologia(nombre, inglesSolicitado, modalidadTrabajo, tecnologia, pageable);
            log.info("Búsqueda de empresas INACTIVAS con tecnologia");
        }


        long totalItems = empresas.getTotalElements();
        long totalPages = empresas.getTotalPages();

        Map<String, Object> response = new HashMap<>();
        response.put("empresas", empresas.getContent());
        response.put("currentPage", page);
        response.put("totalItems", totalItems);
        response.put("totalPages", totalPages);

        return response;
    }


    public Empresa save(Empresa empresa) {
        return this.empresaRepository.save(empresa);
    }

    public Empresa one(Long id) {
        return this.empresaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Empresa.class));
    }

    public Empresa replace(Long id, EmpresaDTO empresaDTO) {
        return this.empresaRepository.findById(id)
                .map(empresa -> {
                    empresa.setInglesSolicitado(EInglesSolicitado.valueOf(empresaDTO.getInglesSolicitado()));
                    empresa.setModalidadesTrabajo(empresaDTO.getModalidadesTrabajo().stream()
                            .map(EModalidadTrabajo::valueOf).collect(Collectors.toSet()));
                    empresa.setResumen(empresaDTO.getResumen());
                    empresa.setPathSitioWeb(empresaDTO.getPathSitioWeb());
                    empresa.setPathFoto(empresaDTO.getPathFoto());

                    return this.empresaRepository.save(empresa);
                })
                .orElseThrow(() -> new EntityNotFoundException(id, Alumno.class));
    }

    public void delete(Long id) {
        this.empresaRepository.findById(id).map(p -> {
                    this.empresaRepository.delete(p);
                    return p;
                })
                .orElseThrow(() -> new EntityNotFoundException(id, Empresa.class));
    }
}
