package org.iesvdm.fctconnect.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class RegisterRequest {
    private String email;
    private String password;
    private String rol;

}

