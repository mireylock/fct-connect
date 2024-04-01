package org.iesvdm.fctconnect.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesvdm.fctconnect.service.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;

@Service
public class AuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;



    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);


    private String parseToken(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }

        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = parseToken(request);
        if ( token != null ) {

            Object[] creationUsername = tokenUtils.getTimeCreationUsername(token);

            long currentTime = new Date().getTime();
            long creationTime = (Long) creationUsername[0];

            if ( currentTime - creationTime < 36000000) {
                //Tiempo de vida de un token de autenticacion

                UserDetails userDetails = userDetailsService.loadUserByUsername((String)creationUsername[1]);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);

            }

        }

        filterChain.doFilter(request, response);

    }

}