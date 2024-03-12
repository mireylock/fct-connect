package org.iesvdm.fctconnect.service;

import org.iesvdm.fctconnect.domain.Empresa;
import org.iesvdm.fctconnect.exception.EntityNotFoundException;
import org.iesvdm.fctconnect.repository.EmpresaRepository;
import org.iesvdm.fctconnect.repository.CustomQueryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;

    private final CustomQueryImpl customQuery;

    public EmpresaService(EmpresaRepository empresaRepository, CustomQueryImpl empresaRepositoryCustomQuery) {
        this.empresaRepository = empresaRepository;
        this.customQuery = empresaRepositoryCustomQuery;
    }

    public List<Empresa> all() {
        return this.empresaRepository.findAll();
    }

    public Map<String, Object> buscarEmpresaPaginacion (Optional<String> nombreOpt,
                                                        Optional<String> modalidadTrabajoOpt,
                                                        Optional<String> inglesSolicitadoOpt,
                                                        Optional<String> orderOpt,
                                                        Optional<Integer> paginaOpt,
                                                        Optional<Integer> tamanioOpt) {
        return this.customQuery.buscarEmpresaPaginacion(nombreOpt, modalidadTrabajoOpt, inglesSolicitadoOpt,
                orderOpt, paginaOpt, tamanioOpt);
    }




    // Búsquedas por nombre por ingles+modalidad de trabajo y paginación
//
//        public List<Empresa> buscarPorNombre (Optional<String> buscarOpt, Optional<String> ordenarOpt) {
//        if (buscarOpt.isPresent() && ordenarOpt.isPresent()) {
//            if (ordenarOpt.get().equals("asc")) {
//                return this.empresaRepository.findEmpresaByNombreContainingIgnoreCaseOrderByNombreAsc(buscarOpt.get());
//            } else if (buscarOpt.get().equals("desc")){
//                return this.empresaRepository.findEmpresaByNombreContainingIgnoreCaseOrderByNombreDesc(buscarOpt.get());
//            }
//        } else if (!buscarOpt.isPresent() && ordenarOpt.isPresent()) {
//            if (ordenarOpt.get().equals("asc")) {
//                return this.empresaRepository.findAllByOrderByNombreAsc();
//            } else if (buscarOpt.get().equals("desc")){
//                return this.empresaRepository.findAllByOrderByNombreDesc();
//            }
//        } else if (buscarOpt.isPresent() && !ordenarOpt.isPresent()) {
//            return this.empresaRepository.findEmpresaByNombreContainingIgnoreCase(buscarOpt.get());
//        }
//        return this.empresaRepository.findAll();
//    }
//
//    public List<Empresa> buscarPorInglesYModalidad (Optional<String> inglesSolicitado, Optional<String> modalidadTrabajo) {
//        if (inglesSolicitado.isPresent() && modalidadTrabajo.isPresent()) {
//            return this.empresaRepository.findEmpresaByInglesSolicitadoContainingIgnoreCaseAndModalidadTrabajoContainingIgnoreCase(inglesSolicitado.get(), modalidadTrabajo.get());
//        } else if (inglesSolicitado.isPresent() && !modalidadTrabajo.isPresent()) {
//            return this.empresaRepository.findEmpresaByInglesSolicitadoContainingIgnoreCase(inglesSolicitado.get());
//        } else if (!inglesSolicitado.isPresent() && modalidadTrabajo.isPresent()) {
//            return this.empresaRepository.findEmpresaByModalidadTrabajoContainingIgnoreCase(modalidadTrabajo.get());
//        }
//
//        return this.empresaRepository.findAll();
//    }

//
//    public Map<String, Object> paginacion (String[] paginado) {
//        int pagina = Integer.parseInt(paginado[0]);
//        int tamanio =  Integer.parseInt(paginado[1]);
//
//        Pageable paginacion = PageRequest.of(pagina, tamanio, Sort.by("id").ascending());
//        Page<Empresa> pageAll = this.empresaRepository.findAll(paginacion);
//
//        Map<String, Object> response = new HashMap<>();
//
//        response.put("empresas", pageAll.getContent());
//        response.put("currentPage", pageAll.getNumber());
//        response.put("totalItems", pageAll.getTotalElements());
//        response.put("totalPages", pageAll.getTotalPages());
//
//        return response;
//    }



    public Empresa save(Empresa empresa) {
        return this.empresaRepository.save(empresa);
    }

    public Empresa one(Long id) {
        return this.empresaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Empresa.class));
    }

    public Empresa replace(Long id, Empresa empresa) {

        return this.empresaRepository.findById(id)
                .map(c -> {
                    empresa.setId(id); // si no se setea el id no lo guarda
                    return this.empresaRepository.save(empresa);
                })
                .orElseThrow(() -> new EntityNotFoundException(id, Empresa.class));
    }

    public void delete(Long id) {
        this.empresaRepository.findById(id).map(p -> {
                    this.empresaRepository.delete(p);
                    return p;
                })
                .orElseThrow(() -> new EntityNotFoundException(id, Empresa.class));
    }
}
