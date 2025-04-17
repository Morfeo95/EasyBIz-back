package com.francisco.easybiz.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DatosDeActualizacionUsuario(
        @NotNull
        Long id,
        String name,
        @Email
        String email,
        String password
) {
}
