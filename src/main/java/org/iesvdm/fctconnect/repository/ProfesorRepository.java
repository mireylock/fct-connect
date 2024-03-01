package org.iesvdm.fctconnect.repository;

import org.iesvdm.fctconnect.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
}
