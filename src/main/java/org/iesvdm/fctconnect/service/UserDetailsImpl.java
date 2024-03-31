//package org.iesvdm.fctconnect.service;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import org.iesvdm.fctconnect.domain.Usuario;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import java.util.Objects;
//
//public class UserDetailsImpl {
//    private static final long serialVersionUID = 1L;
//
//    private Long id;
//    private String email;
//
//    @JsonIgnore
//    private String password;
//
//    private GrantedAuthority authority;
//
//    public UserDetailsImpl(Long id, String email, String password,
//                           GrantedAuthority authority) {
//        this.id = id;
//        this.email = email;
//        this.password = password;
//        this.authority = authority;
//    }
//
//    public static UserDetailsImpl build(Usuario user) {
//        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRol());
//
//        return new UserDetailsImpl(
//                user.getId(),
//                user.getEmail(),
//                user.getPassword(),
//                authority);
//    }
//
//    public GrantedAuthority getAuthority() {
//        return authority;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    public boolean isEnabled() {
//        return true;
//    }
//
//    public boolean equals(Object o) {
//        if (this == o)
//            return true;
//        if (o == null || getClass() != o.getClass())
//            return false;
//        UserDetailsImpl user = (UserDetailsImpl) o;
//        return Objects.equals(id, user.id);
//    }
//}
