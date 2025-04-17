package com.francisco.easybiz.domain.estimado;

import com.francisco.easybiz.domain.gasto.GastoDiario;
import com.francisco.easybiz.domain.gasto.GastoPlanta;
import com.francisco.easybiz.domain.insumos.Insumos;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DatosDeRegistroEstimado(

        @NotNull
        Long usuarioId,

        @NotNull
        String moneda,

        @NotNull
        String nombre,

        @NotNull
        int unidadesProducidas,

        @NotNull
        int plazo,
        @NotNull
        int ganancia,

        List<Insumos> insumos,
        List<GastoPlanta> gastosPlant,
        List<GastoDiario> gastosDiarios
) {
}
