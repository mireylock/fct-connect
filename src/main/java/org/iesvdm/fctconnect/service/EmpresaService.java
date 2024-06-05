package org.iesvdm.fctconnect.service;

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

@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public List<Empresa> all() {
        return this.empresaRepository.findAll();
    }


    public Map<String, Object> all(int pagina, int tamanio) {
        Pageable paginado = PageRequest.of(pagina, tamanio, Sort.by("id").ascending());
        Page<Empresa> pageAll = this.empresaRepository.findAll(paginado);

        Map<String, Object> response = new HashMap<>();

        response.put("empresas", pageAll.getContent());
        response.put("currentPage", pageAll.getNumber());
        response.put("totalItems", pageAll.getTotalElements());
        response.put("totalPages", pageAll.getTotalPages());

        return response;
    }

    public Map<String, Object> buscarEmpresaPaginacion(String nombre, EInglesSolicitado inglesSolicitado, EModalidadTrabajo modalidadTrabajo, String tecnologia, Optional<Integer> pagina, Optional<Integer> tamanio) {
        List<Empresa> empresas = empresaRepository.findEmpresasByNombreInglesModalidadTecnologia(nombre, inglesSolicitado, modalidadTrabajo, tecnologia);
        long totalItems = empresas.size();
        long totalPages = 1;
        if (tamanio.isPresent() && tamanio.get() != 0) {
            double paginas = (double) totalItems / tamanio.get();
            totalPages = (long) Math.ceil(paginas);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("empresas", empresas);
        response.put("currentPage", pagina.orElse(0));
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
