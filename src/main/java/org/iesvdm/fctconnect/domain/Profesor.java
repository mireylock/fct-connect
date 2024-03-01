package org.iesvdm.fctconnect.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="profesor")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profesor")
    @EqualsAndHashCode.Include
    private Long id;

    private String mail;
    private String password;
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String telefono;
    private String direccion;
    private String departamento;

    @OneToMany(mappedBy = "profesor")
    @JsonIgnore
    private List<ProfesorTutorizaAlumno> profesorTutorizaAlumnos;
}
