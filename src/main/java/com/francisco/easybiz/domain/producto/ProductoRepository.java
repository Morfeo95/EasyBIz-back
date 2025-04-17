package com.francisco.easybiz.domain.producto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Page<DatosDeListadoProducto> findByNegocioId(Long usuarioId, Pageable paginacion);
}
