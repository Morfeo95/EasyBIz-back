package com.francisco.easybiz.infra.converters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.francisco.easybiz.domain.producto.Producto;

import java.util.List;

public class ConversorJsonProducto extends ConversorJson<Producto>{
    public ConversorJsonProducto(){
        super(new TypeReference<List<Producto>>() {});
    }
}
