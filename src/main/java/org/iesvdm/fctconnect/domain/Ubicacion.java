package org.iesvdm.fctconnect.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="ubicacion")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_ubicacion")
    @EqualsAndHashCode.Include
    private long id;
    private String direccion;
    private String ciudad;
    private String pais;
    @Column(name = "codigo_postal")
    private String codigoPostal;

    @ManyToOne
    @JoinColumn(name="id_empresa")
    private Empresa empresa;


}
