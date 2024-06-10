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
                                                      Optional<Long> idioma,
                                                      Optional<Integer> pagina,
                                                      Optional<Integer> tamanio) {
        StringBuilder queryStr = new StringBuilder("select A from Alumno A");
        StringBuilder countQueryStr = new StringBuilder("select count(A) from Alumno A");

        if (idioma.isPresent()) {
            queryStr.append(" join A.idiomas.idioma I");
            countQueryStr.append(" join A.idiomas.idioma I");
        }

        queryStr.append(" where A.activo = true");
        countQueryStr.append(" where A.activo = true");

        if (nombre.isPresent() && !nombre.get().isEmpty()) {
            queryStr.append(" and CONCAT(A.nombre, ' ', A.apellido1, ' ', COALESCE(A.apellido2, '')) LIKE CONCAT('%', :nombre, '%')");
            countQueryStr.append(" and CONCAT(A.nombre, ' ', A.apellido1, ' ', COALESCE(A.apellido2, '')) LIKE CONCAT('%', :nombre, '%')");
        }

        if (vehiculoPropio.isPresent()) {
            queryStr.append(" and A.vehiculoPropio = :vehiculoPropio");
            countQueryStr.append(" and A.vehiculoPropio = :vehiculoPropio");
        }

        if (idioma.isPresent()) {
            queryStr.append(" and I.id = :idioma");
            countQueryStr.append(" and I.id = :idioma");
        }

        log.info("LA QUERY ES: " + queryStr);

        Query query = em.createQuery(queryStr.toString(), Alumno.class);
        Query countQuery = em.createQuery(countQueryStr.toString(), Long.class);

        if (nombre.isPresent() && !nombre.get().isEmpty()) query.setParameter("nombre", nombre.get());
        if (vehiculoPropio.isPresent()) query.setParameter("vehiculoPropio", vehiculoPropio.get());
        if (idioma.isPresent()) query.setParameter("idioma", idioma.get());

        if (nombre.isPresent() && !nombre.get().isEmpty()) countQuery.setParameter("nombre", nombre.get());
        if (vehiculoPropio.isPresent()) countQuery.setParameter("vehiculoPropio", vehiculoPropio.get());
        if (idioma.isPresent()) countQuery.setParameter("idioma", idioma.get());

        long totalItems = (Long) countQuery.getSingleResult();
        long totalPages = 1;
        if (tamanio.isPresent() && tamanio.get() != 0) {
            double paginas = (double) totalItems / tamanio.get();
            totalPages = (long) Math.ceil(paginas);
        }

        query.setFirstResult(pagina.orElse(0) * tamanio.orElse(1));
        query.setMaxResults(tamanio.orElse(6));

        List<Alumno> alumnos = query.getResultList();

        Map<String, Object> response = new HashMap<>();
        response.put("alumnos", alumnos);
        response.put("currentPage", pagina.orElse(0));
        response.put("totalItems", totalItems);
        response.put("totalPages", totalPages);

        return response;
    }

    @Override
    public Map<String, Object> buscarAlumnoInactivosPaginacion(Optional<String> nombre, Optional<Boolean> vehiculoPropio, Optional<Long> idioma, Optional<Integer> pagina, Optional<Integer> tamanio) {
        StringBuilder queryStr = new StringBuilder("select A from Alumno A");
        StringBuilder countQueryStr = new StringBuilder("select count(A) from Alumno A");

        if (idioma.isPresent()) {
            queryStr.append(" join A.idiomas.idioma I");
            countQueryStr.append(" join A.idiomas.idioma I");
        }

        queryStr.append(" where A.activo = false");
        countQueryStr.append(" where A.activo = false");

        if (nombre.isPresent() && !nombre.get().isEmpty()) {
            queryStr.append(" and CONCAT(A.nombre, ' ', A.apellido1, ' ', COALESCE(A.apellido2, '')) LIKE CONCAT('%', :nombre, '%')");
            countQueryStr.append(" and CONCAT(A.nombre, ' ', A.apellido1, ' ', COALESCE(A.apellido2, '')) LIKE CONCAT('%', :nombre, '%')");
        }

        if (vehiculoPropio.isPresent()) {
            queryStr.append(" and A.vehiculoPropio = :vehiculoPropio");
            countQueryStr.append(" and A.vehiculoPropio = :vehiculoPropio");
        }

        if (idioma.isPresent()) {
            queryStr.append(" and I.id = :idioma");
            countQueryStr.append(" and I.id = :idioma");
        }

        log.info("LA QUERY ES: " + queryStr);

        Query query = em.createQuery(queryStr.toString(), Alumno.class);
        Query countQuery = em.createQuery(countQueryStr.toString(), Long.class);

        if (nombre.isPresent() && !nombre.get().isEmpty()) query.setParameter("nombre", nombre.get());
        if (vehiculoPropio.isPresent()) query.setParameter("vehiculoPropio", vehiculoPropio.get());
        if (idioma.isPresent()) query.setParameter("idioma", idioma.get());

        if (nombre.isPresent() && !nombre.get().isEmpty()) countQuery.setParameter("nombre", nombre.get());
        if (vehiculoPropio.isPresent()) countQuery.setParameter("vehiculoPropio", vehiculoPropio.get());
        if (idioma.isPresent()) countQuery.setParameter("idioma", idioma.get());

        long totalItems = (Long) countQuery.getSingleResult();
        long totalPages = 1;
        if (tamanio.isPresent() && tamanio.get() != 0) {
            double paginas = (double) totalItems / tamanio.get();
            totalPages = (long) Math.ceil(paginas);
        }

        query.setFirstResult(pagina.orElse(0) * tamanio.orElse(1));
        query.setMaxResults(tamanio.orElse(6));

        List<Alumno> alumnos = query.getResultList();

        Map<String, Object> response = new HashMap<>();
        response.put("alumnos", alumnos);
        response.put("currentPage", pagina.orElse(0));
        response.put("totalItems", totalItems);
        response.put("totalPages", totalPages);

        return response;
    }

}
