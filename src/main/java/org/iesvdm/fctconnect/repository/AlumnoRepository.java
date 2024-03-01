package org.iesvdm.fctconnect.repository;

import org.iesvdm.fctconnect.domain.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
}
