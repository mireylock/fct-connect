package org.iesvdm.fctconnect.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iesvdm.fctconnect.domain.enums.ENivelIdioma;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlumnoHablaIdiomaDTO {
    private String pathDiploma;
    private String descripcion;
    private ENivelIdioma nivel;
    private Long alumnoId;
    private Long idiomaId;
}
