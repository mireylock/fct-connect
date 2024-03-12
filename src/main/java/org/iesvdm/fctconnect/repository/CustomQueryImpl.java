package org.iesvdm.fctconnect.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.iesvdm.fctconnect.domain.Alumno;
import org.iesvdm.fctconnect.domain.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Slf4j
@Repository
public class CustomQueryImpl implements CustomQuery {
    @Autowired
    private EntityManager em;

    @Override
    public Map<String, Object> buscarEmpresaPaginacion (Optional<String> nombreOpt, Optional<String> modalidadTrabajoOpt, Optional<String> inglesSolicitadoOpt, Optional<String> orderOpt, Optional<Integer> paginaOpt, Optional<Integer> tamanio) {
        String queryStr = "select E from Empresa E";
        String countQueryStr = "select count(*) from Empresa E";

        if (nombreOpt.isPresent() || modalidadTrabajoOpt.isPresent() || inglesSolicitadoOpt.isPresent()) {
            queryStr += " where ";
            countQueryStr += " where ";
        }
        if (nombreOpt.isPresent()) {
            queryStr += " E.nombre LIKE CONCAT('%', :nombre, '%')";
            countQueryStr += " E.nombre LIKE CONCAT('%', :nombre, '%')";

            if (modalidadTrabajoOpt.isPresent()) {
                queryStr += " AND E.modalidadTrabajo like :modalidadTrabajo ";
                countQueryStr += " AND E.modalidadTrabajo like :modalidadTrabajo ";
            }

            if (inglesSolicitadoOpt.isPresent()) {
                queryStr += " AND E.inglesSolicitado like :inglesSolicitado ";
                countQueryStr += " AND E.inglesSolicitado like :inglesSolicitado ";
            }

        } else {
            if (modalidadTrabajoOpt.isPresent()) {
                queryStr += " E.modalidadTrabajo like :modalidadTrabajo";
                countQueryStr += " E.modalidadTrabajo like :modalidadTrabajo";

                if (inglesSolicitadoOpt.isPresent()) {
                    queryStr += " AND E.inglesSolicitado like :inglesSolicitado";
                    countQueryStr += " AND E.inglesSolicitado like :inglesSolicitado";

                }
            } else if (!modalidadTrabajoOpt.isPresent()) {
                if (inglesSolicitadoOpt.isPresent()) {
                    queryStr += "E.modalidadTrabajo like :modalidadTrabajo";
                    countQueryStr += "E.modalidadTrabajo like :modalidadTrabajo";
                }
            }
        }

        if (orderOpt.isPresent() && orderOpt.equals("asc") || orderOpt.equals("desc")) queryStr += " order by E.nombre "+orderOpt.get();

        Query query = null;
        query = em.createQuery(queryStr, Empresa.class);
        if (nombreOpt.isPresent()) query.setParameter("nombre", nombreOpt.get());
        if (modalidadTrabajoOpt.isPresent()) query.setParameter("modalidadTrabajo", modalidadTrabajoOpt.get());
        if (inglesSolicitadoOpt.isPresent()) query.setParameter("inglesSolicitado", inglesSolicitadoOpt.get());

        Query countQuery = null;
        countQuery = em.createQuery(countQueryStr, Long.class);
        if (nombreOpt.isPresent()) countQuery.setParameter("nombre", nombreOpt.get());
        if (modalidadTrabajoOpt.isPresent()) countQuery.setParameter("modalidadTrabajo", modalidadTrabajoOpt.get());
        if (inglesSolicitadoOpt.isPresent()) countQuery.setParameter("inglesSolicitado", inglesSolicitadoOpt.get());

        long totalItems = (Long) countQuery.getSingleResult();
        long totalPages = 1;
        if (tamanio.isPresent() && tamanio.get()!=0) {
            totalPages = totalItems/tamanio.get();
        }
        List<Empresa> empresas = query.getResultList();

        Map<String, Object> response = new HashMap<>();
        response.put("empresas", empresas);
        response.put("currentPage", paginaOpt.get());
        response.put("totalItems", totalItems);
        response.put("totalPages", totalPages);

        return response;
    }

