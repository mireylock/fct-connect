package org.iesvdm.fctconnect.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.iesvdm.fctconnect.domain.*;
import org.iesvdm.fctconnect.repository.*;
import org.iesvdm.fctconnect.security.TokenUtils;
import org.iesvdm.fctconnect.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/v1/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    AdministradorRepository administradorRepository;

    @Autowired
    AlumnoRepository alumnoRepository;

    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    ProfesorRepository profesorRepository;

    @PostMapping("/login-alumno")
    public ResponseEntity<Map<String, Object>> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

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
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email ya en uso!"));
        }

        String rol = registerRequest.getRol();
        String encodedPassword = encoder.encode(registerRequest.getPassword());

        if (rol !=null) {
            switch (rol) {
                case "administrador":
                    Administrador nuevoAdmin = new Administrador();
                    nuevoAdmin.setEmail(registerRequest.getEmail());
                    nuevoAdmin.setPassword(encodedPassword);
                    administradorRepository.save(nuevoAdmin);
                    break;

                case "alumno":
                    Alumno nuevoAlumno = new Alumno();
                    nuevoAlumno.setEmail(registerRequest.getEmail());
                    nuevoAlumno.setPassword(encodedPassword);
                    alumnoRepository.save(nuevoAlumno);
                    break;

                case "empresa":
                    Empresa nuevaEmpresa = new Empresa();
                    nuevaEmpresa.setEmail(registerRequest.getEmail());
                    nuevaEmpresa.setPassword(encodedPassword);
                    empresaRepository.save(nuevaEmpresa);
                    break;

                case "profesor":
                    Profesor nuevoProfesor = new Profesor();
                    nuevoProfesor.setEmail(registerRequest.getEmail());
                    nuevoProfesor.setPassword(encodedPassword);
                    profesorRepository.save(nuevoProfesor);
                    break;
            }
        }

        return ResponseEntity.ok(new MessageResponse("Usuario registrado correctamente!"));
    }

}