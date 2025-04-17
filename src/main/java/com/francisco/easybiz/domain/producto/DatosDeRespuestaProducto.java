package com.francisco.easybiz.domain.producto;


import java.time.LocalDateTime;

public record DatosDeRespuestaProducto(
         Long id,
         String nombre,
         double precio,
         Long negocioId,
         Long estimadoId,
         String descripcion,
         LocalDateTime fechaDeCreacion
         )
{
    public DatosDeRespuestaProducto(Producto produco) {
        this(
                produco.getId(),
                produco.getNombre(),
                produco.getPrecio(),
                produco.getNegocio().getId(),
                obtenerIdEstimadoSeguro(produco),
                produco.getDescripcion(),
                produco.getFechaDeCreacion()
        );
    }
    private static Long obtenerIdEstimadoSeguro(Producto producto) {
        return producto.getEstimado() != null ? producto.getEstimado().getId() : null;
    }
}
