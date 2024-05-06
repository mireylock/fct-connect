package org.iesvdm.fctconnect.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue(value="empresa")
public class Empresa extends Usuario{
    @Column(name="ingles_solicitado")
    private EInglesSolicitado inglesSolicitado;

    @Column(name="modalidad_trabajo")
    @ElementCollection
    @CollectionTable(name="modalidad_trabajo_empresa", joinColumns=@JoinColumn(name="id_empresa"))
    @Enumerated(EnumType.STRING)
    private Set<EModalidadTrabajo> modalidadesTrabajo;

    private String resumen;
    @Column(name="path_sitio_web")
    private String pathSitioWeb;

    @OneToMany(mappedBy = "empresa")
    @JsonIgnore
    private List<Solicitud> solicitudes;

    @ManyToMany (fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinTable(
            name = "empresa_tecnologia",
            joinColumns = @JoinColumn(name = "id_empresa", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_tecnologia", referencedColumnName = "id_tecnologia"))
    private Set<Tecnologia> tecnologias = new HashSet<>();

    @OneToMany(mappedBy = "empresa")
    @JsonIgnore
    private Set<Ubicacion> ubicaciones;


}
