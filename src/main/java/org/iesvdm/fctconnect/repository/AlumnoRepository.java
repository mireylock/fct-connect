package org.iesvdm.fctconnect.repository;

import org.iesvdm.fctconnect.domain.Alumno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    List<Alumno> findAllByActivoIsTrue();
    Page<Alumno> findAllByActivoIsTrue(org.springframework.data.domain.Pageable paginado);
    Page<Alumno> findAllByActivoIsFalse(Pageable paginado);
}
