package org.iesvdm.fctconnect.config;


import lombok.extern.slf4j.Slf4j;
import org.iesvdm.fctconnect.security.AuthEntryPointToken;
import org.iesvdm.fctconnect.security.AuthTokenFilter;
import org.iesvdm.fctconnect.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointToken unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(unauthorizedHandler) )
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                    authorizationManagerRequestMatcherRegistry
                            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                            .requestMatchers("/v1/api/**").permitAll()
                            .requestMatchers("/v1/api/auth/register").hasAuthority("administrador")
                            .requestMatchers("/v1/api/auth/request-empresa").hasAuthority("administrador")
                            .requestMatchers("/v1/api/administradores/**").hasAuthority("administrador")
                            .requestMatchers("/v1/api/alumnos/**").hasAnyAuthority("administrador", "empresa", "profesor", "alumno")
                            .requestMatchers("/v1/api/alumnos/inactivos").hasAuthority("administrador")
                            .requestMatchers(HttpMethod.PUT, "/v1/api/alumnos/**").hasAnyAuthority("administrador", "alumno", "profesor")
                            .requestMatchers("/v1/api/empresas/**").hasAnyAuthority("administrador", "alumno", "profesor")
                            .requestMatchers("/v1/api/empresas/inactivas").hasAuthority("administrador")
                            .requestMatchers(HttpMethod.PUT, "/v1/api/empresas/**").hasAuthority("empresa")
                            .requestMatchers("/v1/api/formaciones/**").hasAuthority("administrador")
                            .requestMatchers("/v1/api/idiomas/idioma").hasAuthority("administrador")
                            .requestMatchers(HttpMethod.PUT, "/v1/api/idiomas/**").hasAuthority("alumno")
                            .requestMatchers(HttpMethod.DELETE, "/v1/api/idiomas/aluIdioma/**").hasAuthority("alumno")
                            .requestMatchers(HttpMethod.PUT, "/v1/api/mail/**").hasAuthority("administrador")
                            .requestMatchers(HttpMethod.PUT, "/v1/api/media/**").hasAnyAuthority("alumno", "administrador", "profesor", "empresa")
                            .requestMatchers("/v1/api/profesores/inactivos").hasAuthority("administrador")
                            .requestMatchers("/v1/api/profesores/**").hasAnyAuthority("administrador", "alumno", "empresa")
                            .requestMatchers(HttpMethod.PUT, "/v1/api/profesores/**").hasAuthority("profesor")
                            .requestMatchers(HttpMethod.POST, "/v1/api/tutorias/**").hasAuthority("administrador")
                            .requestMatchers(HttpMethod.DELETE, "/v1/api/tutorias/**").hasAuthority("administrador")
                            .requestMatchers(HttpMethod.GET, "/v1/api/tutorias/**").hasAnyAuthority("administrador", "profesor", "alumno", "empresa")
                            .requestMatchers(HttpMethod.POST, "/v1/api/solicitudes/**").hasAnyAuthority("alumno", "empresa")
                            .requestMatchers(HttpMethod.POST, "/v1/api/solicitudes/alu").hasAuthority("alumno")
                            .requestMatchers(HttpMethod.POST, "/v1/api/solicitudes/emp").hasAuthority("empresa")
                            .requestMatchers(HttpMethod.POST, "/v1/api/tecnologias/**").hasAuthority("empresa")
                            .requestMatchers(HttpMethod.POST, "/v1/api/users/**").hasAuthority("administrador")
                            .anyRequest().authenticated();
                });

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

}