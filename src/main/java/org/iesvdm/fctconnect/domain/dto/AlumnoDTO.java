package org.iesvdm.fctconnect.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlumnoDTO {
    private String telefono;
    private String direccion;
    private Boolean carnetConducir;
    private Boolean vehiculoPropio;

}
