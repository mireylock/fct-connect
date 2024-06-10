package org.iesvdm.fctconnect.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iesvdm.fctconnect.domain.enums.EEstadoSolicitud;
import org.iesvdm.fctconnect.domain.enums.ETipoSolicitud;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SolicitudCrearDTO {
    private Long idAlumno;
    private Long idEmpresa;
    private EEstadoSolicitud estado;
    private ETipoSolicitud tipo;
    private String descripcion;
}
