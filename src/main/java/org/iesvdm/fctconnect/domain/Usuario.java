package org.iesvdm.fctconnect.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorColumn(name="rol")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="usuario",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email"),
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
    @Column(name = "path_foto")
    protected String pathFoto;
    protected Boolean activo;

    public String getRol() {
        // Obtener la anotación @DiscriminatorValue que está en las clases que heredan de Usuario
        DiscriminatorValue rol = this.getClass().getAnnotation(DiscriminatorValue.class);
        return rol.value();
    }
}