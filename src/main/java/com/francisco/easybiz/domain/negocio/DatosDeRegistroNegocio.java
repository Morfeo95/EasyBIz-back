package com.francisco.easybiz.domain.negocio;

import com.francisco.easybiz.domain.producto.Producto;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

public record DatosDeRegistroNegocio(

        @NotNull
        Long usuarioId,

        @NotNull
        String name,
        String urlPhoto,
        List<Producto> productos
) {
}
