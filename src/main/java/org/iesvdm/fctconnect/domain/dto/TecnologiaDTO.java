package org.iesvdm.fctconnect.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TecnologiaDTO {
    private long id;
    private String nombre;
    private String descripcion;
    private long idEmpresa;


}
