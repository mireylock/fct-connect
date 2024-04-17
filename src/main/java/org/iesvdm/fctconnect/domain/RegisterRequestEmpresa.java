package org.iesvdm.fctconnect.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequestEmpresa {
    private String email;
    private String password;
    private String nombre;
}
