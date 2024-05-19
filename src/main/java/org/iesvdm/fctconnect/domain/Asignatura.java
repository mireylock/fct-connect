package org.iesvdm.fctconnect.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="asignatura")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Asignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_asignatura")
    @EqualsAndHashCode.Include
    private long id;
    String nombre;
    String curso;
    String grupo;

    @ManyToMany(mappedBy = "asignaturas", fetch = FetchType.EAGER,  cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JsonIgnore
    private Set<Profesor> profesores = new HashSet<>();

}
