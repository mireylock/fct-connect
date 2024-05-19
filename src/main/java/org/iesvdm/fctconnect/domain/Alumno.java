package org.iesvdm.fctconnect.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="alumno",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "mail"),
                @UniqueConstraint(columnNames = "dni")
})
@Data
@NoArgsConstructor
@DiscriminatorValue(value="alumno")
public class Alumno extends Usuario{
    @NotBlank
    private String dni;
    @NotBlank
    private String apellido1;
    private String apellido2;
    private String telefono;
    private String direccion;
    @Column(name="path_cv")
    private String pathCV;
    @Column(name="path_expediente")
    private String pathExpediente;
    @Column(name="carnet_conducir")
    private Long carnetConducir;
    @Column(name="vehiculo_propio")
    private Long vehiculoPropio;

    @OneToMany(mappedBy = "alumno")
    @JsonIgnore
    private List<Solicitud> solicitudes;

    @OneToMany(mappedBy = "alumno")
    @JsonIgnore
    private List<ProfesorTutorizaAlumno> profesorTutorizaAlumnos;

    @ManyToMany (fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinTable(
            name = "alumno_habla_idioma",
            joinColumns = @JoinColumn(name = "id_alumno", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_idioma", referencedColumnName = "id_idioma"))
    private Set<Idioma> idiomas = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="id_formacion")
    private Formacion formacion;

    @Builder
    public Alumno(long id, @NotBlank @Email String email, @NotBlank @Size(min = 6) String password, @NotBlank String nombre, String pathFoto, String dni, String apellido1, String apellido2, String telefono, String direccion, String pathCV, String pathExpediente, Long carnetConducir, Long vehiculoPropio, List<Solicitud> solicitudes, List<ProfesorTutorizaAlumno> profesorTutorizaAlumnos, Set<Idioma> idiomas, Formacion formacion) {
        super(id, email, password, nombre, pathFoto);
        this.dni = dni;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.telefono = telefono;
        this.direccion = direccion;
        this.pathCV = pathCV;
        this.pathExpediente = pathExpediente;
        this.carnetConducir = carnetConducir;
        this.vehiculoPropio = vehiculoPropio;
        this.solicitudes = solicitudes;
        this.profesorTutorizaAlumnos = profesorTutorizaAlumnos;
        this.idiomas = idiomas;
        this.formacion = formacion;
    }
}
