package org.iesvdm.fctconnect.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="alumno",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "mail"),
                @UniqueConstraint(columnNames = "dni")
        }
)
@Data
@NoArgsConstructor
@DiscriminatorValue(value="alumno")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Alumno extends Usuario{
    private String dni;
    private String nombre;
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
            joinColumns = @JoinColumn(name = "id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_idioma", referencedColumnName = "id_idioma"))
    private Set<Idioma> idiomas = new HashSet<>();


    public Alumno(long id, String email, String password, String dni, String nombre, String apellido1, String apellido2, String telefono, String direccion, String pathCV, String pathExpediente, Long carnetConducir, Long vehiculoPropio, List<Solicitud> solicitudes, List<ProfesorTutorizaAlumno> profesorTutorizaAlumnos, Set<Idioma> idiomas) {
        super(id, email, password);
        this.password = password;
        this.dni = dni;
        this.nombre = nombre;
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
    }


}
