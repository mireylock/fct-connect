package org.iesvdm.fctconnect.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.extern.slf4j.Slf4j;
import org.iesvdm.fctconnect.domain.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Slf4j
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String email;

    private boolean activo;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String email, String password, boolean activo,
                           GrantedAuthority authority) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.activo = activo;
        this.authorities = Collections.singletonList(authority);
    }

    public static UserDetailsImpl build(Usuario user) {
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRol());

        return new UserDetailsImpl(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getActivo(),
                authority);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    public GrantedAuthority getAuthority() {
        return authorities.iterator().next();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }


    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return activo;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
