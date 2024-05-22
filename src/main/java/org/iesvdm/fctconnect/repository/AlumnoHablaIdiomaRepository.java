package org.iesvdm.fctconnect.repository;

import org.iesvdm.fctconnect.domain.AlumnoHablaIdioma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoHablaIdiomaRepository extends JpaRepository<AlumnoHablaIdioma, Long> {
}
