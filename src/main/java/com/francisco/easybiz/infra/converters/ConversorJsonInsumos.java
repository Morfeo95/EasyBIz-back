package com.francisco.easybiz.infra.converters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.francisco.easybiz.domain.insumos.Insumos;

import java.util.List;

public class ConversorJsonInsumos extends ConversorJson<Insumos>{
    public ConversorJsonInsumos() {
        super(new TypeReference<List<Insumos>>() {});
    }
}
