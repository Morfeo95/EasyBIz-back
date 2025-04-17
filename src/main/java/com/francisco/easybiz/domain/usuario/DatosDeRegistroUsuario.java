package com.francisco.easybiz.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DatosDeRegistroUsuario(
        @NotNull
        String name,
        @NotNull
                @Email
        String email,
        @NotNull
        String password
) {
}
