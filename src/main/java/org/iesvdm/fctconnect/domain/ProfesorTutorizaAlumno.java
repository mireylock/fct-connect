package org.iesvdm.fctconnect.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="profesor_tutoriza_alumno")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProfesorTutorizaAlumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profesor_tutoriza_alumno")
    @EqualsAndHashCode.Include
    private Long id;

    private String tipoTutoria; //practicas, proyecto o ambos

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_alumno")
    private Alumno alumno;


//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_profesor")
    private Profesor profesor;
}
