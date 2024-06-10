package org.iesvdm.fctconnect.repository;

import org.iesvdm.fctconnect.domain.Profesor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

//    @Query("SELECT P FROM Profesor P JOIN P.profesorTutorizaAlumnos t "+
//    "WHERE :idAlumno = t.alumno.id")
//    public List<Profesor> profesoresDeUnAlumno(@Param("idAlumno") long idAlumno);

    List<Profesor> findAllByActivoIsTrue();

    List<Profesor> findAllByActivoIsFalse();

    @Query(value = "select P from Profesor P where CONCAT(P.nombre, ' ', P.apellido1, ' ', COALESCE(P.apellido2, '')) LIKE CONCAT('%', :nombre, '%')")
    public Page<Profesor> findProfesorByNombreCompleto(String nombre, Pageable pageable);


//    public List<Profesor> findProfesorByNombreContainingIgnoreCaseOrderByNombreAsc(String order);
//    public List<Profesor> findProfesorByNombreContainingIgnoreCaseOrderByNombreDesc(String order);
//    public List<Profesor> findAllByOrderByNombreAsc();
//    public List<Profesor> findAllByOrderByNombreDesc();
//    public List<Profesor> findProfesorByNombreContainingIgnoreCase(String nombre);
////    public List<Profesor> findProfesorByNombreContainingIgnoreCaseAAndApellido1ContainingIgnoreCaseAndApellido2NotContainingIgnoreCaseOrderByNombreAsc(Optional<String> nombreOpt, Optional<String> apellido1Opt, Optional<String> apellido2Opt, Optional<String> orderOpt);
//    public List<Profesor> findProfesorByNombreContainingIgnoreCaseAAndApellido1ContainingIgnoreCaseAndApellido2NotContainingIgnoreCaseOrderByNombreDesc(Optional<String> nombreOpt, Optional<String> apellido1Opt, Optional<String> apellido2Opt, Optional<String> orderOpt);


}
