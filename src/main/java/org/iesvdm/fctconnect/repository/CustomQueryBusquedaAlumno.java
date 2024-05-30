package org.iesvdm.fctconnect.repository;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public interface CustomQueryBusquedaAlumno {
    public Map<String, Object> buscarAlumnoPaginacion(Optional<String> nombre, Optional<Boolean> vehiculoPropio, Optional<String> idioma, Optional<Integer> pagina, Optional<Integer> tamanio);

}