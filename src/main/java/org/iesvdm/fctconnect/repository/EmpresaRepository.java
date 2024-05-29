package org.iesvdm.fctconnect.repository;

import org.iesvdm.fctconnect.domain.Empresa;
import org.iesvdm.fctconnect.domain.enums.EInglesSolicitado;
import org.iesvdm.fctconnect.domain.enums.EModalidadTrabajo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {


    @Query("SELECT DISTINCT e FROM Empresa e LEFT JOIN e.tecnologias t " +
            "WHERE (:nombre IS NULL OR :nombre = '' OR LOWER(e.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) " +
            "AND (:inglesSolicitado IS NULL OR e.inglesSolicitado = :inglesSolicitado) " +
            "AND (:modalidadTrabajo IS NULL OR :modalidadTrabajo MEMBER OF e.modalidadesTrabajo) " +
            "AND (:tecnologia IS NULL OR t IS NULL OR LOWER(t.nombre) LIKE LOWER(CONCAT('%', :tecnologia, '%')))")
    List<Empresa> findEmpresasByCriteria(@Param("nombre") String nombre,
                                         @Param("inglesSolicitado") EInglesSolicitado inglesSolicitado,
                                         @Param("modalidadTrabajo") EModalidadTrabajo modalidadTrabajo,
                                         @Param("tecnologia") String tecnologia);
}
