package com.francisco.easybiz.domain.producto;

import com.francisco.easybiz.domain.estimado.Estimado;
import com.francisco.easybiz.domain.negocio.Negocio;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "producto")
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private double precio;

    @ManyToOne
    @JoinColumn(name = "negocio_id", nullable = false)
    private Negocio negocio;

    @ManyToOne
    @JoinColumn(name = "estimado_id")
    private Estimado estimado;

    private String descripcion;

    @CreationTimestamp
    private LocalDateTime fechaDeCreacion;

    public Producto(){}

    public Producto(@Valid DatosDeRegistroProducto datos, Negocio negocio) {
        this.nombre = datos.nombre();
        this.precio = datos.precio();
        this.negocio = negocio;
        this.descripcion = datos.descripcion();
    }

    public void setEstimado(Estimado estimado) {
        this.estimado = estimado;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public Negocio getNegocio() {
        return negocio;
    }

    public Estimado getEstimado() {
        return estimado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void actualizate(@Valid DatosDeActualizacionProducto datos) {
        if (datos.nombre() != null && datos.nombre() != ""){
            this.nombre = datos.nombre();
        }
        if (this.precio != datos.precio()){
            this.precio = datos.precio();
        }
        if (datos.descripcion() != null && datos.descripcion() != ""){
            this.descripcion = datos.descripcion();
        }
    }
}
