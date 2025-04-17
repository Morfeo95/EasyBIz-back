package com.francisco.easybiz.domain.estimado;

import com.francisco.easybiz.domain.gasto.GastoDiario;
import com.francisco.easybiz.domain.gasto.GastoPlanta;
import com.francisco.easybiz.domain.insumos.Insumos;

import java.time.LocalDateTime;
import java.util.List;

public record DatosDeListadoEstimado(
        Long id,
        String moneda,
        String nombre,
        int unidadesProducidas,
        int plazo,
        int ganancia,
        List<Insumos> insumos,
        List<GastoPlanta> gastosPlanta,
        List<GastoDiario> gastosDiarios,
        LocalDateTime fecha
) {
}
