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

    @Autowired
    FormacionRepository formacionRepository;


    @Test
    void contextLoads() {
    }

    @Test
    void cargaInicialBbdd() {
        Administrador admin = Administrador.builder()
                .nombre("Admin")
                .apellido1("Istrador")
                .email("admin@mail.com")
                .dni("123456789A")
                .telefono("+34 123456789")
                .password(this.encoder.encode("123456"))
                .pathFoto("../../../../assets/img/profile.png")
                .build();
        this.administradorRepository.save(admin);

        Asignatura entornos = Asignatura.builder()
                .nombre("Entornos del desarrollo")
                .curso("2")
                .grupo("DAW")
                .build();
        this.asignaturaRepository.save(entornos);

        Asignatura hlc = Asignatura.builder()
                .nombre("HLC")
                .curso("2")
                .grupo("DAW")
                .build();
        this.asignaturaRepository.save(hlc);

        Profesor profe1 = Profesor.builder()
                .nombre("Jose Manuel")
                .apellido1("Evangelizador")
                .apellido2("En Java")
                .email("jose@mail.com")
                .dni("123456789B")
                .telefono("+34 123456789")
                .departamento("Departamento1")
                .password(this.encoder.encode("123456"))
                .asignaturas(new HashSet<>(Set.of(hlc, entornos)))
                .pathFoto("../../../../assets/img/profile.png")
                .build();
        this.profesorRepository.save(profe1);

        Profesor profe2 = Profesor.builder()
                .nombre("Carlos")
                .apellido1("Vela")
                .email("carlos@mail.com")
                .dni("123456789C")
                .telefono("+34 123456789")
                .departamento("Departamento1")
                .password(this.encoder.encode("123456"))
                .asignaturas(new HashSet<>(Set.of(hlc)))
                .pathFoto("../../../../assets/img/profile.png")
                .build();
        this.profesorRepository.save(profe2);

        Idioma idioma1 = Idioma.builder()
                .nombre("Inglés")
                .build();
        idiomaRepository.save(idioma1);

        Idioma idioma2 = Idioma.builder()
                .nombre("Francés")
                .build();
        idiomaRepository.save(idioma2);

        Idioma idioma3 = Idioma.builder()
                .nombre("Alemán")
                .build();
        idiomaRepository.save(idioma3);

        Formacion DAW = Formacion.builder()
                .nombre("Desarrollo de Aplicaciones Web")
                .nivel(ENivelFormacion.GRADO_SUPERIOR)
                .build();
        formacionRepository.save(DAW);

        Formacion DAM = Formacion.builder()
                .nombre("Desarrollo de Aplicaciones Multiplataforma")
                .nivel(ENivelFormacion.GRADO_SUPERIOR)
                .build();
        formacionRepository.save(DAM);

        Formacion SMR = Formacion.builder()
                .nombre("Sistemas Microinformáticos y en Red")
                .nivel(ENivelFormacion.GRADO_MEDIO)
                .build();
        formacionRepository.save(SMR);

        Alumno alumno1 = Alumno.builder()
                .nombre("Mireya")
                .apellido1("Medalle")
                .apellido2("Merino")
                .email("mireya@mail.com")
                .dni("123456789D")
                .telefono("+34 123456789")
                .password(this.encoder.encode("123456"))
                .idiomas(Set.of(idioma1, idioma3))
                .carnetConducir(1L)
                .vehiculoPropio(0L)
                .formacion(DAW)
                .pathFoto("../../../../assets/img/profile.png")
                .build();
        this.alumnoRepository.save(alumno1);

        Alumno alumno2 = Alumno.builder()
                .nombre("Juan")
                .apellido1("García")
                .email("juan@mail.com")
                .dni("123456789E")
                .telefono("+34 123456789")
                .password(this.encoder.encode("123456"))
                .idiomas(Set.of(idioma1, idioma2))
                .carnetConducir(1L)
                .vehiculoPropio(1L)
                .formacion(DAM)
                .pathFoto("../../../../assets/img/profile.png")
                .build();
        this.alumnoRepository.save(alumno2);

        Alumno alumno3 = Alumno.builder()
                .nombre("Ramiro")
                .apellido1("Ramírez")
                .email("ramiro@mail.com")
                .dni("123456789F")
                .telefono("+34 123456789")
                .password(this.encoder.encode("123456"))
                .carnetConducir(0L)
                .vehiculoPropio(0L)
                .formacion(SMR)
                .pathFoto("../../../../assets/img/profile.png")
                .build();
        this.alumnoRepository.save(alumno3);

        Alumno alumno4 = Alumno.builder()
                .nombre("Paquito")
                .apellido1("Perez")
                .email("paqman@mail.com")
                .dni("123456779F")
                .telefono("+34 123456789")
                .password(this.encoder.encode("123456"))
                .carnetConducir(0L)
                .vehiculoPropio(0L)
                .formacion(SMR)
                .pathFoto("../../../../assets/img/profile.png")
                .build();
        this.alumnoRepository.save(alumno4);

        Alumno alumno5 = Alumno.builder()
                .nombre("Luis")
                .apellido1("García")
                .email("luis@mail.com")
                .dni("567890123K")
                .telefono("+34 654321789")
                .password(this.encoder.encode("abc123"))
                .carnetConducir(1L)
                .vehiculoPropio(0L)
                .formacion(DAM)
                .pathFoto("../../../../assets/img/profile.png")
                .build();
        this.alumnoRepository.save(alumno5);

        Alumno alumno6 = Alumno.builder()
                .nombre("Sofía")
                .apellido1("Sánchez")
                .email("sofia@mail.com")
                .dni("678901234L")
                .telefono("+34 789654123")
                .password(this.encoder.encode("pass123"))
                .carnetConducir(0L)
                .vehiculoPropio(1L)
                .formacion(DAW)
                .pathFoto("../../../../assets/img/profile.png")
                .build();
        this.alumnoRepository.save(alumno6);

        Alumno alumno7 = Alumno.builder()
                .nombre("Carlos")
                .apellido1("Díaz")
                .email("carlos@mail.com")
                .dni("789012345M")
                .telefono("+34 321987654")
                .password(this.encoder.encode("qwerty123"))
                .carnetConducir(1L)
                .vehiculoPropio(0L)
                .formacion(SMR)
                .pathFoto("../../../../assets/img/profile.png")
                .build();
        this.alumnoRepository.save(alumno7);

        Alumno alumno8 = Alumno.builder()
                .nombre("Ana")
                .apellido1("Rodríguez")
                .email("ana@mail.com")
                .dni("890123456N")
                .telefono("+34 123987654")
                .password(this.encoder.encode("abc123xyz"))
                .carnetConducir(0L)
                .vehiculoPropio(1L)
                .formacion(DAW)
                .pathFoto("../../../../assets/img/profile.png")
                .build();
        this.alumnoRepository.save(alumno8);

        Alumno alumno9 = Alumno.builder()
                .nombre("Pedro")
                .apellido1("Pérez")
                .email("pedro@mail.com")
                .dni("901234567O")
                .telefono("+34 987123789")
                .password(this.encoder.encode("password123"))
                .carnetConducir(1L)
                .vehiculoPropio(0L)
                .formacion(DAM)
                .pathFoto("../../../../assets/img/profile.png")
                .build();
        this.alumnoRepository.save(alumno9);

        Alumno alumno10 = Alumno.builder()
                .nombre("ELENA")
                .apellido1("Ruiz")
                .email("elena@mail.com")
                .dni("012345678P")
                .telefono("+34 789654321")
                .password(this.encoder.encode("pass123abc"))
                .carnetConducir(0L)
                .vehiculoPropio(1L)
                .formacion(SMR)
                .pathFoto("../../../../assets/img/profile.png")
                .build();
        this.alumnoRepository.save(alumno10);

        Alumno alumno11 = Alumno.builder()
                .nombre("Elena1")
                .apellido1("Ruiz")
                .email("elena1@mail.com")
                .dni("012345671P")
                .telefono("+34 789654321")
                .password(this.encoder.encode("pass123abc"))
                .carnetConducir(0L)
                .vehiculoPropio(1L)
                .formacion(SMR)
                .pathFoto("../../../../assets/img/profile.png")
                .build();
        this.alumnoRepository.save(alumno11);

        Alumno alumno12 = Alumno.builder()
                .nombre("Elena2")
                .apellido1("Ruiz")
                .email("elena2@mail.com")
                .dni("012345672P")
                .telefono("+34 789654321")
                .password(this.encoder.encode("pass123abc"))
                .carnetConducir(0L)
                .vehiculoPropio(1L)
                .formacion(SMR)
                .pathFoto("../../../../assets/img/profile.png")
                .build();
        this.alumnoRepository.save(alumno12);

        Tecnologia javaSpring = Tecnologia.builder()
                .nombre("Java en Spring Boot")
                .descripcion("Para un proyecto del banco ING")
                .build();
        this.tecnologiaRepository.save(javaSpring);

        Tecnologia angular = Tecnologia.builder()
                .nombre("Angular 17")
                .build();
        this.tecnologiaRepository.save(angular);

        Empresa empresa1 = Empresa.builder()
                .nombre("Accenture")
                .email("acc@mail.com")
                .password(this.encoder.encode("123456"))
                .inglesSolicitado(EInglesSolicitado.IMPORTANTE)
                .modalidadesTrabajo(new HashSet<>(Set.of(EModalidadTrabajo.HIBRIDO, EModalidadTrabajo.PRESENCIAL)))
                .resumen("Empresa consultora")
                .tecnologias(new HashSet<>(Set.of(javaSpring, angular)))
                .pathFoto("../../../../assets/img/profile.png")
                .build();
        this.empresaRepository.save(empresa1);

        Ubicacion ubi1emp1 = Ubicacion.builder()
                .pais("España")
                .ciudad("Málaga")
                .direccion("Calle Severo Ochoa, 8")
                .codigoPostal("29002")
                .empresa(empresa1)
                .build();
        this.ubicacionRepository.save(ubi1emp1);

        Empresa empresa2 = Empresa.builder()
                .nombre("Babel")
                .email("babel@mail.com")
                .password(this.encoder.encode("123456"))
                .inglesSolicitado(EInglesSolicitado.IMPRESCINDIBLE)
                .modalidadesTrabajo(new HashSet<>(Set.of(EModalidadTrabajo.HIBRIDO, EModalidadTrabajo.ONLINE, EModalidadTrabajo.PRESENCIAL)))
                .resumen("Empresa consultora")
                .tecnologias(new HashSet<>(Set.of(javaSpring)))
                .pathFoto("../../../../assets/img/profile.png")
                .build();
        this.empresaRepository.save(empresa2);

        Ubicacion ubi1emp2 = Ubicacion.builder()
                .pais("España")
                .ciudad("Málaga")
                .direccion("Calle Severo Ochoa, 8")
                .codigoPostal("29002")
                .empresa(empresa2)
                .build();
        this.ubicacionRepository.save(ubi1emp2);

        Ubicacion ubi2emp2 = Ubicacion.builder()
                .pais("España")
                .ciudad("Sevilla")
                .direccion("Calle Mairena Aljarafe, 8")
                .codigoPostal("46000")
                .empresa(empresa2)
                .build();
        this.ubicacionRepository.save(ubi2emp2);

        Empresa empresa3 = Empresa.builder()
                .nombre("Indra")
                .email("indra@mail.com")
                .password(this.encoder.encode("123456"))
                .inglesSolicitado(EInglesSolicitado.NO_NECESARIO)
                .modalidadesTrabajo(new HashSet<>(Set.of(EModalidadTrabajo.HIBRIDO, EModalidadTrabajo.ONLINE)))
                .resumen("Empresa consultora")
                .pathFoto("../../../../assets/img/profile.png")
                .build();
        this.empresaRepository.save(empresa3);

        Solicitud solAlu1 = Solicitud.builder()
                .alumno(alumno1)
                .empresa(empresa2)
                .tipo(ETipoSolicitud.ALUMNO_A_EMPRESA)
                .descripcion("Mireya, alumno1, solicita a empresa 2, Babel")
                .estado(EEstadoSolicitud.ENVIADA)
                .build();
        this.solicitudRepository.save(solAlu1);

        Solicitud solEmp2 = Solicitud.builder()
                .alumno(alumno1)
                .empresa(empresa1)
                .tipo(ETipoSolicitud.EMPRESA_A_ALUMNO)
                .descripcion("Accenture, empresa1, solicita a alumno 1, Mireya")
                .estado(EEstadoSolicitud.RECHAZADA)
                .build();
        this.solicitudRepository.save(solEmp2);

        ProfesorTutorizaAlumno tutoria1 = ProfesorTutorizaAlumno.builder()
                .profesor(profe1)
                .alumno(alumno1)
                .tipoTutoria("Practicas")
                .build();
        this.profesorTutorizaAlumnoRepository.save(tutoria1);

        ProfesorTutorizaAlumno tutoria2 = ProfesorTutorizaAlumno.builder()
                .profesor(profe2)
                .alumno(alumno1)
                .tipoTutoria("Proyecto")
                .build();
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
//        Alumno alumno1 = Alumno();
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
//        Alumno alumno2 = Alumno();
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
//        Alumno alumno3 = Alumno();
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
//        Alumno alumno4 = Alumno();
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
//        Alumno alumno5 = Alumno();
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
//        Alumno alumno6 = Alumno();
//        alumno6.setNombre("Esperanza");
//        alumno6.setApellido1("Perales");
//        Set<Idioma> idiomasAlu6 = new HashSet<>();
//        idiomasAlu6.add(idioma3);
//        alumno6.setIdiomas(idiomasAlu6);
//        alumno6.setCarnetConducir(0L);
//        alumno6.setVehiculoPropio(1L);
//        this.alumnoRepository.save(alumno6);
//
//        Alumno alumno7 = Alumno();
//        alumno7.setNombre("María");
//        alumno7.setApellido1("Figueras");
//        Set<Idioma> idiomasAlu7 = new HashSet<>();
//        idiomasAlu7.add(idioma1);
//        alumno7.setIdiomas(idiomasAlu7);
//        alumno7.setCarnetConducir(1L);
//        alumno7.setVehiculoPropio(0L);
//        this.alumnoRepository.save(alumno7);
//
//        Alumno alumno8 = Alumno();
//        alumno8.setNombre("Pamela");
//        alumno8.setApellido1("Pabón");
//        Set<Idioma> idiomasAlu8 = new HashSet<>();
//        idiomasAlu8.add(idioma2);
//        alumno8.setIdiomas(idiomasAlu8);
//        alumno8.setCarnetConducir(0L);
//        alumno8.setVehiculoPropio(1L);
//        this.alumnoRepository.save(alumno8);
//
//        Alumno alumno9 = Alumno();
//        alumno9.setNombre("Ángel");
//        alumno9.setApellido1("Angulo");
//        Set<Idioma> idiomasAlu9 = new HashSet<>();
//        idiomasAlu9.add(idioma3);
//        alumno9.setIdiomas(idiomasAlu9);
//        alumno9.setCarnetConducir(0L);
//        alumno9.setVehiculoPropio(0L);
//        this.alumnoRepository.save(alumno9);
//
//        Alumno alumno10 = Alumno();
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
