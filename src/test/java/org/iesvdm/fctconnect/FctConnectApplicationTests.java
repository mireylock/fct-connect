package org.iesvdm.fctconnect;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.fctconnect.domain.*;
import org.iesvdm.fctconnect.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;


@Slf4j
@SpringBootTest
class FctConnectApplicationTests {
    @Autowired
    ProfesorRepository profesorRepository;
    @Autowired
    AlumnoRepository alumnoRepository;
    @Autowired
    EmpresaRepository empresaRepository;
    @Autowired
    SolicitudRepository solicitudRepository;
    @Autowired
    ProfesorTutorizaAlumnoRepository profesorTutorizaAlumnoRepository;
    @Autowired
    IdiomaRepository idiomaRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void crearProfesorAlumno() {
        Profesor profe1 = new Profesor();
        profe1.setNombre("John");
        profe1.setApellido1("Keating");
        this.profesorRepository.save(profe1);

        Empresa empresa1 = new Empresa();
        empresa1.setNombre("Accenture");
        empresa1.setInglesSolicitado("importante");
        empresa1.setModalidadTrabajo("presencial");
        this.empresaRepository.save(empresa1);

        Empresa empresa2 = new Empresa();
        empresa2.setNombre("Babel");
        empresa2.setInglesSolicitado("importante");
        empresa2.setModalidadTrabajo("hibrido");
        this.empresaRepository.save(empresa2);

        Idioma idioma1 = new Idioma();
        idioma1.setNombre("Inglés");
        idiomaRepository.save(idioma1);

        Idioma idioma2 = new Idioma();
        idioma2.setNombre("Francés");
        idiomaRepository.save(idioma2);

        Idioma idioma3 = new Idioma();
        idioma3.setNombre("Alemán");
        idiomaRepository.save(idioma3);

        Alumno alumno1 = new Alumno();
        alumno1.setNombre("Todd");
        alumno1.setApellido1("Anderson");
        Set<Idioma> idiomasAlu1 = new HashSet<>();
        idiomasAlu1.add(idioma1);
        idiomasAlu1.add(idioma2);
        alumno1.setIdiomas(idiomasAlu1);
        this.alumnoRepository.save(alumno1);

        Alumno alumno2 = new Alumno();
        alumno2.setNombre("Neil");
        alumno2.setApellido1("Perry");
        Set<Idioma> idiomasAlu2 = new HashSet<>();
        idiomasAlu2.add(idioma1);
        idiomasAlu2.add(idioma2);
        idiomasAlu2.add(idioma3);
        alumno2.setIdiomas(idiomasAlu2);
        this.alumnoRepository.save(alumno2);

        Solicitud solAlu1 = new Solicitud();
        solAlu1.setAlumno(alumno1);
        solAlu1.setEmpresa(empresa1);
        solAlu1.setTipo("De alumno a empresa");
        solAlu1.setDescripcion("Todd, alumno1, solicita a empresa 1, Acccenture");
        this.solicitudRepository.save(solAlu1);

        Solicitud solEmp2 = new Solicitud();
        solEmp2.setAlumno(alumno1);
        solEmp2.setEmpresa(empresa2);
        solEmp2.setTipo("De empresa a alumno");
        solEmp2.setDescripcion("Babel, empresa2, solicita a alumno 2, Neil");
        this.solicitudRepository.save(solEmp2);

        ProfesorTutorizaAlumno tutoria1 = new ProfesorTutorizaAlumno();
        tutoria1.setProfesor(profe1);
        tutoria1.setAlumno(alumno1);
        tutoria1.setTipoTutoria("Proyecto");
        this.profesorTutorizaAlumnoRepository.save(tutoria1);


        ProfesorTutorizaAlumno tutoria2 = new ProfesorTutorizaAlumno();
        tutoria2.setProfesor(profe1);
        tutoria2.setAlumno(alumno1);
        tutoria2.setTipoTutoria("Practicas");
        this.profesorTutorizaAlumnoRepository.save(tutoria2);

        ProfesorTutorizaAlumno tutoria3 = new ProfesorTutorizaAlumno();
        tutoria3.setProfesor(profe1);
        tutoria3.setAlumno(alumno2);
        tutoria3.setTipoTutoria("Practicas");
        this.profesorTutorizaAlumnoRepository.save(tutoria3);

    }


}
