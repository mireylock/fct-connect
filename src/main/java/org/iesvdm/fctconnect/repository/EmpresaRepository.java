package org.iesvdm.fctconnect.repository;

import org.iesvdm.fctconnect.domain.Empresa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    Page<Empresa> findEmpresaByNameContainingIgnoreCaseAAndInglesSolicitadoAndModalidadesTrabajoContainingAndTecnologias(String nombre, String inglesSolicitado, String modalidadTrabajo);


    // Búsquedas por nombre por ingles+modalidad de trabajo y paginación
//    public List<Empresa> findEmpresaByNombreContainingIgnoreCaseOrderByNombreAsc(String buscar);
//
//    List<Empresa> findEmpresaByNombreContainingIgnoreCaseOrderByNombreDesc(String buscar);
//
//    List<Empresa> findAllByOrderByNombreAsc();
//
//    List<Empresa> findAllByOrderByNombreDesc();
//
//    List<Empresa> findEmpresaByNombreContainingIgnoreCase(String buscar);
//
//    List<Empresa> findEmpresaByInglesSolicitadoContainingIgnoreCaseAndModalidadTrabajoContainingIgnoreCase(String inglesSolicitado, String modalidadTrabajo);
//
//    List<Empresa> findEmpresaByInglesSolicitadoContainingIgnoreCase(String inglesSolicitado);
//
//    List<Empresa> findEmpresaByModalidadTrabajoContainingIgnoreCase(String modalidadTrabajo);



}
