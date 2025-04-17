package com.francisco.easybiz.domain.producto;

import java.time.LocalDateTime;

public record DatosDeListadoProducto(
        Long id,
        String nombre,
        double precio,
        Long negocioId,
        Long estimadoId,
        String descripcion,
        LocalDateTime fechaDeCreacion
) {
}
