package org.iesvdm.fctconnect;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.fctconnect.domain.*;
import org.iesvdm.fctconnect.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;


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

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AsignaturaRepository asignaturaRepository;

    @Autowired
    TecnologiaRepository tecnologiaRepository;

    @Autowired
    UbicacionRepository ubicacionRepository;


    @Test
    void contextLoads() {
    }

    @Test
    void cargaInicialBbdd() {
        Administrador admin = new Administrador();
        admin.setNombre("Admin");
        admin.setApellido1("Istrador");
        admin.setEmail("admin@mail.com");
        admin.setDni("123456789A");
        admin.setTelefono("+34 123456789");
        admin.setPassword(this.encoder.encode("123456"));
        this.administradorRepository.save(admin);

        Asignatura entornos = new Asignatura();
        entornos.setNombre("Entornos del desarrollo");
        entornos.setCurso("2");
        entornos.setGrupo("DAW");
        this.asignaturaRepository.save(entornos);

        Asignatura hlc = new Asignatura();
        hlc.setNombre("HLC");
        hlc.setCurso("2");
        hlc.setGrupo("DAW");
        this.asignaturaRepository.save(hlc);

        Profesor profe1 = new Profesor();
        profe1.setNombre("Jose Manuel");
        profe1.setApellido1("Evangelizador");
        profe1.setApellido2("En Java");
        profe1.setEmail("jose@mail.com");
        profe1.setDni("123456789B");
        profe1.setTelefono("+34 123456789");
        profe1.setDepartamento("Departamento1");
        profe1.setPassword(this.encoder.encode("123456"));
        Set<Asignatura> asignaturasProfe1 = new HashSet<>();
        asignaturasProfe1.add(hlc);
        asignaturasProfe1.add(entornos);
        profe1.setAsignaturas(asignaturasProfe1);
        this.profesorRepository.save(profe1);

        Profesor profe2 = new Profesor();
        profe2.setNombre("Carlos");
        profe2.setApellido1("Vela");
        profe2.setEmail("carlos@mail.com");
        profe2.setDni("123456789C");
        profe2.setTelefono("+34 123456789");
        profe2.setDepartamento("Departamento1");
        profe2.setPassword(this.encoder.encode("123456"));
        Set<Asignatura> asignaturasProfe2 = new HashSet<>();
        asignaturasProfe2.add(hlc);
        profe2.setAsignaturas(asignaturasProfe2);
        this.profesorRepository.save(profe2);


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
        alumno1.setEmail("mireya@mail.com");
        alumno1.setDni("123456789D");
        alumno1.setTelefono("+34 123456789");
        alumno1.setPassword(this.encoder.encode("123456"));
        Set<Idioma> idiomasAlu1 = new HashSet<>();
        idiomasAlu1.add(idioma1);
        idiomasAlu1.add(idioma3);
        alumno1.setIdiomas(idiomasAlu1);
        alumno1.setCarnetConducir(1L);
        alumno1.setVehiculoPropio(0L);
        this.alumnoRepository.save(alumno1);

        Tecnologia javaSpring = new Tecnologia();
        javaSpring.setNombre("Java en Spring Boot");
        javaSpring.setNombre("Para un proyecto del banco ING");
        this.tecnologiaRepository.save(javaSpring);

        Tecnologia angular = new Tecnologia();
        angular.setNombre("Angular 17");
        this.tecnologiaRepository.save(angular);

        Empresa empresa1 = new Empresa();
        empresa1.setNombre("Accenture");
        empresa1.setEmail("acc@mail.com");
        empresa1.setPassword(this.encoder.encode("123456"));
        empresa1.setInglesSolicitado(EInglesSolicitado.IMPORTANTE);
        Set<EModalidadTrabajo> modalidadesTrabajoEmp1 = new HashSet<>();
        modalidadesTrabajoEmp1.add(EModalidadTrabajo.HIBRIDO);
        modalidadesTrabajoEmp1.add(EModalidadTrabajo.PRESENCIAL);
        empresa1.setModalidadesTrabajo(modalidadesTrabajoEmp1);
        empresa1.setResumen("Empresa consultora");
        empresa1.setPassword(this.encoder.encode("123456"));
        Set<Tecnologia> tecnologiasEmpr1 = new HashSet<>();
        tecnologiasEmpr1.add(javaSpring);
        tecnologiasEmpr1.add(angular);
        empresa1.setTecnologias(tecnologiasEmpr1);
        empresaRepository.save(empresa1);

        Ubicacion ubi1emp1 = new Ubicacion();
        ubi1emp1.setPais("España");
        ubi1emp1.setCiudad("Málaga");
        ubi1emp1.setDireccion("Calle Severo Ochoa, 8");
        ubi1emp1.setCodigoPostal("29002");
        ubi1emp1.setEmpresa(empresa1);
        this.ubicacionRepository.save(ubi1emp1);
        

        Empresa empresa2 = new Empresa();
        empresa2.setNombre("Babel");
        empresa2.setEmail("babel@mail.com");
        empresa2.setPassword(this.encoder.encode("123456"));
        empresa2.setInglesSolicitado(EInglesSolicitado.IMPRESCINDIBLE);
        Set<EModalidadTrabajo> modalidadesTrabajoEmp2 = new HashSet<>();
        modalidadesTrabajoEmp2.add(EModalidadTrabajo.HIBRIDO);
        modalidadesTrabajoEmp2.add(EModalidadTrabajo.ONLINE);
        modalidadesTrabajoEmp2.add(EModalidadTrabajo.PRESENCIAL);
        empresa2.setModalidadesTrabajo(modalidadesTrabajoEmp2);
        empresa2.setResumen("Empresa consultora");
        Set<Tecnologia> tecnologiasEmpr2 = new HashSet<>();
        tecnologiasEmpr2.add(javaSpring);
        empresa2.setTecnologias(tecnologiasEmpr2);
        empresaRepository.save(empresa2);

        Ubicacion ubi1emp2 = new Ubicacion();
        ubi1emp2.setPais("España");
        ubi1emp2.setCiudad("Málaga");
        ubi1emp2.setDireccion("Calle Severo Ochoa, 8");
        ubi1emp2.setCodigoPostal("29002");
        ubi1emp2.setEmpresa(empresa2);
        this.ubicacionRepository.save(ubi1emp2);

        Ubicacion ubi2emp2 = new Ubicacion();
        ubi2emp2.setPais("España");
        ubi2emp2.setCiudad("Sevilla");
        ubi2emp2.setDireccion("Calle Mairena Aljarafe, 8");
        ubi2emp2.setCodigoPostal("46000");
        ubi2emp2.setEmpresa(empresa2);
        this.ubicacionRepository.save(ubi2emp2);

        Empresa empresa3 = new Empresa();
        empresa3.setNombre("Indra");
        empresa3.setEmail("indra@mail.com");
        empresa3.setPassword(this.encoder.encode("123456"));
        empresa3.setInglesSolicitado(EInglesSolicitado.NO_NECESARIO);
        Set<EModalidadTrabajo> modalidadesTrabajoEmp3 = new HashSet<>();
        modalidadesTrabajoEmp3.add(EModalidadTrabajo.HIBRIDO);
        modalidadesTrabajoEmp3.add(EModalidadTrabajo.ONLINE);
        empresa3.setModalidadesTrabajo(modalidadesTrabajoEmp3);
        empresa3.setResumen("Empresa consultora");
        empresaRepository.save(empresa3);

        Solicitud solAlu1 = new Solicitud();
        solAlu1.setAlumno(alumno1);
        solAlu1.setEmpresa(empresa2);
        solAlu1.setTipo(ETipoSolicitud.ALUMNO_A_EMPRESA);
        solAlu1.setDescripcion("Mireya, alumno1, solicita a empresa 2, Babel");
        solAlu1.setEstado(EEstadoSolicitud.ENVIADA);
        this.solicitudRepository.save(solAlu1);

        Solicitud solEmp2 = new Solicitud();
        solEmp2.setAlumno(alumno1);
        solEmp2.setEmpresa(empresa1);
        solEmp2.setTipo(ETipoSolicitud.EMPRESA_A_ALUMNO);
        solEmp2.setDescripcion("Accenture, empresa1, solicita a alumno 1, Mireya");
        solEmp2.setEstado(EEstadoSolicitud.RECHAZADA);
        this.solicitudRepository.save(solEmp2);

        ProfesorTutorizaAlumno tutoria1 = new ProfesorTutorizaAlumno();
        tutoria1.setProfesor(profe1);
        tutoria1.setAlumno(alumno1);
        tutoria1.setTipoTutoria("Practicas");
        this.profesorTutorizaAlumnoRepository.save(tutoria1);

        ProfesorTutorizaAlumno tutoria2 = new ProfesorTutorizaAlumno();
        tutoria2.setProfesor(profe2);
        tutoria2.setAlumno(alumno1);
        tutoria2.setTipoTutoria("Proyecto");
        this.profesorTutorizaAlumnoRepository.save(tutoria2);
    }

