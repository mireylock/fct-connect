package org.iesvdm.fctconnect.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfesorTutorizaAlumnoDTO {
    private Long id;
    private String tipoTutoria;
    private Long idAlumno;
    private Long idProfesor;

}
