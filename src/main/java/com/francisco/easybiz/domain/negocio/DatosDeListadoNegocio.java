package com.francisco.easybiz.domain.negocio;

import com.francisco.easybiz.domain.producto.Producto;

import java.time.LocalDateTime;
import java.util.List;

public record DatosDeListadoNegocio(
        Long id,
        Long usuarioId,
        String name,
        String urlPhoto,
        List<Producto> productos,
        LocalDateTime fechaDeCreacion
) {
    public DatosDeListadoNegocio(Negocio negocio) {
        this(negocio.getId(), negocio.getUser().getId(), negocio.getName(),
                negocio.getUrlPhoto(), negocio.getProductos(), negocio.getFechaDeCreacion());
    }
}
