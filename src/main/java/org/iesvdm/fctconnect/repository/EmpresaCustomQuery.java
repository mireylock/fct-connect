package org.iesvdm.fctconnect.repository;

import jakarta.persistence.Query;
import org.iesvdm.fctconnect.domain.Empresa;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface EmpresaCustomQuery {
    public Map<String, Object> buscarEmpresaPaginacion (Optional<String> nombreOpt, Optional<String> modalidadTrabajoOpt, Optional<String> inglesSolicitadoOpt, Optional<String> orderOpt, Optional<Integer> paginaOpt, Optional<Integer> tamanio);

}
