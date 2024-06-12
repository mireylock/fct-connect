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

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    List<Empresa> findAllByActivoIsTrue();

    @Query("SELECT e FROM Empresa e JOIN e.tecnologias t " +
            "WHERE (:nombre IS NULL OR :nombre = '' OR LOWER(e.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) " +
            "AND (:inglesSolicitado IS NULL OR e.inglesSolicitado = :inglesSolicitado) " +
            "AND (:modalidadTrabajo IS NULL OR :modalidadTrabajo MEMBER OF e.modalidadesTrabajo) " +
            "AND (:tecnologia IS NULL OR LOWER(t.nombre) LIKE LOWER(CONCAT('%', :tecnologia, '%')))" +
            "AND (e.activo = true)")
    Page<Empresa> findEmpresasByNombreInglesModalidadTecnologia(@Param("nombre") String nombre,
                                                                @Param("inglesSolicitado") EInglesSolicitado inglesSolicitado,
                                                                @Param("modalidadTrabajo") EModalidadTrabajo modalidadTrabajo,
                                                                @Param("tecnologia") String tecnologia,
                                                                Pageable pageable);

    @Query("SELECT e FROM Empresa e " +
            "WHERE (:nombre IS NULL OR :nombre = '' OR LOWER(e.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) " +
            "AND (:inglesSolicitado IS NULL OR e.inglesSolicitado = :inglesSolicitado) " +
            "AND (:modalidadTrabajo IS NULL OR :modalidadTrabajo MEMBER OF e.modalidadesTrabajo)"+
            "AND (e.activo = true)")
    Page<Empresa> findEmpresasByNombreInglesModalidad(@Param("nombre") String nombre,
                                                        @Param("inglesSolicitado") EInglesSolicitado inglesSolicitado,
                                                        @Param("modalidadTrabajo") EModalidadTrabajo modalidadTrabajo,
                                                      Pageable pageable);

    @Query("SELECT e FROM Empresa e JOIN e.tecnologias t " +
            "WHERE (:nombre IS NULL OR :nombre = '' OR LOWER(e.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) " +
            "AND (:inglesSolicitado IS NULL OR e.inglesSolicitado = :inglesSolicitado) " +
            "AND (:modalidadTrabajo IS NULL OR :modalidadTrabajo MEMBER OF e.modalidadesTrabajo) " +
            "AND (:tecnologia IS NULL OR LOWER(t.nombre) LIKE LOWER(CONCAT('%', :tecnologia, '%')))" +
            "AND (e.activo = false)")
    Page<Empresa> findEmpresasInactivasByNombreInglesModalidadTecnologia(@Param("nombre") String nombre,
                                                                @Param("inglesSolicitado") EInglesSolicitado inglesSolicitado,
                                                                @Param("modalidadTrabajo") EModalidadTrabajo modalidadTrabajo,
                                                                @Param("tecnologia") String tecnologia,
                                                                         Pageable pageable);

    @Query("SELECT e FROM Empresa e " +
            "WHERE (:nombre IS NULL OR :nombre = '' OR LOWER(e.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) " +
            "AND (:inglesSolicitado IS NULL OR e.inglesSolicitado = :inglesSolicitado) " +
            "AND (:modalidadTrabajo IS NULL OR :modalidadTrabajo MEMBER OF e.modalidadesTrabajo)" +
            "AND (e.activo = false)")
    Page<Empresa> findEmpresasInactivasByNombreInglesModalidad(@Param("nombre") String nombre,
                                                      @Param("inglesSolicitado") EInglesSolicitado inglesSolicitado,
                                                      @Param("modalidadTrabajo") EModalidadTrabajo modalidadTrabajo,
                                                               Pageable pageable);
}
