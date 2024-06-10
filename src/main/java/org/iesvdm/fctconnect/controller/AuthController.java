package org.iesvdm.fctconnect.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.iesvdm.fctconnect.domain.*;
import org.iesvdm.fctconnect.domain.enums.ENivelFormacion;
import org.iesvdm.fctconnect.repository.*;
import org.iesvdm.fctconnect.security.TokenUtils;
import org.iesvdm.fctconnect.service.EmpresaRequestService;
import org.iesvdm.fctconnect.service.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/v1/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository userRepository;
    private final PasswordEncoder encoder;
    private final TokenUtils tokenUtils;
    private final AdministradorRepository administradorRepository;
    private final AlumnoRepository alumnoRepository;
    private final EmpresaRepository empresaRepository;
    private final ProfesorRepository profesorRepository;
    private final EmpresaRequestRepository empresaRequestRepository;
    private final EmpresaRequestService empresaRequestService;

    public AuthController(AuthenticationManager authenticationManager, UsuarioRepository userRepository, PasswordEncoder encoder, TokenUtils tokenUtils, AdministradorRepository administradorRepository, AlumnoRepository alumnoRepository, EmpresaRepository empresaRepository, ProfesorRepository profesorRepository, EmpresaRequestRepository empresaRequestRepository, EmpresaRequestService empresaRequestService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.tokenUtils = tokenUtils;
        this.administradorRepository = administradorRepository;
        this.alumnoRepository = alumnoRepository;
        this.empresaRepository = empresaRepository;
        this.profesorRepository = profesorRepository;
        this.empresaRequestRepository = empresaRequestRepository;
        this.empresaRequestService = empresaRequestService;
    }

    @PostMapping(value = {"/login-alumno", "/login-empresa", "/login-profesor"})
    public ResponseEntity<Map<String, Object>> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = tokenUtils.generateToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            String rol = userDetails.getAuthority().getAuthority();

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("id", userDetails.getId());
            response.put("email", userDetails.getEmail());
            response.put("rol", rol);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Unauthorized");
            response.put("message", "Invalid email or password");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(value = {"/register"})
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            log.info("Email ya en uso");
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email ya en uso!"));
        }

        String rol = registerRequest.getRol();
        String encodedPassword = encoder.encode(registerRequest.getPassword());

        if (rol != null) {
            switch (rol) {
                case "administrador":
                    Administrador nuevoAdmin = Administrador.builder()
                            .email(registerRequest.getEmail())
                            .password(encodedPassword)
                            .nombre(registerRequest.getNombre())
                            .apellido1(registerRequest.getApellido1())
                            .apellido2(registerRequest.getApellido2())
                            .dni(registerRequest.getDni())
                            .pathFoto(registerRequest.getPathFoto())
                            .activo(true)
                            .build();
                    administradorRepository.save(nuevoAdmin);
                    break;

                case "alumno":
                    Alumno nuevoAlumno = Alumno.builder()
                            .email(registerRequest.getEmail())
                            .password(encodedPassword)
                            .nombre(registerRequest.getNombre())
                            .apellido1(registerRequest.getApellido1())
                            .apellido2(registerRequest.getApellido2())
                            .dni(registerRequest.getDni())
                            .pathFoto(registerRequest.getPathFoto())
                            .vehiculoPropio(registerRequest.getCarnetConducir())
                            .carnetConducir(registerRequest.getVehiculoPropio())
                            .formacion(registerRequest.getFormacion())
                            .activo(true)
                            .build();
                    alumnoRepository.save(nuevoAlumno);
                    break;

                case "empresa":
                    Empresa nuevaEmpresa = Empresa.builder()
                            .email(registerRequest.getEmail())
                            .password(encodedPassword)
                            .nombre(registerRequest.getNombre())
                            .pathFoto(registerRequest.getPathFoto())
                            .activo(true)
                            .build();
                    empresaRepository.save(nuevaEmpresa);
                    break;

                case "profesor":
                    Profesor nuevoProfesor = Profesor.builder()
                            .email(registerRequest.getEmail())
                            .password(encodedPassword)
                            .nombre(registerRequest.getNombre())
                            .apellido1(registerRequest.getApellido1())
                            .apellido2(registerRequest.getApellido2())
                            .dni(registerRequest.getDni())
                            .pathFoto(registerRequest.getPathFoto())
                            .departamento(registerRequest.getDepartamento())
                            .activo(true)
                            .build();
                    profesorRepository.save(nuevoProfesor);
                    break;
            }
        }

        return ResponseEntity.ok(new MessageResponse("Usuario registrado correctamente!"));
    }

    @GetMapping("/request-empresa")
    public List<EmpresaRequest> all() {
        return this.empresaRequestService.all();
    }


    @PostMapping("/request-empresa")
    public ResponseEntity<?> registerEmpresaRequest(@Valid @RequestBody RegisterRequestEmpresa registerRequestEmpresa) {
        if (userRepository.existsByEmail(registerRequestEmpresa.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email ya en uso!"));
        }

        EmpresaRequest empresaRequest = new EmpresaRequest();
        empresaRequest.setEmail(registerRequestEmpresa.getEmail());
        empresaRequest.setPassword(registerRequestEmpresa.getPassword());
        empresaRequest.setNombre(registerRequestEmpresa.getNombre());
        empresaRequest.setPathFoto(registerRequestEmpresa.getPathFoto());
        empresaRequestRepository.save(empresaRequest);

        return ResponseEntity.ok(new MessageResponse("Solicitud de registro enviada correctamente!"));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/request-empresa/{id}")
    public void deleteEmpresaRequest(@PathVariable("id") Long id) {
        this.empresaRequestService.delete(id);
    }

}