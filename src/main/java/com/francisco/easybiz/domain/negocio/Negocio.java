package com.francisco.easybiz.domain.negocio;

import com.francisco.easybiz.domain.producto.Producto;
import com.francisco.easybiz.domain.usuario.Usuario;
import com.francisco.easybiz.infra.converters.ConversorJsonProducto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "negocio")
@Table(name = "negocios")
public class Negocio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario user;

    private String name;

    private String urlPhoto;

    @Convert(converter = ConversorJsonProducto.class)
    @Column(columnDefinition = "json")
    private List<Producto> productos;

    @CreationTimestamp
    private LocalDateTime fechaDeCreacion;

    public Negocio(@Valid DatosDeRegistroNegocio datos, Usuario usuario) {
        this.user=usuario;
        this.name= datos.name();
        if (datos.urlPhoto()!= null && datos.urlPhoto()!=""){
            this.urlPhoto= datos.urlPhoto();
        }if (datos.productos() != null && !datos.productos().isEmpty()){
            this.productos=datos.productos();
        }
    }

    public Negocio(){}

    public Long getId() {
        return id;
    }

    public Usuario getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void actualizate(@Valid DatosDeActualizacionNegocio datos) {
        if (datos.name() != null && !datos.name().isEmpty()) {
            this.name = datos.name();
        }
        if (datos.urlPhoto()!= null && datos.urlPhoto() != "") {
            this.urlPhoto= datos.urlPhoto();
        }
        if (datos.productos()!= null && !datos.productos().isEmpty()) {
            this.productos=datos.productos();
        }
    }
}
