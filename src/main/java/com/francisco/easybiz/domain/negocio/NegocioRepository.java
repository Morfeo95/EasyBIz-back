package com.francisco.easybiz.domain.negocio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NegocioRepository extends JpaRepository<Negocio, Long> {
    Page<Negocio> findByUserId(Long usuarioId, Pageable paginacion);
}
