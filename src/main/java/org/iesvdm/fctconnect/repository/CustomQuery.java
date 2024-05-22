package org.iesvdm.fctconnect.repository;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public interface CustomQuery {
    public Map<String, Object> buscarEmpresaPaginacion (Optional<String> nombreOpt, Optional<String> modalidadTrabajoOpt, Optional<String> inglesSolicitadoOpt, Optional<String> orderOpt, Optional<Integer> paginaOpt, Optional<Integer> tamanio);

    public Map<String, Object> buscarAlumnoPaginacion(Optional<String> nombre, Optional<Boolean> vehiculoPropio, Optional<String> idioma, Optional<Integer> pagina, Optional<Integer> tamanio);

}
