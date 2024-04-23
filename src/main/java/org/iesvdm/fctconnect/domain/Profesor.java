package org.iesvdm.fctconnect.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private String dni;
    private String apellido1;
    private String apellido2;
    private String telefono;
    private String direccion;
    private String departamento;

    @OneToMany(mappedBy = "profesor")
    @JsonIgnore
    private List<ProfesorTutorizaAlumno> profesorTutorizaAlumnos;
}
