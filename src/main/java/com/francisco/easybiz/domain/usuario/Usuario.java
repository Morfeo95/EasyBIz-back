package com.francisco.easybiz.domain.usuario;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity(name = "usuario")
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @CreationTimestamp
    private LocalDateTime fechaDeCreacion;

    public Usuario(){}

    public Usuario(@Valid DatosDeRegistroUsuario datos, String contrasenaEncriptada) {
        this.name = datos.name();
        this.email = datos.email();
        this.password = contrasenaEncriptada;
    }

    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void actualizate(@Valid DatosDeActualizacionUsuario datos) {
        if (datos.name() != null && !datos.name().equals("")){
            this.name = datos.name();
        }
        if (datos.email() != null && !datos.email().equals("")){
            this.email = datos.email();
        }
    }

    public void setPassword(String encode) {
        this.password=encode;
    }
}
