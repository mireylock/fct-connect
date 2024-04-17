package org.iesvdm.fctconnect.service;

import org.iesvdm.fctconnect.domain.Alumno;
import org.iesvdm.fctconnect.domain.EmpresaRequest;
import org.iesvdm.fctconnect.exception.EntityNotFoundException;
import org.iesvdm.fctconnect.repository.EmpresaRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaRequestService {
    private final EmpresaRequestRepository empresaRequestRepository;

    public EmpresaRequestService(EmpresaRequestRepository empresaRequestRepositoryRepository) {
        this.empresaRequestRepository = empresaRequestRepositoryRepository;
    }

    public List<EmpresaRequest> all() {
        return this.empresaRequestRepository.findAll();
    }


    public void delete(Long id) {
        this.empresaRequestRepository.findById(id).map(e -> {
                    this.empresaRequestRepository.delete(e);
                    return e;
        })
                .orElseThrow(() -> new EntityNotFoundException(id, Alumno.class));
    }

}
