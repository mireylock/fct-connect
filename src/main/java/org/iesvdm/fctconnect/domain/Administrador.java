package org.iesvdm.fctconnect.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Data
@NoArgsConstructor
@DiscriminatorValue(value="administrador")
public class Administrador extends Usuario {
        @NotBlank
        private String dni;
        @NotBlank
        private String apellido1;
        private String apellido2;
        private String telefono;
        private String direccion;

        @Builder

        public Administrador(long id, @NotBlank @Email String email, @NotBlank @Size(min = 6) String password, @NotBlank String nombre, String pathFoto, boolean activo, String dni, String apellido1, String apellido2, String telefono, String direccion) {
                super(id, email, password, nombre, pathFoto, activo);
                this.dni = dni;
                this.apellido1 = apellido1;
                this.apellido2 = apellido2;
                this.telefono = telefono;
                this.direccion = direccion;
        }
}
