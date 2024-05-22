package org.iesvdm.fctconnect.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.iesvdm.fctconnect.domain.Alumno;
import org.iesvdm.fctconnect.domain.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Map<String, Object> buscarEmpresaPaginacion (Optional<String> nombreOpt,
                                                        Optional<String> modalidadTrabajoOpt,
                                                        Optional<String> inglesSolicitadoOpt,
                                                        Optional<String> orderOpt,
                                                        Optional<Integer> paginaOpt,
                                                        Optional<Integer> tamanio) {
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
            } else {
                if (inglesSolicitadoOpt.isPresent()) {
                    queryStr += "E.modalidadTrabajo like :modalidadTrabajo";
                    countQueryStr += "E.modalidadTrabajo like :modalidadTrabajo";
                }
            }
        }

        if (nombreOpt.isPresent() && orderOpt.isPresent() &&
                (orderOpt.get().equals("asc") || orderOpt.get().equals("desc"))) {
            queryStr += " order by E.nombre "+orderOpt.get();
        }

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
            double paginas = (double) totalItems /tamanio.get();
            totalPages = (long) Math.ceil(paginas);
        }
        List<Empresa> empresas = query.getResultList();

        Map<String, Object> response = new HashMap<>();
        response.put("empresas", empresas);
        response.put("currentPage", paginaOpt.orElse(0));
        response.put("totalItems", totalItems);
        response.put("totalPages", totalPages);

        return response;
    }

    @Override
    public Map<String, Object> buscarAlumnoPaginacion(Optional<String> nombre,
                                                      Optional<Boolean> vehiculoPropio,
                                                      Optional<String> idioma,
                                                      Optional<Integer> pagina,
                                                      Optional<Integer> tamanio) {
        String queryStr = "select A from Alumno A";
        String countQueryStr = "select count(*) from Alumno A";

        if (nombre.isPresent() && !nombre.get().isEmpty() || vehiculoPropio.isPresent() && idioma.isEmpty()) {
            queryStr += " where ";
            countQueryStr += " where ";
        } else if (idioma.isPresent() && !idioma.get().isEmpty()) {
            queryStr += " join A.idiomas I where ";
            countQueryStr += " join A.idiomas I where ";
        }

        if (nombre.isPresent() && !nombre.get().isEmpty()) {
            queryStr += " CONCAT(A.nombre, ' ', A.apellido1, ' ', COALESCE(A.apellido2, '')) LIKE CONCAT('%', :nombre, '%') ";
            countQueryStr += " CONCAT(A.nombre, ' ', A.apellido1, ' ', COALESCE(A.apellido2, '')) LIKE CONCAT('%', :nombre, '%') ";

            if (vehiculoPropio.isPresent()) {
                queryStr += " and A.vehiculoPropio=:vehiculoPropio ";
                countQueryStr += " and A.vehiculoPropio=:vehiculoPropio ";
            }

            if (idioma.isPresent() && !idioma.get().isEmpty()) {
                queryStr += " and I.nombre LIKE CONCAT('%', :idioma, '%') ";
                countQueryStr += " and I.nombre LIKE CONCAT('%', :idioma, '%') ";

            }
        } else {
            if (vehiculoPropio.isPresent()) {
                queryStr += " A.vehiculoPropio=:vehiculoPropio ";
                countQueryStr += " A.vehiculoPropio=:vehiculoPropio ";

                if (idioma.isPresent() && !idioma.get().isEmpty()) {
                    queryStr += " and I.nombre LIKE CONCAT('%', :idioma, '%') ";
                    countQueryStr += " and I.nombre LIKE CONCAT('%', :idioma, '%') ";
                }
            } else if (idioma.isPresent() && !idioma.get().isEmpty()) {
                queryStr += " I.nombre LIKE CONCAT('%', :idioma, '%') ";
                countQueryStr += " I.nombre LIKE CONCAT('%', :idioma, '%') ";
            }

        }

        log.info("LA QUERY ES: "+queryStr);

        Query query = null;
        query = em.createQuery(queryStr, Alumno.class);

        if (nombre.isPresent() && !nombre.get().isEmpty()) query.setParameter("nombre", nombre.get());
        if (vehiculoPropio.isPresent()) query.setParameter("vehiculoPropio", vehiculoPropio.get() ? 1 : 0);
        if (idioma.isPresent() && !idioma.get().isEmpty()) query.setParameter("idioma", idioma.get());

        Query countQuery = null;
        countQuery = em.createQuery(countQueryStr, Long.class);
        if (nombre.isPresent() && !nombre.get().isEmpty()) countQuery.setParameter("nombre", nombre.get());
        if (vehiculoPropio.isPresent()) countQuery.setParameter("vehiculoPropio", vehiculoPropio.get() ? 1 : 0);
        if (idioma.isPresent() && !idioma.get().isEmpty()) countQuery.setParameter("idioma", idioma.get());

        long totalItems = (Long) countQuery.getSingleResult();
        long totalPages = 1;
        if (tamanio.isPresent() && tamanio.get()!=0) {
            double paginas = (double) totalItems /tamanio.get();
            totalPages = (long) Math.ceil(paginas);
        }

        query.setFirstResult(pagina.orElse(0) * tamanio.orElse(1));
        query.setMaxResults(tamanio.orElse(5));

        List<Alumno> alumnos = query.getResultList();


        Map<String, Object> response = new HashMap<>();
        response.put("alumnos", alumnos);
        response.put("currentPage", pagina.orElse(0));
        response.put("totalItems", totalItems);
        response.put("totalPages", totalPages);

        return response;

    }

}
