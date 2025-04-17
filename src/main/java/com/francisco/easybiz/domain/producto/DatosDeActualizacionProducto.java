package com.francisco.easybiz.domain.producto;

import jakarta.validation.constraints.NotNull;

public record DatosDeActualizacionProducto(

        @NotNull
        Long id,

        String nombre,
        double precio,
        String descripcion
) {
}
