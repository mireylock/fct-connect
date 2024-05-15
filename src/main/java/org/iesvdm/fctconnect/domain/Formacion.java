package org.iesvdm.fctconnect.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="formacion")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Formacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_formacion")
    @EqualsAndHashCode.Include
    private long id;
    @NotBlank
    private String nombre;
    @Enumerated(EnumType.STRING)
    private ENivelFormacion nivel;

    @OneToMany(mappedBy = "formacion")
    @JsonIgnore
    private List<Alumno> alumnos;

}
