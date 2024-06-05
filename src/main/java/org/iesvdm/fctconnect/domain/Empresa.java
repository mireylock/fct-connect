package org.iesvdm.fctconnect.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iesvdm.fctconnect.domain.enums.EInglesSolicitado;
import org.iesvdm.fctconnect.domain.enums.EModalidadTrabajo;

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

    @OneToMany(mappedBy = "empresa")
    private Set<Tecnologia> tecnologias;

//    @ManyToMany (fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "empresa_tecnologia",
//            joinColumns = @JoinColumn(name = "id_empresa", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "id_tecnologia", referencedColumnName = "id_tecnologia"))
//    private Set<Tecnologia> tecnologias = new HashSet<>();

    @OneToMany(mappedBy = "empresa")
    @JsonIgnore
    private Set<Ubicacion> ubicaciones;

    @Builder
    public Empresa(long id, @NotBlank @Email String email, @NotBlank @Size(min = 6) String password, @NotBlank String nombre, String pathFoto, EInglesSolicitado inglesSolicitado, Set<EModalidadTrabajo> modalidadesTrabajo, String resumen, String pathSitioWeb, List<Solicitud> solicitudes, Set<Tecnologia> tecnologias, Set<Ubicacion> ubicaciones) {
        super(id, email, password, nombre, pathFoto);
        this.inglesSolicitado = inglesSolicitado;
        this.modalidadesTrabajo = modalidadesTrabajo;
        this.resumen = resumen;
        this.pathSitioWeb = pathSitioWeb;
        this.solicitudes = solicitudes;
        this.tecnologias = tecnologias;
        this.ubicaciones = ubicaciones;
    }
}