    @Override
    public Map<String, Object> buscarAlumnoPaginacion(Optional<Boolean> carnetConducirOpt, Optional<Boolean> vehiculoPropioOpt, Optional<String> idiomaOpt,  Optional<String> orderOpt, Optional<Integer> paginaOpt, Optional<Integer> tamanio) {
        String queryStr = "select A from Alumno A";
        String countQueryStr = "select count(*) from Alumno A";

        if ((carnetConducirOpt.isPresent() || vehiculoPropioOpt.isPresent()) && !idiomaOpt.isPresent()) {
            queryStr += " where ";
            countQueryStr += " where ";
        } else if (idiomaOpt.isPresent()) {
            queryStr += " join A.idiomas I where ";
            countQueryStr += " join A.idiomas I where ";
        }

        if (carnetConducirOpt.isPresent()) {
            queryStr += " A.carnetConducir=:carnetConducir ";
            countQueryStr += " A.carnetConducir=:carnetConducir ";

            if (vehiculoPropioOpt.isPresent()) {
                queryStr += " and A.vehiculoPropio=:vehiculoPropio ";
                countQueryStr += " and A.vehiculoPropio=:vehiculoPropio ";
            }

            if (idiomaOpt.isPresent()) {
                queryStr += " and I.nombre LIKE CONCAT('%', :nombre, '%') ";
                countQueryStr += " and I.nombre LIKE CONCAT('%', :nombre, '%') ";

            }
        } else {
            if (vehiculoPropioOpt.isPresent()) {
                queryStr += " A.vehiculoPropio=:vehiculoPropio ";
                countQueryStr += " A.vehiculoPropio=:vehiculoPropio ";

                if (idiomaOpt.isPresent()) {
                    queryStr += " and I.nombre LIKE CONCAT('%', :nombre, '%') ";
                    countQueryStr += " and I.nombre LIKE CONCAT('%', :nombre, '%') ";
                }
            } else if (idiomaOpt.isPresent()) {
                queryStr += " I.nombre LIKE CONCAT('%', :nombre, '%') ";
                countQueryStr += " I.nombre LIKE CONCAT('%', :nombre, '%') ";
            }

        }

        if (orderOpt.isPresent() && orderOpt.equals("asc") || orderOpt.equals("desc")) queryStr += " order by I.nombre "+orderOpt.get();

        log.info(queryStr);

        Query query = null;
        query = em.createQuery(queryStr, Alumno.class);
        if (carnetConducirOpt.isPresent()) query.setParameter("carnetConducir", carnetConducirOpt.get() ? 1 : 0);

        if (vehiculoPropioOpt.isPresent()) query.setParameter("vehiculoPropio", vehiculoPropioOpt.get() ? 1 : 0);
        if (idiomaOpt.isPresent()) query.setParameter("nombre", idiomaOpt.get());

        Query countQuery = null;
        countQuery = em.createQuery(countQueryStr, Long.class);
        if (carnetConducirOpt.isPresent()) countQuery.setParameter("carnetConducir", carnetConducirOpt.get() ? 1 : 0);
        if (vehiculoPropioOpt.isPresent()) countQuery.setParameter("vehiculoPropio", vehiculoPropioOpt.get() ? 1 : 0);
        if (idiomaOpt.isPresent()) countQuery.setParameter("nombre", idiomaOpt.get());

        long totalItems = (Long) countQuery.getSingleResult();
        long totalPages = 1;
        if (tamanio.isPresent() && tamanio.get()!=0) {
            totalPages = totalItems/tamanio.get();
        }        List<Empresa> alumnos = query.getResultList();

        Map<String, Object> response = new HashMap<>();
        response.put("alumnos", alumnos);
        response.put("currentPage", paginaOpt.get());
        response.put("totalItems", totalItems);
        response.put("totalPages", totalPages);

        return response;

    }

}
