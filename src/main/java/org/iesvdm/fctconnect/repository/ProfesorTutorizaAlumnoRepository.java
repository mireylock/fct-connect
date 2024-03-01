package org.iesvdm.fctconnect.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorTutorizaAlumnoRepository extends JpaRepository<org.iesvdm.fctconnect.domain.ProfesorTutorizaAlumno, Long> {
}
