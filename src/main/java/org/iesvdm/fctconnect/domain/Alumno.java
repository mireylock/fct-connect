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
@Table(name="alumno")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_alumno")
    @EqualsAndHashCode.Include
    private long id;

    private String mail;
    private String password;
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
    private boolean carnetConducir;
    @Column(name="vehiculo_propio")
    private boolean vehiculoPropio;

    @OneToMany(mappedBy = "alumno")
    @JsonIgnore
    private List<Solicitud> solicitudes;

    @OneToMany(mappedBy = "alumno")
    @JsonIgnore
    private List<ProfesorTutorizaAlumno> profesorTutorizaAlumnos;

    @ManyToMany (fetch = FetchType.EAGER)
    //@JsonIgnore
    @JoinTable(
            name = "alumno_habla_idioma",
            joinColumns = @JoinColumn(name = "id_alumno", referencedColumnName = "id_alumno"),
            inverseJoinColumns = @JoinColumn(name = "id_idioma", referencedColumnName = "id_idioma"))
    private Set<Idioma> idiomas = new HashSet<>();



}
