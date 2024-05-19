package org.iesvdm.fctconnect.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

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

    @Builder
    public Profesor(long id, @NotBlank @Email String email, @NotBlank @Size(min = 6) String password, @NotBlank String nombre, String pathFoto, String dni, String apellido1, String apellido2, String telefono, String direccion, String departamento, List<ProfesorTutorizaAlumno> profesorTutorizaAlumnos, Set<Asignatura> asignaturas) {
        super(id, email, password, nombre, pathFoto);
        this.dni = dni;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.telefono = telefono;
        this.direccion = direccion;
        this.departamento = departamento;
        this.profesorTutorizaAlumnos = profesorTutorizaAlumnos;
        this.asignaturas = asignaturas;
    }
}
