package org.iesvdm.fctconnect.domain;

import jakarta.persistence.*;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    protected long id;
    protected String email;
    protected String password;

    public String getRol() {
        // Obtener la anotación @DiscriminatorValue que está en las clases que heredan de Usuario
        DiscriminatorValue rol = this.getClass().getAnnotation(DiscriminatorValue.class);
        return rol.value();
    }
}