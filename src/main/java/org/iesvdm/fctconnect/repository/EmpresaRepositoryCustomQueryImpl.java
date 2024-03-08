package org.iesvdm.fctconnect.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.iesvdm.fctconnect.domain.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class EmpresaRepositoryCustomQueryImpl implements  EmpresaCustomQuery{
    @Autowired
    private EntityManager em;

    @Override
    public Map<String, Object> buscarEmpresaPaginacion (Optional<String> nombreOpt, Optional<String> modalidadTrabajoOpt, Optional<String> inglesSolicitadoOpt, Optional<String> orderOpt, Optional<Integer> paginaOpt, Optional<Integer> tamanio) {
        String queryStr = "select E from Empresa E";
        if (nombreOpt.isPresent() || modalidadTrabajoOpt.isPresent() || inglesSolicitadoOpt.isPresent()) queryStr += " where ";
        if (nombreOpt.isPresent()) queryStr += "E.nombre like:nombre";
        if (modalidadTrabajoOpt.isPresent()) queryStr += "E.modalidadTrabajo like :modalidadTrabajo";
        if (modalidadTrabajoOpt.isPresent()) queryStr += "E.inglesSolicitado like :inglesSolicitado";
        if (orderOpt.isPresent() && orderOpt.equals("asc") || orderOpt.equals("desc")) queryStr += " order by "+orderOpt.get();

        Query query = null;

        query = em.createQuery(queryStr, Empresa.class);

        if (nombreOpt.isPresent()) query.setParameter("nombre", nombreOpt.get());
        if (modalidadTrabajoOpt.isPresent()) query.setParameter("modalidadTrabajo", modalidadTrabajoOpt.get());
        if (inglesSolicitadoOpt.isPresent()) query.setParameter("inglesSolicitado", inglesSolicitadoOpt);

        String countQueryStr = "select count(*) from Empresa E";
        Query countQuery = null;
        countQuery = em.createQuery(countQueryStr, Long.class);
        long totalItems = (Long) countQuery.getSingleResult();

        long totalPages = totalItems/paginaOpt.get();

        List<Empresa> empresas = query.getResultList();

        Map<String, Object> response = new HashMap<>();
        response.put("empresas", empresas);
        response.put("currentPage", paginaOpt.get());
        response.put("totalItems", countQuery);
        response.put("totalPages", totalPages);

        return response;
    }

}
