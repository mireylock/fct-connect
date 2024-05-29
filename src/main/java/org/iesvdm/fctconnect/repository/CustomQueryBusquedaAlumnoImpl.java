package org.iesvdm.fctconnect.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.iesvdm.fctconnect.domain.Alumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Slf4j
@Repository
public class CustomQueryBusquedaAlumnoImpl implements CustomQueryBusquedaAlumno {
    @Autowired
    private EntityManager em;


    @Override
    public Map<String, Object> buscarAlumnoPaginacion(Optional<String> nombre,
                                                      Optional<Boolean> vehiculoPropio,
                                                      Optional<String> idioma,
                                                      Optional<Integer> pagina,
                                                      Optional<Integer> tamanio) {
        String queryStr = "select A from Alumno A";
        String countQueryStr = "select count(*) from Alumno A";

        if ((nombre.isPresent() && !nombre.get().isEmpty() || vehiculoPropio.isPresent())
                && (idioma.isEmpty() || idioma.get().isEmpty())) {
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
