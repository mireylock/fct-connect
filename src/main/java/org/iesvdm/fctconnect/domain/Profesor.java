package org.iesvdm.fctconnect.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="profesor",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "mail"),
        @UniqueConstraint(columnNames = "dni")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue(value="profesor")
public class Profesor extends Usuario{
    @NotBlank
    private String dni;
    @NotBlank
    private String apellido1;
    private String apellido2;
    private String telefono;
    private String direccion;
    private String departamento;

    @OneToMany(mappedBy = "profesor")
    @JsonIgnore
    private List<ProfesorTutorizaAlumno> profesorTutorizaAlumnos;

    @ManyToMany (fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinTable(
            name = "profesor_imparte_asignatura",
            joinColumns = @JoinColumn(name = "id_profesor", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_asignatura", referencedColumnName = "id_asignatura"))
    private Set<Asignatura> asignaturas = new HashSet<>();
}