//    @Test
//    void cargaSinValidacion() {
//        Profesor profe1 = new Profesor();
//        profe1.setNombre("Antonio");
//        profe1.setApellido1("Ramos");
//        profe1.setApellido2("Flores");
//        profe1.setEmail("aramflo@email");
//        profe1.setPassword(this.encoder.encode("123456"));
//        this.profesorRepository.save(profe1);
//
//
//        Idioma idioma1 = new Idioma();
//        idioma1.setNombre("Inglés");
//        idiomaRepository.save(idioma1);
//
//        Idioma idioma2 = new Idioma();
//        idioma2.setNombre("Francés");
//        idiomaRepository.save(idioma2);
//
//        Idioma idioma3 = new Idioma();
//        idioma3.setNombre("Alemán");
//        idiomaRepository.save(idioma3);
//
//        Alumno alumno1 = new Alumno();
//        alumno1.setNombre("Mireya");
//        alumno1.setApellido1("Medalle");
//        alumno1.setEmail("mireya@email.com");
//        alumno1.setPassword(this.encoder.encode("123456"));
//        Set<Idioma> idiomasAlu1 = new HashSet<>();
//        idiomasAlu1.add(idioma1);
//        idiomasAlu1.add(idioma3);
//        alumno1.setIdiomas(idiomasAlu1);
//        alumno1.setCarnetConducir(1L);
//        alumno1.setVehiculoPropio(0L);
//        this.alumnoRepository.save(alumno1);
//
//        Alumno alumno2 = new Alumno();
//        alumno2.setNombre("Pepito");
//        alumno2.setApellido1("De los Palotes");
//        Set<Idioma> idiomasAlu2 = new HashSet<>();
//        idiomasAlu2.add(idioma1);
//        idiomasAlu2.add(idioma2);
//        idiomasAlu2.add(idioma3);
//        alumno2.setIdiomas(idiomasAlu2);
//        alumno1.setCarnetConducir(0L);
//        alumno1.setVehiculoPropio(0L);
//        this.alumnoRepository.save(alumno2);
//
//        Alumno alumno3 = new Alumno();
//        alumno3.setNombre("Paquito");
//        alumno3.setApellido1("Perez");
//        Set<Idioma> idiomasAlu3 = new HashSet<>();
//        idiomasAlu3.add(idioma1);
//        idiomasAlu3.add(idioma2);
//        alumno3.setIdiomas(idiomasAlu3);
//        alumno3.setCarnetConducir(0L);
//        alumno3.setVehiculoPropio(0L);
//        this.alumnoRepository.save(alumno3);
//
//        Alumno alumno4 = new Alumno();
//        alumno4.setNombre("Francisco");
//        alumno4.setApellido1("Flores");
//        Set<Idioma> idiomasAlu4 = new HashSet<>();
//        idiomasAlu4.add(idioma2);
//        idiomasAlu4.add(idioma3);
//        alumno4.setIdiomas(idiomasAlu4);
//        alumno4.setCarnetConducir(1L);
//        alumno4.setVehiculoPropio(0L);
//        this.alumnoRepository.save(alumno4);
//
//        Alumno alumno5 = new Alumno();
//        alumno5.setNombre("Antonio");
//        alumno5.setApellido1("Antunez");
//        Set<Idioma> idiomasAlu5 = new HashSet<>();
//        idiomasAlu5.add(idioma1);
//        idiomasAlu5.add(idioma3);
//        alumno5.setIdiomas(idiomasAlu5);
//        alumno5.setCarnetConducir(1L);
//        alumno5.setVehiculoPropio(1L);
//        this.alumnoRepository.save(alumno5);
//
//        Alumno alumno6 = new Alumno();
//        alumno6.setNombre("Esperanza");
//        alumno6.setApellido1("Perales");
//        Set<Idioma> idiomasAlu6 = new HashSet<>();
//        idiomasAlu6.add(idioma3);
//        alumno6.setIdiomas(idiomasAlu6);
//        alumno6.setCarnetConducir(0L);
//        alumno6.setVehiculoPropio(1L);
//        this.alumnoRepository.save(alumno6);
//
//        Alumno alumno7 = new Alumno();
//        alumno7.setNombre("María");
//        alumno7.setApellido1("Figueras");
//        Set<Idioma> idiomasAlu7 = new HashSet<>();
//        idiomasAlu7.add(idioma1);
//        alumno7.setIdiomas(idiomasAlu7);
//        alumno7.setCarnetConducir(1L);
//        alumno7.setVehiculoPropio(0L);
//        this.alumnoRepository.save(alumno7);
//
//        Alumno alumno8 = new Alumno();
//        alumno8.setNombre("Pamela");
//        alumno8.setApellido1("Pabón");
//        Set<Idioma> idiomasAlu8 = new HashSet<>();
//        idiomasAlu8.add(idioma2);
//        alumno8.setIdiomas(idiomasAlu8);
//        alumno8.setCarnetConducir(0L);
//        alumno8.setVehiculoPropio(1L);
//        this.alumnoRepository.save(alumno8);
//
//        Alumno alumno9 = new Alumno();
//        alumno9.setNombre("Ángel");
//        alumno9.setApellido1("Angulo");
//        Set<Idioma> idiomasAlu9 = new HashSet<>();
//        idiomasAlu9.add(idioma3);
//        alumno9.setIdiomas(idiomasAlu9);
//        alumno9.setCarnetConducir(0L);
//        alumno9.setVehiculoPropio(0L);
//        this.alumnoRepository.save(alumno9);
//
//        Alumno alumno10 = new Alumno();
//        alumno10.setNombre("Santiago");
//        alumno10.setApellido1("Moreno");
//        Set<Idioma> idiomasAlu10 = new HashSet<>();
//        idiomasAlu10.add(idioma1);
//        idiomasAlu10.add(idioma2);
//        idiomasAlu10.add(idioma3);
//        alumno10.setIdiomas(idiomasAlu10);
//        alumno10.setCarnetConducir(1L);
//        alumno10.setVehiculoPropio(1L);
//        this.alumnoRepository.save(alumno10);
//
//        Empresa empresa1 = new Empresa();
//        empresa1.setNombre("Accenture");
//        empresa1.setInglesSolicitado(EInglesSolicitado.IMPORTANTE.toString());
//        empresa1.setModalidadTrabajo(EModalidadTrabajo.HIBRIDO.toString());
//        empresa1.setPassword(this.encoder.encode("123456"));
//        empresaRepository.save(empresa1);
//
//        Empresa empresa2 = new Empresa();
//        empresa2.setNombre("Babel");
//        empresa2.setInglesSolicitado(EInglesSolicitado.IMPORTANTE.toString());
//        empresa2.setModalidadTrabajo(EModalidadTrabajo.HIBRIDO.toString());
//        empresaRepository.save(empresa2);
//
//        Empresa empresa3 = new Empresa();
//        empresa3.setNombre("Indra");
//        empresa3.setInglesSolicitado(EInglesSolicitado.NO_NECESARIO.toString());
//        empresa3.setModalidadTrabajo(EModalidadTrabajo.HIBRIDO.toString());
//        empresaRepository.save(empresa3);
//
//        Empresa empresa4 = new Empresa();
//        empresa4.setNombre("Innovatech");
//        empresa4.setInglesSolicitado(EInglesSolicitado.IMPRESCINDIBLE.toString());
//        empresa4.setModalidadTrabajo(EModalidadTrabajo.PRESENCIAL.toString());
//        empresaRepository.save(empresa4);
//
//        Empresa empresa5 = new Empresa();
//        empresa5.setNombre("TechBridge");
//        empresa5.setInglesSolicitado(EInglesSolicitado.IMPORTANTE.toString());
//        empresa5.setModalidadTrabajo(EModalidadTrabajo.ONLINE.toString());
//        empresaRepository.save(empresa5);
//
//        Empresa empresa6 = new Empresa();
//        empresa6.setNombre("TechWorks");
//        empresa6.setInglesSolicitado(EInglesSolicitado.IMPORTANTE.toString());
//        empresa6.setModalidadTrabajo(EModalidadTrabajo.HIBRIDO.toString());
//        empresaRepository.save(empresa6);
//
//        Empresa empresa7 = new Empresa();
//        empresa7.setNombre("CloudConnect");
//        empresa7.setInglesSolicitado(EInglesSolicitado.IMPRESCINDIBLE.toString());
//        empresa7.setModalidadTrabajo(EModalidadTrabajo.PRESENCIAL.toString());
//        empresaRepository.save(empresa7);
//
//        Empresa empresa8 = new Empresa();
//        empresa8.setNombre("DataMinds");
//        empresa8.setInglesSolicitado(EInglesSolicitado.IMPORTANTE.toString());
//        empresa8.setModalidadTrabajo(EModalidadTrabajo.ONLINE.toString());
//        empresaRepository.save(empresa8);
//
//        Empresa empresa9 = new Empresa();
//        empresa9.setNombre("InTech");
//        empresa9.setInglesSolicitado(EInglesSolicitado.IMPRESCINDIBLE.toString());
//        empresa9.setModalidadTrabajo(EModalidadTrabajo.HIBRIDO.toString());
//        empresaRepository.save(empresa9);
//
//        Empresa empresa10 = new Empresa();
//        empresa10.setNombre("WebSolutions");
//        empresa10.setInglesSolicitado(EInglesSolicitado.IMPORTANTE.toString());
//        empresa10.setModalidadTrabajo(EModalidadTrabajo.PRESENCIAL.toString());
//        empresaRepository.save(empresa10);
//
//        Solicitud solAlu1 = new Solicitud();
//        solAlu1.setAlumno(alumno1);
//        solAlu1.setEmpresa(empresa2);
//        solAlu1.setTipo("De alumno a empresa");
//        solAlu1.setDescripcion("Mireya, alumno1, solicita a empresa 2, Babel");
//        this.solicitudRepository.save(solAlu1);
//
//        Solicitud solEmp2 = new Solicitud();
//        solEmp2.setAlumno(alumno2);
//        solEmp2.setEmpresa(empresa1);
//        solEmp2.setTipo("De empresa a alumno");
//        solEmp2.setDescripcion("Babel, empresa2, solicita a alumno 2, Pepito");
//        this.solicitudRepository.save(solEmp2);
//
//        ProfesorTutorizaAlumno tutoria1 = new ProfesorTutorizaAlumno();
//        tutoria1.setProfesor(profe1);
//        tutoria1.setAlumno(alumno1);
//        tutoria1.setTipoTutoria("Proyecto");
//        this.profesorTutorizaAlumnoRepository.save(tutoria1);
//
//        ProfesorTutorizaAlumno tutoria2 = new ProfesorTutorizaAlumno();
//        tutoria2.setProfesor(profe1);
//        tutoria2.setAlumno(alumno1);
//        tutoria2.setTipoTutoria("Practicas");
//        this.profesorTutorizaAlumnoRepository.save(tutoria2);
//
//        ProfesorTutorizaAlumno tutoria3 = new ProfesorTutorizaAlumno();
//        tutoria3.setProfesor(profe1);
//        tutoria3.setAlumno(alumno2);
//        tutoria3.setTipoTutoria("Practicas");
//        this.profesorTutorizaAlumnoRepository.save(tutoria3);
//
//        Administrador admin = new Administrador();
//        admin.setNombre("Adeministrador");
//        admin.setEmail("admin@email.com");
//        admin.setPassword(this.encoder.encode("123456"));
//        this.administradorRepository.save(admin);
//
//
//    }


}
