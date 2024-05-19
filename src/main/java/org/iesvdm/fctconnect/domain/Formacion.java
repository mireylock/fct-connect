package org.iesvdm.fctconnect.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table(name="formacion")
@Data
@Builder
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
