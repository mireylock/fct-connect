package org.iesvdm.fctconnect.repository;

import org.iesvdm.fctconnect.domain.Formacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormacionRepository extends JpaRepository<Formacion, Long> {
}
