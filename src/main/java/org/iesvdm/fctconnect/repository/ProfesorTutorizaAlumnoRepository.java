package org.iesvdm.fctconnect.repository;


import org.iesvdm.fctconnect.domain.Alumno;
import org.iesvdm.fctconnect.domain.Profesor;
import org.iesvdm.fctconnect.domain.ProfesorTutorizaAlumno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesorTutorizaAlumnoRepository extends JpaRepository<ProfesorTutorizaAlumno, Long> {

    @Query("SELECT p.alumno FROM ProfesorTutorizaAlumno p WHERE p.profesor.id = :profesorId")
    List<Alumno> findAlumnosByProfesorId(@Param("profesorId") Long profesorId);


    @Query("SELECT p.alumno FROM ProfesorTutorizaAlumno p WHERE p.profesor.id = :profesorId AND " +
            "LOWER(CONCAT(p.alumno.nombre, ' ', p.alumno.apellido1, ' ', COALESCE(p.alumno.apellido2, ''))) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    Page<Alumno> findAlumnoTutoriaByNombreCompleto(@Param("profesorId") Long profesorId, @Param("nombre") String nombre, Pageable pageable);
}
