package org.iesvdm.fctconnect.service;

import org.iesvdm.fctconnect.domain.Empresa;
import org.iesvdm.fctconnect.domain.Tecnologia;
import org.iesvdm.fctconnect.domain.dto.TecnologiaDTO;
import org.iesvdm.fctconnect.exception.EntityNotFoundException;
import org.iesvdm.fctconnect.repository.TecnologiaRepository;
import org.springframework.stereotype.Service;

@Service
public class TecnologiaService {
    private final TecnologiaRepository tecnologiaRepository;
    private final EmpresaService empresaService;

    public TecnologiaService(TecnologiaRepository tecnologiaRepository, EmpresaService empresaService) {
        this.tecnologiaRepository = tecnologiaRepository;
        this.empresaService = empresaService;
    }

    public Tecnologia save(TecnologiaDTO tecnologiaDTO) {
        Tecnologia tecnologia = Tecnologia.builder()
                .nombre(tecnologiaDTO.getNombre())
                .descripcion(tecnologiaDTO.getDescripcion())
                .build();

        Empresa empresa = empresaService.one(tecnologiaDTO.getIdEmpresa());
        tecnologia.setEmpresa(empresa);

        return tecnologiaRepository.save(tecnologia);

    }


    public void delete(Long id) {
        this.tecnologiaRepository.findById(id).map(t -> {
                    this.tecnologiaRepository.delete(t);
                    return t;
                })
                .orElseThrow(() -> new EntityNotFoundException(id, Tecnologia.class));
    }
}
