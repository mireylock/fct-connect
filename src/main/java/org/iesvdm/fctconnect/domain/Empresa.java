package org.iesvdm.fctconnect.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="empresa")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    @EqualsAndHashCode.Include
    private Long id;

    private String nombre;
    private String mail;
    private String password;
    @Column(name="ingles_solicitado")
    private String inglesSolicitado; //imprescindible, importante, no necesario
    @Column(name="modalidad_trabajo")
    private String modalidadTrabajo; //presencial, online o híbrido
    private String resumen;
    @Column(name="path_sitio_web")
    private String pathSitioWeb;

    @OneToMany(mappedBy = "empresa")
    @JsonIgnore
    private List<Solicitud> solicitudes;

}
