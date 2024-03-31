package org.iesvdm.fctconnect.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {
    private String mail;
    private String password;

}
