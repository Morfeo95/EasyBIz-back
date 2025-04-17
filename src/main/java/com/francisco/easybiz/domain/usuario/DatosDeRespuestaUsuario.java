package com.francisco.easybiz.domain.usuario;

import java.time.LocalDateTime;

public record DatosDeRespuestaUsuario(
        Long id,
        String name,
        String email,
        LocalDateTime fechaDeCreacion
) {
    public DatosDeRespuestaUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getName(), usuario.getEmail(), usuario.getFechaDeCreacion());
    }
}
