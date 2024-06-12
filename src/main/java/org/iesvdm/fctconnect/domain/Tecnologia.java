package org.iesvdm.fctconnect.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tecnologia")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Tecnologia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_tecnologia")
    @EqualsAndHashCode.Include
    private long id;
    @NotNull
    @NotBlank
    private String nombre;
    private String descripcion;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="id_empresa")
    private Empresa empresa;


}
