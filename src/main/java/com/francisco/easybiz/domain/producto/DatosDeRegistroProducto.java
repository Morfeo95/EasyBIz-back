package com.francisco.easybiz.domain.producto;


import jakarta.validation.constraints.NotNull;

public record DatosDeRegistroProducto(

        @NotNull
        String nombre,

        @NotNull
        double precio,

        @NotNull
        Long negocioId,

        Long estimadoId,
        String descripcion
) {
}
