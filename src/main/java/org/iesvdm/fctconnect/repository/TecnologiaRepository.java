package org.iesvdm.fctconnect.repository;

import org.iesvdm.fctconnect.domain.Tecnologia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnologiaRepository extends JpaRepository<Tecnologia, Long> {
}
