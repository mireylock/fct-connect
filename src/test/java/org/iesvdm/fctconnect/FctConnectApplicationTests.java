package org.iesvdm.fctconnect;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.fctconnect.domain.*;
import org.iesvdm.fctconnect.domain.enums.*;
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

    @Autowired
    AlumnoHablaIdiomaRepository alumnoHablaIdiomaRepository;


    @Test
    void contextLoads() {
    }


    @Test
    void empresasSinCampos() {
        Empresa empresaSinIngles = Empresa.builder()
                .nombre("sin ingles")
                .email("noenglish@mail.com")
                .password(this.encoder.encode("123456"))
                .modalidadesTrabajo(new HashSet<>(Set.of(EModalidadTrabajo.HIBRIDO, EModalidadTrabajo.ONLINE)))
                .resumen("Empresa sin ingles")
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.empresaRepository.save(empresaSinIngles);

        Empresa empresaSinModalidades = Empresa.builder()
                .nombre("sin modalidades")
                .email("nomodal@mail.com")
                .password(this.encoder.encode("123456"))
                .inglesSolicitado(EInglesSolicitado.NO_NECESARIO)
                .resumen("Empresa consultora")
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.empresaRepository.save(empresaSinModalidades);
    }
    @Test
    void cargaInicialBbdd() {
        Idioma ingles = Idioma.builder()
                .nombre("Inglés")
                .build();
        idiomaRepository.save(ingles);

        Idioma frances = Idioma.builder()
                .nombre("Francés")
                .build();
        idiomaRepository.save(frances);

        Idioma aleman = Idioma.builder()
                .nombre("Alemán")
                .build();
        idiomaRepository.save(aleman);

        Idioma ruso = Idioma.builder()
                .nombre("Ruso")
                .build();
        idiomaRepository.save(ruso);

        Idioma tagalog = Idioma.builder()
                .nombre("Tagalog")
                .build();
        idiomaRepository.save(tagalog);

        Idioma holandes = Idioma.builder()
                .nombre("Holandés")
                .build();
        idiomaRepository.save(holandes);

        Idioma noruego = Idioma.builder()
                .nombre("Noruego")
                .build();
        idiomaRepository.save(noruego);

        Idioma chino = Idioma.builder()
                .nombre("Chino")
                .build();
        idiomaRepository.save(chino);


        Idioma japones = Idioma.builder()
                .nombre("Japonés")
                .build();
        idiomaRepository.save(japones);

        Administrador admin = Administrador.builder()
                .nombre("Admin")
                .apellido1("Istrador")
                .email("admin@mail.com")
                .dni("123456789A")
                .telefono("+34 123456789")
                .password(this.encoder.encode("123456"))
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
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
                .activo(true)
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
                .activo(true)
                .build();
        this.profesorRepository.save(profe2);



        Formacion DAW = Formacion.builder()
                .nombre("Desarrollo de Aplicaciones Web")
                .nivel("Grado Superior")
                .build();
        formacionRepository.save(DAW);

        Formacion DAM = Formacion.builder()
                .nombre("Desarrollo de Aplicaciones Multiplataforma")
                .nivel("Grado Superior")
                .build();
        formacionRepository.save(DAM);

        Formacion SMR = Formacion.builder()
                .nombre("Sistemas Microinformáticos y en Red")
                .nivel("Grado Medio")
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
//                .idiomas(List.of(idioma1, aleman))
                .carnetConducir(Boolean.TRUE)
                .vehiculoPropio(Boolean.FALSE)
                .formacion(DAW)
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.alumnoRepository.save(alumno1);

        Alumno alumno2 = Alumno.builder()
                .nombre("Juan")
                .apellido1("García")
                .email("juan@mail.com")
                .dni("123456789E")
                .telefono("+34 123456789")
                .password(this.encoder.encode("123456"))
//                .idiomas(Set.of(idioma1, frances))
                .carnetConducir(Boolean.TRUE)
                .vehiculoPropio(Boolean.TRUE)
                .formacion(DAM)
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.alumnoRepository.save(alumno2);

        Alumno alumno3 = Alumno.builder()
                .nombre("Ramiro")
                .apellido1("Ramírez")
                .email("ramiro@mail.com")
                .dni("123456789F")
                .telefono("+34 123456789")
//                .idiomas(Set.of(idioma1))
                .password(this.encoder.encode("123456"))
                .carnetConducir(Boolean.FALSE)
                .vehiculoPropio(Boolean.FALSE)
                .formacion(SMR)
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.alumnoRepository.save(alumno3);

        Alumno alumno4 = Alumno.builder()
                .nombre("Paquito")
                .apellido1("Perez")
                .email("paqman@mail.com")
                .dni("123456779F")
                .telefono("+34 123456789")
                .password(this.encoder.encode("123456"))
                .carnetConducir(Boolean.FALSE)
                .vehiculoPropio(Boolean.FALSE)
                .formacion(SMR)
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.alumnoRepository.save(alumno4);

        Alumno alumno5 = Alumno.builder()
                .nombre("Luis")
                .apellido1("García")
                .email("luis@mail.com")
                .dni("567890123K")
                .telefono("+34 654321789")
                .password(this.encoder.encode("abc123"))
                .carnetConducir(Boolean.TRUE)
                .vehiculoPropio(Boolean.FALSE)
                .formacion(DAM)
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.alumnoRepository.save(alumno5);

        Alumno alumno6 = Alumno.builder()
                .nombre("Sofía")
                .apellido1("Sánchez")
                .email("sofia@mail.com")
                .dni("678901234L")
                .telefono("+34 789654123")
                .password(this.encoder.encode("pass123"))
                .carnetConducir(Boolean.FALSE)
                .vehiculoPropio(Boolean.TRUE)
                .formacion(DAW)
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.alumnoRepository.save(alumno6);

        AlumnoHablaIdioma mireyaHablaIngles = AlumnoHablaIdioma.builder()
                .idioma(ingles)
                .alumno(alumno1)
                .nivel(ENivelIdioma.AVANZADO)
                .descripcion("Dispongo de diploma B2 pero mi nivel es más bien C1")
                .pathDiploma("/diplomaB2")
                .build();
        this.alumnoHablaIdiomaRepository.save(mireyaHablaIngles);

        AlumnoHablaIdioma mireyaHablaAleman = AlumnoHablaIdioma.builder()
                .idioma(aleman)
                .alumno(alumno1)
                .nivel(ENivelIdioma.PRINCIPIANTE)
                .descripcion("Tres años estudiando alemán en el instituto")
                .build();
        this.alumnoHablaIdiomaRepository.save(mireyaHablaAleman);

        AlumnoHablaIdioma alumno2HablaIngles = AlumnoHablaIdioma.builder()
                .idioma(ingles)
                .alumno(alumno2)
                .nivel(ENivelIdioma.PRINCIPIANTE)
                .descripcion("Nivel básico")
                .build();
        this.alumnoHablaIdiomaRepository.save(alumno2HablaIngles);


        Alumno alumno7 = Alumno.builder()
                .nombre("Carlos")
                .apellido1("Díaz")
                .email("carlos@mail.com")
                .dni("789012345M")
                .telefono("+34 321987654")
                .password(this.encoder.encode("qwerty123"))
                .carnetConducir(Boolean.TRUE)
                .vehiculoPropio(Boolean.FALSE)
                .formacion(SMR)
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.alumnoRepository.save(alumno7);

        Alumno alumno8 = Alumno.builder()
                .nombre("Ana")
                .apellido1("Rodríguez")
                .email("ana@mail.com")
                .dni("890123456N")
                .telefono("+34 123987654")
                .password(this.encoder.encode("abc123xyz"))
                .carnetConducir(Boolean.FALSE)
                .vehiculoPropio(Boolean.TRUE)
                .formacion(DAW)
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.alumnoRepository.save(alumno8);

        Alumno alumno9 = Alumno.builder()
                .nombre("Pedro")
                .apellido1("Pérez")
                .email("pedro@mail.com")
                .dni("901234567O")
                .telefono("+34 987123789")
                .password(this.encoder.encode("password123"))
                .carnetConducir(Boolean.TRUE)
                .vehiculoPropio(Boolean.FALSE)
                .formacion(DAM)
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.alumnoRepository.save(alumno9);

        Alumno alumno10 = Alumno.builder()
                .nombre("ELENA")
                .apellido1("Ruiz")
                .email("elena@mail.com")
                .dni("012345678P")
                .telefono("+34 789654321")
                .password(this.encoder.encode("pass123abc"))
                .carnetConducir(Boolean.FALSE)
                .vehiculoPropio(Boolean.TRUE)
                .formacion(SMR)
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.alumnoRepository.save(alumno10);

        Alumno alumno11 = Alumno.builder()
                .nombre("Elena1")
                .apellido1("Ruiz")
                .email("elena1@mail.com")
                .dni("012345671P")
                .telefono("+34 789654321")
                .password(this.encoder.encode("pass123abc"))
                .carnetConducir(Boolean.FALSE)
                .vehiculoPropio(Boolean.TRUE)
                .formacion(SMR)
                .pathFoto("../../../../assets/img/profile.png")
                .activo(false)
                .build();
        this.alumnoRepository.save(alumno11);

        Alumno alumno12 = Alumno.builder()
                .nombre("Elena2")
                .apellido1("Ruiz")
                .email("elena2@mail.com")
                .dni("012345672P")
                .telefono("+34 789654321")
                .password(this.encoder.encode("pass123abc"))
                .carnetConducir(Boolean.FALSE)
                .vehiculoPropio(Boolean.TRUE)
                .formacion(SMR)
                .pathFoto("../../../../assets/img/profile.png")
                .activo(false)
                .build();
        this.alumnoRepository.save(alumno12);


        Empresa empresa1 = Empresa.builder()
                .nombre("Accenture")
                .email("acc@mail.com")
                .password(this.encoder.encode("123456"))
                .inglesSolicitado(EInglesSolicitado.IMPORTANTE)
                .modalidadesTrabajo(new HashSet<>(Set.of(EModalidadTrabajo.HIBRIDO, EModalidadTrabajo.PRESENCIAL)))
                .resumen("Empresa consultora")
//                .tecnologias(new HashSet<>(Set.of(javaSpring, angular)))
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.empresaRepository.save(empresa1);

        Tecnologia javaAccenture = Tecnologia.builder()
                .empresa(empresa1)
                .nombre("Java 17")
                .descripcion("Para un proyecto X")
                .build();
        this.tecnologiaRepository.save(javaAccenture);

        Tecnologia angularAccenture = Tecnologia.builder()
                .empresa(empresa1)
                .nombre("Angular 17")
                .build();
        this.tecnologiaRepository.save(angularAccenture);


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
//                .tecnologias(new HashSet<>(Set.of(javaSpring)))
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.empresaRepository.save(empresa2);

        Tecnologia angularBabel = Tecnologia.builder()
                .empresa(empresa2)
                .nombre("Angular 14")
                .build();
        this.tecnologiaRepository.save(angularBabel);

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
                .activo(true)
                .build();
        this.empresaRepository.save(empresa3);

        Empresa empresa4 = Empresa.builder()
                .nombre("NTT DATA")
                .email("ntt@mail.com")
                .password(this.encoder.encode("123456"))
                .inglesSolicitado(EInglesSolicitado.NO_NECESARIO)
                .modalidadesTrabajo(new HashSet<>(Set.of(EModalidadTrabajo.HIBRIDO, EModalidadTrabajo.ONLINE)))
                .resumen("Empresa consultora")
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.empresaRepository.save(empresa4);

        Empresa empresa5 = Empresa.builder()
                .nombre("CGI")
                .email("cgi@mail.com")
                .password(this.encoder.encode("123456"))
                .inglesSolicitado(EInglesSolicitado.NO_NECESARIO)
                .modalidadesTrabajo(new HashSet<>(Set.of(EModalidadTrabajo.HIBRIDO, EModalidadTrabajo.ONLINE)))
                .resumen("Empresa consultora")
                .pathFoto("../../../../assets/img/profile.png")
                .activo(false)
                .build();
        this.empresaRepository.save(empresa5);

        //Enviada de Mireya
        Solicitud solAlu1 = Solicitud.builder()
                .alumno(alumno1)
                .empresa(empresa2)
                .tipo(ETipoSolicitud.ALUMNO_A_EMPRESA)
                .descripcion("Mireya, alumno1, solicita a empresa 2, Babel")
                .estado(EEstadoSolicitud.ENVIADA)
                .build();
        this.solicitudRepository.save(solAlu1);

        //Recibida de Mireya
        Solicitud solEmp2 = Solicitud.builder()
                .alumno(alumno1)
                .empresa(empresa1)
                .tipo(ETipoSolicitud.EMPRESA_A_ALUMNO)
                .descripcion("Accenture, empresa1, solicita a alumno 1, Mireya")
                .estado(EEstadoSolicitud.ENVIADA)
                .build();
        this.solicitudRepository.save(solEmp2);

        //Aceptada de Mireya
        Solicitud solEmp3 = Solicitud.builder()
                .alumno(alumno1)
                .empresa(empresa3)
                .tipo(ETipoSolicitud.EMPRESA_A_ALUMNO)
                .descripcion("Empresa3, solicita a alumno 1, Mireya")
                .estado(EEstadoSolicitud.ACEPTADA)
                .build();
        this.solicitudRepository.save(solEmp3);

        //Rechazada de Mireya
        Solicitud solEmp4 = Solicitud.builder()
                .alumno(alumno1)
                .empresa(empresa4)
                .tipo(ETipoSolicitud.EMPRESA_A_ALUMNO)
                .descripcion("Empresa4, solicita a alumno 1, Mireya")
                .estado(EEstadoSolicitud.RECHAZADA)
                .build();
        this.solicitudRepository.save(solEmp4);


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

        ProfesorTutorizaAlumno tutoria3 = ProfesorTutorizaAlumno.builder()
                .profesor(profe1)
                .alumno(alumno2)
                .tipoTutoria("Practicas")
                .build();
        this.profesorTutorizaAlumnoRepository.save(tutoria3);

    }


}
