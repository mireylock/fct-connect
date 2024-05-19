package org.iesvdm.fctconnect.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequestEmpresa {
    private String email;
    private String password;
    private String nombre;
    private String pathFoto;
}
