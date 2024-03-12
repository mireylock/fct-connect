package org.iesvdm.fctconnect.repository;

import org.iesvdm.fctconnect.domain.Idioma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdiomaRepository extends JpaRepository<Idioma, Long> {
}
