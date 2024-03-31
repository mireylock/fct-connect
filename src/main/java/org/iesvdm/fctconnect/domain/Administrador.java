package org.iesvdm.fctconnect.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="adminisitrador",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "mail"),
                @UniqueConstraint(columnNames = "dni")
        }
)
@Data
@NoArgsConstructor
@DiscriminatorValue(value="administrador")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Administrador extends Usuario {
        private String dni;
        private String nombre;
        private String apellido1;
        private String apellido2;
        private String telefono;
        private String direccion;

        public Administrador(long id, String email, String password, String dni, String nombre, String apellido1, String apellido2, String telefono, String direccion) {
                super(id, email, password);
                this.dni = dni;
                this.nombre = nombre;
                this.apellido1 = apellido1;
                this.apellido2 = apellido2;
                this.telefono = telefono;
                this.direccion = direccion;
        }
}
