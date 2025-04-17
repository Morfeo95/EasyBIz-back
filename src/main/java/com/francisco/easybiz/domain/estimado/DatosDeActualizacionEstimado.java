package com.francisco.easybiz.domain.estimado;

import com.francisco.easybiz.domain.gasto.GastoDiario;
import com.francisco.easybiz.domain.gasto.GastoPlanta;
import com.francisco.easybiz.domain.insumos.Insumos;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DatosDeActualizacionEstimado(
        @NotNull
        Long id,

        @NotNull
        Long usuarioId,

        String moneda,
        String nombre,
        int unidadesProducidas,
        int plazo,
        int ganancia,
        List<Insumos> insumos,
        List<GastoPlanta> gastosPlant,
        List<GastoDiario> gastosDiarios
) {
}
