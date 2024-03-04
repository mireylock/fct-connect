package org.iesvdm.fctconnect.repository;

import org.iesvdm.fctconnect.domain.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    public List<Empresa> findEmpresaByNombreContainingIgnoreCaseOrderByNombreAsc(String buscar);

    List<Empresa> findEmpresaByNombreContainingIgnoreCaseOrderByNombreDesc(String buscar);

    List<Empresa> findAllByOrderByNombreAsc();

    List<Empresa> findAllByOrderByNombreDesc();

    List<Empresa> findEmpresaByNombreContainingIgnoreCase(String buscar);

    List<Empresa> findEmpresaByInglesSolicitadoAndModalidadTrabajo(String inglesSolicitado, String modalidadTrabajo);

    List<Empresa> findEmpresaByInglesSolicitado(String inglesSolicitado);

    List<Empresa> findEmpresaByModalidadTrabajo(String modalidadTrabajo);

}
