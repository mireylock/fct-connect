package org.iesvdm.fctconnect.repository;

import org.iesvdm.fctconnect.domain.EEstadoSolicitud;
import org.iesvdm.fctconnect.domain.ETipoSolicitud;
import org.iesvdm.fctconnect.domain.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {
    List<Solicitud> findByEstadoAndTipoAndAlumno_Id(EEstadoSolicitud estado, ETipoSolicitud tipo, long id);
    List<Solicitud> findByEstadoAndTipoAndEmpresa_Id(EEstadoSolicitud estado, ETipoSolicitud tipo, long id);


}
