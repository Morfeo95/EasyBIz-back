package com.francisco.easybiz.domain.estimado;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstimadoRepository extends JpaRepository<Estimado, Long> {
    Page<DatosDeListadoEstimado> findByUsuarioId(Long usuarioId, Pageable paginacion);

}
