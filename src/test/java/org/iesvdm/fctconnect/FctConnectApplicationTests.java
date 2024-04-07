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
    @Autowired
    AdministradorRepository administradorRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void cargaInicialBbdd() {
        Profesor profe1 = new Profesor();
        profe1.setNombre("Jose Manuel");
        profe1.setApellido1("Evangelizador");
        profe1.setApellido2("En Java");
        profe1.setEmail("jevang@email.com");
        profe1.setPassword("pass1234");
        this.profesorRepository.save(profe1);

        Profesor profe2 = new Profesor();
        profe2.setNombre("Eustaquio");
        profe2.setApellido1("Montañez");
        profe2.setApellido2("del Valle");
        this.profesorRepository.save(profe2);

        Profesor profe3 = new Profesor();
        profe3.setNombre("Jose");
        profe3.setApellido1("Rodríguez");
        profe3.setApellido2("de la Fuente");
        this.profesorRepository.save(profe3);

        Profesor profe4 = new Profesor();
        profe4.setNombre("Anacleto");
        profe4.setApellido1("García");
        profe4.setApellido2("Martínez");
        this.profesorRepository.save(profe4);

        Profesor profe5 = new Profesor();
        profe5.setNombre("Fausto");
        profe5.setApellido1("López");
        profe5.setApellido2("Sánchez");
        this.profesorRepository.save(profe5);

        Profesor profe6 = new Profesor();
        profe6.setNombre("Fulgencio");
        profe6.setApellido1("Martín");
        profe6.setApellido2("González");
        this.profesorRepository.save(profe6);

        Profesor profe7 = new Profesor();
        profe7.setNombre("Protasio");
        profe7.setApellido1("Hernández");
        profe7.setApellido2("Gutiérrez");
        this.profesorRepository.save(profe7);

        Profesor profe8 = new Profesor();
        profe8.setNombre("Pedrielectro");
        profe8.setApellido1("López");
        profe8.setApellido2("Gil");
        this.profesorRepository.save(profe8);

        Profesor profe9 = new Profesor();
        profe9.setNombre("Segismundo");
        profe9.setApellido1("Gómez");
        profe9.setApellido2("Serrano");
        this.profesorRepository.save(profe9);

        Profesor profe10 = new Profesor();
        profe10.setNombre("Bonifacio");
        profe10.setApellido1("Fernández");
        profe10.setApellido2("López");
        this.profesorRepository.save(profe10);



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
        alumno1.setNombre("Mireya");
        alumno1.setApellido1("Medalle");
        alumno1.setEmail("mireya@email.com");
        alumno1.setPassword("pass1234");
        Set<Idioma> idiomasAlu1 = new HashSet<>();
        idiomasAlu1.add(idioma1);
        idiomasAlu1.add(idioma3);
        alumno1.setIdiomas(idiomasAlu1);
        alumno1.setCarnetConducir(1L);
        alumno1.setVehiculoPropio(0L);
        this.alumnoRepository.save(alumno1);

        Alumno alumno2 = new Alumno();
        alumno2.setNombre("Pepito");
        alumno2.setApellido1("De los Palotes");
        Set<Idioma> idiomasAlu2 = new HashSet<>();
        idiomasAlu2.add(idioma1);
        idiomasAlu2.add(idioma2);
        idiomasAlu2.add(idioma3);
        alumno2.setIdiomas(idiomasAlu2);
        alumno1.setCarnetConducir(0L);
        alumno1.setVehiculoPropio(0L);
        this.alumnoRepository.save(alumno2);

        Alumno alumno3 = new Alumno();
        alumno3.setNombre("Paquito");
        alumno3.setApellido1("Perez");
        Set<Idioma> idiomasAlu3 = new HashSet<>();
        idiomasAlu3.add(idioma1);
        idiomasAlu3.add(idioma2);
        alumno3.setIdiomas(idiomasAlu3);
        alumno3.setCarnetConducir(0L);
        alumno3.setVehiculoPropio(0L);
        this.alumnoRepository.save(alumno3);

        Alumno alumno4 = new Alumno();
        alumno4.setNombre("Francisco");
        alumno4.setApellido1("Flores");
        Set<Idioma> idiomasAlu4 = new HashSet<>();
        idiomasAlu4.add(idioma2);
        idiomasAlu4.add(idioma3);
        alumno4.setIdiomas(idiomasAlu4);
        alumno4.setCarnetConducir(1L);
        alumno4.setVehiculoPropio(0L);
        this.alumnoRepository.save(alumno4);

        Alumno alumno5 = new Alumno();
        alumno5.setNombre("Antonio");
        alumno5.setApellido1("Antunez");
        Set<Idioma> idiomasAlu5 = new HashSet<>();
        idiomasAlu5.add(idioma1);
        idiomasAlu5.add(idioma3);
        alumno5.setIdiomas(idiomasAlu5);
        alumno5.setCarnetConducir(1L);
        alumno5.setVehiculoPropio(1L);
        this.alumnoRepository.save(alumno5);

        Alumno alumno6 = new Alumno();
        alumno6.setNombre("Esperanza");
        alumno6.setApellido1("Perales");
        Set<Idioma> idiomasAlu6 = new HashSet<>();
        idiomasAlu6.add(idioma3);
        alumno6.setIdiomas(idiomasAlu6);
        alumno6.setCarnetConducir(0L);
        alumno6.setVehiculoPropio(1L);
        this.alumnoRepository.save(alumno6);

        Alumno alumno7 = new Alumno();
        alumno7.setNombre("María");
        alumno7.setApellido1("Figueras");
        Set<Idioma> idiomasAlu7 = new HashSet<>();
        idiomasAlu7.add(idioma1);
        alumno7.setIdiomas(idiomasAlu7);
        alumno7.setCarnetConducir(1L);
        alumno7.setVehiculoPropio(0L);
        this.alumnoRepository.save(alumno7);

        Alumno alumno8 = new Alumno();
        alumno8.setNombre("Pamela");
        alumno8.setApellido1("Pabón");
        Set<Idioma> idiomasAlu8 = new HashSet<>();
        idiomasAlu8.add(idioma2);
        alumno8.setIdiomas(idiomasAlu8);
        alumno8.setCarnetConducir(0L);
        alumno8.setVehiculoPropio(1L);
        this.alumnoRepository.save(alumno8);

        Alumno alumno9 = new Alumno();
        alumno9.setNombre("Ángel");
        alumno9.setApellido1("Angulo");
        Set<Idioma> idiomasAlu9 = new HashSet<>();
        idiomasAlu9.add(idioma3);
        alumno9.setIdiomas(idiomasAlu9);
        alumno9.setCarnetConducir(0L);
        alumno9.setVehiculoPropio(0L);
        this.alumnoRepository.save(alumno9);

        Alumno alumno10 = new Alumno();
        alumno10.setNombre("Santiago");
        alumno10.setApellido1("Moreno");
        Set<Idioma> idiomasAlu10 = new HashSet<>();
        idiomasAlu10.add(idioma1);
        idiomasAlu10.add(idioma2);
        idiomasAlu10.add(idioma3);
        alumno10.setIdiomas(idiomasAlu10);
        alumno10.setCarnetConducir(1L);
        alumno10.setVehiculoPropio(1L);
        this.alumnoRepository.save(alumno10);

        Empresa empresa1 = new Empresa();
        empresa1.setNombre("Accenture");
        empresa1.setInglesSolicitado(EInglesSolicitado.IMPORTANTE.toString());
        empresa1.setModalidadTrabajo(EModalidadTrabajo.HIBRIDO.toString());
        empresaRepository.save(empresa1);

        Empresa empresa2 = new Empresa();
        empresa2.setNombre("Babel");
        empresa2.setInglesSolicitado(EInglesSolicitado.IMPORTANTE.toString());
        empresa2.setModalidadTrabajo(EModalidadTrabajo.HIBRIDO.toString());
        empresaRepository.save(empresa2);

        Empresa empresa3 = new Empresa();
        empresa3.setNombre("Indra");
        empresa3.setInglesSolicitado(EInglesSolicitado.NO_NECESARIO.toString());
        empresa3.setModalidadTrabajo(EModalidadTrabajo.HIBRIDO.toString());
        empresaRepository.save(empresa3);

        Empresa empresa4 = new Empresa();
        empresa4.setNombre("Innovatech");
        empresa4.setInglesSolicitado(EInglesSolicitado.IMPRESCINDIBLE.toString());
        empresa4.setModalidadTrabajo(EModalidadTrabajo.PRESENCIAL.toString());
        empresaRepository.save(empresa4);

        Empresa empresa5 = new Empresa();
        empresa5.setNombre("TechBridge");
        empresa5.setInglesSolicitado(EInglesSolicitado.IMPORTANTE.toString());
        empresa5.setModalidadTrabajo(EModalidadTrabajo.ONLINE.toString());
        empresaRepository.save(empresa5);

        Empresa empresa6 = new Empresa();
        empresa6.setNombre("TechWorks");
        empresa6.setInglesSolicitado(EInglesSolicitado.IMPORTANTE.toString());
        empresa6.setModalidadTrabajo(EModalidadTrabajo.HIBRIDO.toString());
        empresaRepository.save(empresa6);

        Empresa empresa7 = new Empresa();
        empresa7.setNombre("CloudConnect");
        empresa7.setInglesSolicitado(EInglesSolicitado.IMPRESCINDIBLE.toString());
        empresa7.setModalidadTrabajo(EModalidadTrabajo.PRESENCIAL.toString());
        empresaRepository.save(empresa7);

        Empresa empresa8 = new Empresa();
        empresa8.setNombre("DataMinds");
        empresa8.setInglesSolicitado(EInglesSolicitado.IMPORTANTE.toString());
        empresa8.setModalidadTrabajo(EModalidadTrabajo.ONLINE.toString());
        empresaRepository.save(empresa8);

        Empresa empresa9 = new Empresa();
        empresa9.setNombre("InTech");
        empresa9.setInglesSolicitado(EInglesSolicitado.IMPRESCINDIBLE.toString());
        empresa9.setModalidadTrabajo(EModalidadTrabajo.HIBRIDO.toString());
        empresaRepository.save(empresa9);

        Empresa empresa10 = new Empresa();
        empresa10.setNombre("WebSolutions");
        empresa10.setInglesSolicitado(EInglesSolicitado.IMPORTANTE.toString());
        empresa10.setModalidadTrabajo(EModalidadTrabajo.PRESENCIAL.toString());
        empresaRepository.save(empresa10);

        Solicitud solAlu1 = new Solicitud();
        solAlu1.setAlumno(alumno1);
        solAlu1.setEmpresa(empresa2);
        solAlu1.setTipo("De alumno a empresa");
        solAlu1.setDescripcion("Mireya, alumno1, solicita a empresa 2, Babel");
        this.solicitudRepository.save(solAlu1);

        Solicitud solEmp2 = new Solicitud();
        solEmp2.setAlumno(alumno2);
        solEmp2.setEmpresa(empresa1);
        solEmp2.setTipo("De empresa a alumno");
        solEmp2.setDescripcion("Babel, empresa2, solicita a alumno 2, Pepito");
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

        Administrador admin = new Administrador();
        admin.setNombre("Adeministrador");
        admin.setEmail("admin@email.com");
        admin.setPassword("pass1234");
        this.administradorRepository.save(admin);


    }


}
