package com.francisco.easybiz.infra.converters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.francisco.easybiz.domain.gasto.GastoDiario;

import java.util.List;

public class ConversorJsonGastoDiario extends ConversorJson<GastoDiario>{
    public ConversorJsonGastoDiario(){
        super(new TypeReference<List<GastoDiario>>() {});
    }
}
