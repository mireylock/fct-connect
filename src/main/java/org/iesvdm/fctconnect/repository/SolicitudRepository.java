package org.iesvdm.fctconnect.repository;

import org.iesvdm.fctconnect.domain.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {
}
