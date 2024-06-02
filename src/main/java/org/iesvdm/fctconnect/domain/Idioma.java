package org.iesvdm.fctconnect.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="idioma")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Idioma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_idioma")
    @EqualsAndHashCode.Include
    private long id;

    private String nombre;
//    private String descripcion;
//    @Column(name="path_diploma")
//    private String pathDiploma;

//    @ManyToMany(mappedBy = "idiomas", fetch = FetchType.EAGER,  cascade = {
//            CascadeType.PERSIST,
//            CascadeType.MERGE})
//    @JsonIgnore
//    private Set<Alumno> alumnos = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "idioma")
    private List<AlumnoHablaIdioma> alumnoHablaIdiomas;






}