package org.iesvdm.fctconnect.domain.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iesvdm.fctconnect.domain.enums.EInglesSolicitado;
import org.iesvdm.fctconnect.domain.enums.EModalidadTrabajo;

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

}
