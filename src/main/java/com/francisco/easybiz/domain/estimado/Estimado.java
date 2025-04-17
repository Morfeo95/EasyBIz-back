package com.francisco.easybiz.domain.estimado;

import com.francisco.easybiz.domain.gasto.GastoDiario;
import com.francisco.easybiz.domain.gasto.GastoPlanta;
import com.francisco.easybiz.domain.insumos.Insumos;
import com.francisco.easybiz.domain.usuario.Usuario;
import com.francisco.easybiz.infra.converters.ConversorJsonGastoDiario;
import com.francisco.easybiz.infra.converters.ConversorJsonGastosPlanta;
import com.francisco.easybiz.infra.converters.ConversorJsonInsumos;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "estimado")
@Table(name = "estimados")
public class Estimado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    private String moneda;
    private String nombre;
    private int unidadesProducidas;
    private int plazo;
    private int ganancia;

    @CreationTimestamp
    private LocalDateTime fecha;

    @Convert(converter = ConversorJsonInsumos.class)
    @Column(columnDefinition = "json")
    private List<Insumos> insumos;

    @Convert(converter = ConversorJsonGastosPlanta.class)
    @Column(columnDefinition = "json")
    private List<GastoPlanta> gastosPlanta;

    @Convert(converter = ConversorJsonGastoDiario.class)
    @Column(columnDefinition = "json")
    private List<GastoDiario> gastosDiarios;

    public Estimado() {
    }

    public Estimado(@Valid DatosDeRegistroEstimado datos, Usuario usuario) {
        this.moneda = datos.moneda();
        this.nombre = datos.nombre();
        this.unidadesProducidas = datos.unidadesProducidas();
        this.plazo = datos.plazo();
        this.ganancia = datos.ganancia();
        this.insumos = datos.insumos();
        this.gastosPlanta = datos.gastosPlant();
        this.gastosDiarios = datos.gastosDiarios();
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public String getMoneda() {
        return moneda;
    }

    public String getNombre() {
        return nombre;
    }

    public int getUnidadesProducidas() {
        return unidadesProducidas;
    }

    public int getPlazo() {
        return plazo;
    }

    public int getGanancia() {
        return ganancia;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public List<Insumos> getInsumos() {
        return insumos;
    }

    public List<GastoPlanta> getGastosPlanta() {
        return gastosPlanta;
    }

    public List<GastoDiario> getGastosDiarios() {
        return gastosDiarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void actualizate(@Valid DatosDeActualizacionEstimado datos) {
        if (datos.moneda() != null && datos.moneda() != "") {
            this.moneda=datos.moneda();
        }
        if (datos.nombre() != null && datos.nombre() != "") {
            this.nombre=datos.nombre();
        }
        if (datos.unidadesProducidas()>0) {
            this.unidadesProducidas=datos.unidadesProducidas();
        }
        if (datos.plazo()>0) {
            this.plazo=datos.plazo();
        }
        if (datos.ganancia()>0) {
            this.ganancia=datos.ganancia();
        }
        if (!datos.insumos().isEmpty()) {
            this.insumos=datos.insumos();
        }
        if (!datos.gastosPlant().isEmpty()) {
            this.gastosPlanta=datos.gastosPlant();
        }
        if (!datos.gastosDiarios().isEmpty()) {
            this.gastosDiarios=datos.gastosDiarios();
        }
    }
}