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
    void cargaInicialBbdd() {
        //////////      IDIOMAS     ////////////////
        Idioma espaniol = Idioma.builder()
                .nombre("Español")
                .build();
        idiomaRepository.save(espaniol);

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

        Idioma italiano = Idioma.builder()
                .nombre("Italiano")
                .build();
        idiomaRepository.save(italiano);

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

        //////////      FORMACIONES (GRADOS)     ///////////////
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

        //////////      ASIGNATURAS     ////////////////
        Asignatura servidor = Asignatura.builder()
                .nombre("Entornos del desarrollo en entorno servidor")
                .curso("2")
                .grupo("DAW")
                .build();
        this.asignaturaRepository.save(servidor);

        Asignatura cliente = Asignatura.builder()
                .nombre("Entornos del desarrollo en entorno cliente")
                .curso("2")
                .grupo("DAW")
                .build();
        this.asignaturaRepository.save(cliente);

        Asignatura hlc = Asignatura.builder()
                .nombre("HLC")
                .curso("2")
                .grupo("DAW")
                .build();
        this.asignaturaRepository.save(hlc);

        Asignatura despliegue = Asignatura.builder()
                .nombre("Despliegue de aplicaciones web")
                .curso("2º")
                .grupo("DAW")
                .build();
        this.asignaturaRepository.save(despliegue);

        Asignatura lenguajeMarcas = Asignatura.builder()
                .nombre("Lenguaje de marcas")
                .curso("1º")
                .grupo("DAW")
                .build();
        this.asignaturaRepository.save(lenguajeMarcas);


        //////////      USUARIOS     ////////////////

        //////////      ADMINISTRADOR     ////////////////
        Administrador admin = Administrador.builder()
                .nombre("Admin")
                .apellido1("Istrador")
                .email("admin@g.educaand.es")
                .dni("123456789A")
                .telefono("+34 123456789")
                .password(this.encoder.encode("123456"))
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.administradorRepository.save(admin);

        //////////      PROFESORES     ////////////////
        Profesor profe1 = Profesor.builder()
                .nombre("Jose Manuel")
                .apellido1("Evangelizador")
                .apellido2("En Java")
                .email("jose@g.educaand.es")
                .dni("123456789B")
                .telefono("+34 123456789")
                .direccion("Calle falsa 123, Cártama")
                .departamento("Informática y comunicaciones")
                .password(this.encoder.encode("123456"))
                .asignaturas(new HashSet<>(Set.of(hlc, servidor)))
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.profesorRepository.save(profe1);

        Profesor profe2 = Profesor.builder()
                .nombre("Carlos")
                .apellido1("Vela")
                .email("carlos@g.educaand.es")
                .dni("123456789C")
                .telefono("+34 123456789")
                .direccion("Calle Falsa 123, Málaga")
                .departamento("Informática y comunicaciones")
                .asignaturas(new HashSet<>(Set.of(cliente)))
                .password(this.encoder.encode("123456"))
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.profesorRepository.save(profe2);

        Profesor profe3 = Profesor.builder()
                .nombre("Abraham")
                .apellido1("Ramos")
                .apellido2("Cózar")
                .email("abraham@g.educaand.es")
                .dni("123456789D")
                .telefono("+34 123456789")
                .departamento("Departamento de informática")
                .direccion("Calle falsa 123, Málaga")
                .asignaturas(new HashSet<>(Set.of(lenguajeMarcas, despliegue)))
                .password(this.encoder.encode("123456"))
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.profesorRepository.save(profe3);

        Profesor profe4 = Profesor.builder()
                .nombre("Beatriz")
                .apellido1("García")
                .email("beatriz@g.educaand.es")
                .dni("123456789E")
                .telefono("+34 123456789")
                .departamento("Jefatura")
                .password(this.encoder.encode("123456"))
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.profesorRepository.save(profe4);

        Profesor profe5 = Profesor.builder()
                .nombre("Miguel Ángel")
                .apellido1("Sáez")
                .email("miguel@g.educaand.es")
                .dni("123456789F")
                .telefono("+34 123456789")
                .departamento("Informática y comunicaciones")
                .password(this.encoder.encode("123456"))
                .asignaturas(new HashSet<>(Set.of(hlc)))
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.profesorRepository.save(profe5);

        Profesor profe6 = Profesor.builder()
                .nombre("Manuel")
                .apellido1("Aragonés")
                .email("manuel@g.educaand.es")
                .dni("123456789G")
                .telefono("+34 123456789")
                .departamento("Departamento de informática")
                .direccion("Calle falsa 123, Málaga")
                .password(this.encoder.encode("123456"))
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.profesorRepository.save(profe6);

        Profesor profe7 = Profesor.builder()
                .nombre("Alfonso")
                .apellido1("Pérez")
                .email("alf@g.educaand.es")
                .dni("123456789H")
                .telefono("+34 123456789")
                .departamento("Departamento de informática")
                .direccion("Calle falsa 123, Málaga")
                .password(this.encoder.encode("123456"))
                .pathFoto("../../../../assets/img/profile.png")
                .activo(false)
                .build();
        this.profesorRepository.save(profe7);


        //////////      ALUMNOS     ///////////////
        Alumno alumno1 = Alumno.builder()
                .nombre("Mireya")
                .apellido1("Medalle")
                .apellido2("Merino")
                .email("mireya@g.educaand.es")
                .dni("123456789I")
                .telefono("+34 123456789")
                .password(this.encoder.encode("123456"))
                .carnetConducir(Boolean.TRUE)
                .vehiculoPropio(Boolean.FALSE)
                .formacion(DAW)
                .pathFoto("../../../../assets/img/mireya.jpg")
                .activo(true)
                .build();
        this.alumnoRepository.save(alumno1);


        Alumno alumno4 = Alumno.builder()
                .nombre("Michelle")
                .apellido1("Albacura")
                .email("michi@g.educaand.es")
                .dni("123456789L")
                .telefono("+34 123456789")
                .password(this.encoder.encode("123456"))
                .carnetConducir(Boolean.FALSE)
                .vehiculoPropio(Boolean.FALSE)
                .formacion(DAW)
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.alumnoRepository.save(alumno4);

        Alumno alumno5 = Alumno.builder()
                .nombre("Héctor")
                .apellido1("López")
                .apellido2("Díaz")
                .email("hector@g.educaand.es")
                .dni("123456789M")
                .telefono("+34 123456789")
                .password(this.encoder.encode("123456"))
                .carnetConducir(Boolean.TRUE)
                .vehiculoPropio(Boolean.TRUE)
                .formacion(DAW)
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.alumnoRepository.save(alumno5);

        Alumno alumno6 = Alumno.builder()
                .nombre("Guillermo")
                .apellido1("Rodríguez")
                .apellido2("Moreno")
                .email("guille@g.educaand.es")
                .dni("123456789N")
                .telefono("+34 123456789")
                .password(this.encoder.encode("123456"))
                .carnetConducir(Boolean.TRUE)
                .vehiculoPropio(Boolean.TRUE)
                .formacion(DAW)
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.alumnoRepository.save(alumno6);

        Alumno alumno7 = Alumno.builder()
                .nombre("Mattia")
                .apellido1("Lu")
                .email("matti@g.educaand.es")
                .dni("123456789Z")
                .telefono("+34 123456789")
                .password(this.encoder.encode("123456"))
                .carnetConducir(Boolean.TRUE)
                .vehiculoPropio(Boolean.TRUE)
                .formacion(DAW)
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.alumnoRepository.save(alumno7);

        Alumno alumno2 = Alumno.builder()
                .nombre("Juan")
                .apellido1("García")
                .email("juan@g.educaand.es")
                .dni("123456789J")
                .telefono("+34 123456789")
                .password(this.encoder.encode("123456"))
                .carnetConducir(Boolean.TRUE)
                .vehiculoPropio(Boolean.TRUE)
                .formacion(DAM)
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.alumnoRepository.save(alumno2);

        Alumno alumno8 = Alumno.builder()
                .nombre("Juan")
                .apellido1("Moreno")
                .email("juanmo@g.educaand.es")
                .dni("123456789O")
                .telefono("+34 123456789")
                .password(this.encoder.encode("123456"))
                .carnetConducir(Boolean.FALSE)
                .vehiculoPropio(Boolean.FALSE)
                .formacion(DAM)
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.alumnoRepository.save(alumno8);

        Alumno alumno9 = Alumno.builder()
                .nombre("Rafael")
                .apellido1("Galván")
                .email("rafa@g.educaand.es")
                .dni("123456789P")
                .telefono("+34 123456789")
                .password(this.encoder.encode("123456"))
                .carnetConducir(Boolean.TRUE)
                .vehiculoPropio(Boolean.TRUE)
                .formacion(DAM)
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.alumnoRepository.save(alumno9);

        Alumno alumno3 = Alumno.builder()
                .nombre("Ramiro")
                .apellido1("Ramírez")
                .email("ramiro@g.educaand.es")
                .dni("123456789K")
                .telefono("+34 123456789")
                .password(this.encoder.encode("123456"))
                .carnetConducir(Boolean.FALSE)
                .vehiculoPropio(Boolean.FALSE)
                .formacion(SMR)
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.alumnoRepository.save(alumno3);

        Alumno alumno10 = Alumno.builder()
                .nombre("Santiago")
                .apellido1("Moreno")
                .apellido2("Casillas")
                .email("santi@g.educaand.es")
                .dni("123456789Q")
                .telefono("+34 123456789")
                .password(this.encoder.encode("123456"))
                .carnetConducir(Boolean.TRUE)
                .vehiculoPropio(Boolean.TRUE)
                .formacion(SMR)
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.alumnoRepository.save(alumno10);

        Alumno alumno11 = Alumno.builder()
                .nombre("Francisco")
                .apellido1("Fernández")
                .apellido2("Porras")
                .email("paco@g.educaand.es")
                .dni("123456789R")
                .telefono("+34 123456789")
                .password(this.encoder.encode("123456"))
                .carnetConducir(Boolean.TRUE)
                .vehiculoPropio(Boolean.FALSE)
                .formacion(SMR)
                .pathFoto("../../../../assets/img/profile.png")
                .activo(false)
                .build();
        this.alumnoRepository.save(alumno11);

        Alumno alumno12 = Alumno.builder()
                .nombre("Lara")
                .apellido1("López")
                .apellido2("López")
                .email("lara@g.educaand.es")
                .dni("123456789X")
                .telefono("+34 123456789")
                .password(this.encoder.encode("123456"))
                .carnetConducir(Boolean.FALSE)
                .vehiculoPropio(Boolean.FALSE)
                .formacion(DAM)
                .pathFoto("../../../../assets/img/profile.png")
                .activo(false)
                .build();
        this.alumnoRepository.save(alumno12);

        Alumno alumno13 = Alumno.builder()
                .nombre("María")
                .apellido1("López")
                .apellido2("Jiménez")
                .email("maria@g.educaand.es")
                .dni("123456789S")
                .telefono("+34 123456789")
                .password(this.encoder.encode("123456"))
                .carnetConducir(Boolean.TRUE)
                .vehiculoPropio(Boolean.FALSE)
                .formacion(DAW)
                .pathFoto("../../../../assets/img/profile.png")
                .activo(false)
                .build();
        this.alumnoRepository.save(alumno13);

        Alumno alumno14 = Alumno.builder()
                .nombre("Xenia")
                .apellido1("Enamor")
                .email("contraks@g.educaand.es")
                .dni("123456789T")
                .telefono("+41 123456789")
                .password(this.encoder.encode("123456"))
                .carnetConducir(Boolean.FALSE)
                .vehiculoPropio(Boolean.FALSE)
                .formacion(DAM)
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.alumnoRepository.save(alumno14);

        Alumno alumno15 = Alumno.builder()
                .nombre("Julia")
                .apellido1("Bolsó")
                .apellido2("Flyman")
                .email("julia@g.educaand.es")
                .dni("123456789U")
                .telefono("+34 123456789")
                .password(this.encoder.encode("123456"))
                .carnetConducir(Boolean.FALSE)
                .vehiculoPropio(Boolean.FALSE)
                .formacion(SMR)
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.alumnoRepository.save(alumno15);

        Alumno alumno16 = Alumno.builder()
                .nombre("David")
                .apellido1("Broglio")
                .apellido2("Quero")
                .email("david@g.educaand.es")
                .dni("123456789Y")
                .telefono("+34 123456789")
                .password(this.encoder.encode("123456"))
                .carnetConducir(Boolean.FALSE)
                .vehiculoPropio(Boolean.FALSE)
                .formacion(DAW)
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.alumnoRepository.save(alumno16);

        Alumno alumno17 = Alumno.builder()
                .nombre("Cristian")
                .apellido1("Serrano")
                .apellido2("Lupión")
                .email("cristian@g.educaand.es")
                .dni("123456789W")
                .telefono("+34 123456789")
                .password(this.encoder.encode("123456"))
                .carnetConducir(Boolean.FALSE)
                .vehiculoPropio(Boolean.FALSE)
                .formacion(DAW)
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.alumnoRepository.save(alumno17);

        Alumno alumno18 = Alumno.builder()
                .nombre("Tor")
                .apellido1("Nilden")
                .email("tor@g.educaand.es")
                .dni("223456789A")
                .telefono("+34 123456789")
                .password(this.encoder.encode("123456"))
                .carnetConducir(Boolean.TRUE)
                .vehiculoPropio(Boolean.TRUE)
                .formacion(SMR)
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.alumnoRepository.save(alumno18);

        AlumnoHablaIdioma torNoruego = AlumnoHablaIdioma.builder()
                .idioma(noruego)
                .alumno(alumno18)
                .nivel(ENivelIdioma.NATIVO)
                .descripcion("Segunda lengua nativa porque mi madre es holandesa")
                .build();
        this.alumnoHablaIdiomaRepository.save(torNoruego);

        AlumnoHablaIdioma juliaHolandes = AlumnoHablaIdioma.builder()
                .idioma(holandes)
                .alumno(alumno15)
                .nivel(ENivelIdioma.NATIVO)
                .descripcion("Segunda lengua nativa porque mi madre es holandesa")
                .build();
        this.alumnoHablaIdiomaRepository.save(juliaHolandes);

        AlumnoHablaIdioma xeniaAleman = AlumnoHablaIdioma.builder()
                .idioma(aleman)
                .alumno(alumno14)
                .nivel(ENivelIdioma.AVANZADO)
                .descripcion("6 años trabajando en la parte alemana de Suiza y hablando inglés en la vida cotidiana")
                .build();
        this.alumnoHablaIdiomaRepository.save(xeniaAleman);

        AlumnoHablaIdioma xeniaRuso = AlumnoHablaIdioma.builder()
                .idioma(ruso)
                .alumno(alumno14)
                .nivel(ENivelIdioma.NATIVO)
                .build();
        this.alumnoHablaIdiomaRepository.save(xeniaRuso);

        AlumnoHablaIdioma mattiChino = AlumnoHablaIdioma.builder()
                .idioma(chino)
                .alumno(alumno7)
                .nivel(ENivelIdioma.NATIVO)
                .build();
        this.alumnoHablaIdiomaRepository.save(mattiChino);

        AlumnoHablaIdioma mattiItaliano = AlumnoHablaIdioma.builder()
                .idioma(italiano)
                .alumno(alumno7)
                .nivel(ENivelIdioma.AVANZADO)
                .descripcion("Cursados estudios de ESO en Italia")
                .build();
        this.alumnoHablaIdiomaRepository.save(mattiItaliano);

        AlumnoHablaIdioma mattiEspaniol = AlumnoHablaIdioma.builder()
                .idioma(espaniol)
                .alumno(alumno7)
                .nivel(ENivelIdioma.AVANZADO)
                .descripcion("Cursados estudios de grado superior en España")
                .build();
        this.alumnoHablaIdiomaRepository.save(mattiEspaniol);

        AlumnoHablaIdioma mattiIngles = AlumnoHablaIdioma.builder()
                .idioma(ingles)
                .alumno(alumno7)
                .nivel(ENivelIdioma.MEDIO)
                .build();
        this.alumnoHablaIdiomaRepository.save(mattiIngles);

        AlumnoHablaIdioma hectorIgnles   = AlumnoHablaIdioma.builder()
                .idioma(ingles)
                .alumno(alumno5)
                .nivel(ENivelIdioma.AVANZADO)
                .descripcion("1 año de experiencia trabajando en un hotel en Reino Unido")
                .build();
        this.alumnoHablaIdiomaRepository.save(hectorIgnles  );

        AlumnoHablaIdioma guilleIngles = AlumnoHablaIdioma.builder()
                .idioma(ingles)
                .alumno(alumno6)
                .nivel(ENivelIdioma.AVANZADO)
                .build();
        this.alumnoHablaIdiomaRepository.save(guilleIngles);

        AlumnoHablaIdioma michiIngles = AlumnoHablaIdioma.builder()
                .idioma(ingles)
                .alumno(alumno4)
                .nivel(ENivelIdioma.MEDIO)
                .build();
        this.alumnoHablaIdiomaRepository.save(michiIngles);


        //////////      EMPRESAS     ///////////////
        Empresa empresa1 = Empresa.builder()
                .nombre("Accenture")
                .email("acc@mail.com")
                .password(this.encoder.encode("123456"))
                .inglesSolicitado(EInglesSolicitado.IMPORTANTE)
                .modalidadesTrabajo(new HashSet<>(Set.of(EModalidadTrabajo.HIBRIDO, EModalidadTrabajo.PRESENCIAL)))
                .resumen("Empresa consultora")
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
                .nombre("Angular 12")
                .build();
        this.tecnologiaRepository.save(angularAccenture);

        Ubicacion ubi1emp1 = Ubicacion.builder()
                .pais("España")
                .ciudad("Málaga")
                .direccion("Calle Severo Ochoa, 18")
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
                .pathFoto("../../../../assets/img/babel_logo.jpg")
                .activo(true)
                .build();
        this.empresaRepository.save(empresa2);

        Tecnologia javaBabel = Tecnologia.builder()
                .empresa(empresa2)
                .nombre("Java 17")
                .build();
        this.tecnologiaRepository.save(javaBabel);

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
                .activo(true)
                .build();
        this.empresaRepository.save(empresa5);

        Empresa empresa6 = Empresa.builder()
                .nombre("ViewNext")
                .email("viewnext@mail.com")
                .password(this.encoder.encode("123456"))
                .inglesSolicitado(EInglesSolicitado.IMPORTANTE)
                .modalidadesTrabajo(new HashSet<>(Set.of(EModalidadTrabajo.PRESENCIAL)))
                .resumen("Empresa consultora")
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.empresaRepository.save(empresa6);

        Empresa empresa7 = Empresa.builder()
                .nombre("Ebury")
                .email("ebury@mail.com")
                .password(this.encoder.encode("123456"))
                .inglesSolicitado(EInglesSolicitado.IMPRESCINDIBLE)
                .modalidadesTrabajo(new HashSet<>(Set.of(EModalidadTrabajo.PRESENCIAL, EModalidadTrabajo.HIBRIDO)))
                .resumen("Empresa consultora internacional")
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.empresaRepository.save(empresa7);

        Empresa empresa8 = Empresa.builder()
                .nombre("Dekra")
                .email("dekra@mail.com")
                .password(this.encoder.encode("123456"))
                .inglesSolicitado(EInglesSolicitado.NO_NECESARIO)
                .modalidadesTrabajo(new HashSet<>(Set.of(EModalidadTrabajo.PRESENCIAL, EModalidadTrabajo.ONLINE)))
                .resumen("Empresa consultora")
                .pathFoto("../../../../assets/img/profile.png")
                .activo(false)
                .build();
        this.empresaRepository.save(empresa8);

        Empresa empresa9 = Empresa.builder()
                .nombre("Bartolomé Consultora")
                .email("bart@mail.com")
                .password(this.encoder.encode("123456"))
                .inglesSolicitado(EInglesSolicitado.NO_NECESARIO)
                .modalidadesTrabajo(new HashSet<>(Set.of(EModalidadTrabajo.PRESENCIAL)))
                .resumen("Empresa consultora de Fuengirola")
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.empresaRepository.save(empresa9);

        Empresa empresa10 = Empresa.builder()
                .nombre("My site works")
                .email("siteworks@mail.com")
                .password(this.encoder.encode("123456"))
                .inglesSolicitado(EInglesSolicitado.NO_NECESARIO)
                .modalidadesTrabajo(new HashSet<>(Set.of(EModalidadTrabajo.PRESENCIAL, EModalidadTrabajo.ONLINE, EModalidadTrabajo.HIBRIDO)))
                .resumen("Empresa consultora de Torremolinos")
                .pathFoto("../../../../assets/img/profile.png")
                .activo(true)
                .build();
        this.empresaRepository.save(empresa10);


        //////////      SOLICITUDES     ///////////////
        //Enviada de Mireya
        Solicitud solAlu1 = Solicitud.builder()
                .alumno(alumno1)
                .empresa(empresa2)
                .tipo(ETipoSolicitud.ALUMNO_A_EMPRESA)
                .descripcion("Buenos días, estaría muy interesada en realizar las prácticas con vosotros")
                .estado(EEstadoSolicitud.ENVIADA)
                .build();
        this.solicitudRepository.save(solAlu1);

        //Recibidas de Mireya
        Solicitud solEmp2 = Solicitud.builder()
                .alumno(alumno1)
                .empresa(empresa1)
                .tipo(ETipoSolicitud.EMPRESA_A_ALUMNO)
                .descripcion("Buenos días Mireya, actualmente nos encontramos eligiendo alumnos de FCT y tu perfil nos parece interesante")
                .estado(EEstadoSolicitud.ENVIADA)
                .build();
        this.solicitudRepository.save(solEmp2);

        Solicitud solEmp3 = Solicitud.builder()
                .alumno(alumno1)
                .empresa(empresa3)
                .tipo(ETipoSolicitud.EMPRESA_A_ALUMNO)
                .descripcion("Buenos días Mireya, nos interesaría conocerte más")
                .estado(EEstadoSolicitud.ENVIADA)
                .build();
        this.solicitudRepository.save(solEmp3);


        //////////      TUTORÍAS     ///////////////
        ProfesorTutorizaAlumno tutoria1 = ProfesorTutorizaAlumno.builder()
                .profesor(profe1)
                .alumno(alumno1)
                .tipoTutoria("Prácticas")
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
                .alumno(alumno5)
                .tipoTutoria("Proyecto")
                .build();
        this.profesorTutorizaAlumnoRepository.save(tutoria3);

        ProfesorTutorizaAlumno tutoria4 = ProfesorTutorizaAlumno.builder()
                .profesor(profe1)
                .alumno(alumno16)
                .tipoTutoria("Prácticas")
                .build();
        this.profesorTutorizaAlumnoRepository.save(tutoria4);

        ProfesorTutorizaAlumno tutoria5 = ProfesorTutorizaAlumno.builder()
                .profesor(profe1)
                .alumno(alumno17)
                .tipoTutoria("Prácticas")
                .build();
        this.profesorTutorizaAlumnoRepository.save(tutoria5);


    }


}
