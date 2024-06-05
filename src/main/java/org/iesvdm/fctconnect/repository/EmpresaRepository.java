package org.iesvdm.fctconnect.repository;

import org.iesvdm.fctconnect.domain.Empresa;
import org.iesvdm.fctconnect.domain.enums.EInglesSolicitado;
import org.iesvdm.fctconnect.domain.enums.EModalidadTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {


//    @Query("SELECT e FROM Empresa e JOIN e.tecnologias t " +
//            "WHERE (:nombre IS NULL OR :nombre = '' OR LOWER(e.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) " +
//            "AND (:inglesSolicitado IS NULL OR e.inglesSolicitado = :inglesSolicitado) " +
//            "AND (:modalidadTrabajo IS NULL OR :modalidadTrabajo MEMBER OF e.modalidadesTrabajo) " +
//            "AND (:tecnologia IS NULL OR LOWER(t.nombre) LIKE LOWER(CONCAT('%', :tecnologia, '%')))")

    @Query("SELECT e FROM Empresa e JOIN e.tecnologias t " +
            "WHERE (:nombre IS NULL OR :nombre = '' OR LOWER(e.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) " +
            "AND (:inglesSolicitado IS NULL OR e.inglesSolicitado = :inglesSolicitado) " +
            "AND (:modalidadTrabajo IS NULL OR :modalidadTrabajo MEMBER OF e.modalidadesTrabajo) " +
            "AND (:tecnologia IS NULL OR LOWER(t.nombre) LIKE LOWER(CONCAT('%', :tecnologia, '%')))")
    List<Empresa> findEmpresasByNombreInglesModalidadTecnologia(@Param("nombre") String nombre,
                                                                @Param("inglesSolicitado") EInglesSolicitado inglesSolicitado,
                                                                @Param("modalidadTrabajo") EModalidadTrabajo modalidadTrabajo,
                                                                @Param("tecnologia") String tecnologia);
}
