package org.iesvdm.fctconnect.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpresaDTO {
    private String inglesSolicitado;
    private Set<String> modalidadesTrabajo;
    private String resumen;
    private String pathSitioWeb;
    private String pathFoto;

}
