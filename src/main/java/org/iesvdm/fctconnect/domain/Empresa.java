package org.iesvdm.fctconnect.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="empresa", uniqueConstraints = {
        @UniqueConstraint(columnNames = "mail")}
)
@Data
@NoArgsConstructor
@DiscriminatorValue(value="empresa")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Empresa extends Usuario{
    private String nombre;
    @Column(name="ingles_solicitado")
    private String inglesSolicitado; //imprescindible, importante, no necesario
    @Column(name="modalidad_trabajo")
    private String modalidadTrabajo; //presencial, online o híbrido TODO cambiar a que puedan ser varios
    private String resumen;
    @Column(name="path_sitio_web")
    private String pathSitioWeb;

    @OneToMany(mappedBy = "empresa")
    @JsonIgnore
    private List<Solicitud> solicitudes;

    public Empresa(long id, String email, String password, String nombre, String inglesSolicitado, String modalidadTrabajo, String resumen, String pathSitioWeb, List<Solicitud> solicitudes) {
        super(id, email, password);
        this.nombre = nombre;
        this.inglesSolicitado = inglesSolicitado;
        this.modalidadTrabajo = modalidadTrabajo;
        this.resumen = resumen;
        this.pathSitioWeb = pathSitioWeb;
        this.solicitudes = solicitudes;
    }
}
