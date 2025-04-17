package com.francisco.easybiz.infra.converters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.francisco.easybiz.domain.gasto.GastoPlanta;

import java.util.List;

public class ConversorJsonGastosPlanta extends ConversorJson<GastoPlanta>{

    public ConversorJsonGastosPlanta(){
        super(new TypeReference<List<GastoPlanta>>() {});
    }

}
