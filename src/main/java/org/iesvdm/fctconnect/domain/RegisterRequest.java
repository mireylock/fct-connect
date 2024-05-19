package org.iesvdm.fctconnect.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String email;
    private String password;
    private String rol;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String dni;
    private String pathFoto;
}

