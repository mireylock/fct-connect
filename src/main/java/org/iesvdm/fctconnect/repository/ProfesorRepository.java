package org.iesvdm.fctconnect.repository;

import org.iesvdm.fctconnect.domain.Profesor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

    List<Profesor> findAllByActivoIsTrue();

    @Query(value = "select P from Profesor P where CONCAT(P.nombre, ' ', P.apellido1, ' ', COALESCE(P.apellido2, '')) LIKE CONCAT('%', :nombre, '%') and P.activo=true")
    public Page<Profesor> findProfesorByNombreCompleto(String nombre, Pageable pageable);

    @Query(value = "select P from Profesor P where CONCAT(P.nombre, ' ', P.apellido1, ' ', COALESCE(P.apellido2, '')) LIKE CONCAT('%', :nombre, '%') and P.activo=false")
    public Page<Profesor> findProfesorByNombreCompletoInactivo(String nombre, Pageable pageable);


}
