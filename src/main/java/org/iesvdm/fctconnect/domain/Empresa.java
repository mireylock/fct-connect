package org.iesvdm.fctconnect.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue(value="empresa")
public class Empresa extends Usuario{
    @Column(name="ingles_solicitado")
    private String inglesSolicitado;
    @Column(name="modalidad_trabajo")
    private String modalidadTrabajo; //TODO cambiar para que puedan ser varias a la vez... presencial, híbdrido y/o online
    private String resumen;
    @Column(name="path_sitio_web")
    private String pathSitioWeb;

    @OneToMany(mappedBy = "empresa")
    @JsonIgnore
    private List<Solicitud> solicitudes;

    public Empresa(long id, String email, String password, String nombre, String inglesSolicitado, String modalidadTrabajo, String resumen, String pathSitioWeb, List<Solicitud> solicitudes) {
        super(id, email, password, nombre);
        this.nombre = nombre;
        this.inglesSolicitado = inglesSolicitado;
        this.modalidadTrabajo = modalidadTrabajo;
        this.resumen = resumen;
        this.pathSitioWeb = pathSitioWeb;
        this.solicitudes = solicitudes;
    }
}
