package org.iesvdm.fctconnect.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.iesvdm.fctconnect.domain.enums.ENivelIdioma;

@Entity
@Table(name="alumno_habla_idioma")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AlumnoHablaIdioma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alumno_habla_idioma")
    @EqualsAndHashCode.Include
    private Long id;
    @Column
    private String pathDiploma;
    private String descripcion;
    private ENivelIdioma nivel;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_alumno")
    private Alumno alumno;


    @ManyToOne
    @JoinColumn(name="id_idioma")
    private Idioma idioma;

}
