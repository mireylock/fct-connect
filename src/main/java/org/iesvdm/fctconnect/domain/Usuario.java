package org.iesvdm.fctconnect.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorColumn(name="rol")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="usuario",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "mail"),
                @UniqueConstraint(columnNames = "dni")
        }
)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    protected long id;
    @NotBlank
    @Email
    protected String email;
    @NotBlank
    @Size(min = 6)
    protected String password;
    @NotBlank
    protected String nombre;

    public String getRol() {
        // Obtener la anotación @DiscriminatorValue que está en las clases que heredan de Usuario
        DiscriminatorValue rol = this.getClass().getAnnotation(DiscriminatorValue.class);
        return rol.value();
    }
}