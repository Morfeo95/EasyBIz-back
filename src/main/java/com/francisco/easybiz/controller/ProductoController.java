package com.francisco.easybiz.controller;

import com.francisco.easybiz.domain.estimado.EstimadoRepository;
import com.francisco.easybiz.domain.negocio.*;
import com.francisco.easybiz.domain.producto.*;
import com.francisco.easybiz.infra.security.UserValidation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    private ProductoRepository repository;

    @Autowired
    private NegocioRepository negocioRepository;

    @Autowired
    private EstimadoRepository estimadoRepository;

    @Autowired
    private UserValidation validacion;

    @PostMapping
    public ResponseEntity<DatosDeRespuestaProducto> registraProducto(@Valid @RequestBody DatosDeRegistroProducto datos,
                                                                    @RequestHeader("Authorization") String token){
        var negocioOptional = negocioRepository.findById(datos.negocioId());
        if (negocioOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var negocio = negocioOptional.get();
        if (!validacion.validaUsuario(token, negocio.getUser().getId())){
            return ResponseEntity.status(403).build();
        }
        var produco = new Producto(datos, negocio);
        if (datos.estimadoId() != null){
            var estimadoOptional = estimadoRepository.findById(datos.estimadoId());
            if (estimadoOptional.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            var estimado = estimadoOptional.get();
            produco.setEstimado(estimado);
            repository.save(produco);
            var datosRespuesta = new DatosDeRespuestaProducto(produco);
        }
        repository.save(produco);
        var datosRespuesta = new DatosDeRespuestaProducto(produco);
        return ResponseEntity.ok(datosRespuesta);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosDeRespuestaProducto> actualizaProducto(@Valid @RequestBody DatosDeActualizacionProducto datos,
                                                                    @RequestHeader("Authorization") String token){
        var productoOptional = repository.findById(datos.id());
        if (productoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var producto = productoOptional.get();
        var negocioOptional = negocioRepository.findById(producto.getNegocio().getId());
        if (negocioOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var negocio = negocioOptional.get();
        if (!validacion.validaUsuario(token, negocio.getUser().getId())){
            return ResponseEntity.status(403).build();
        }
        producto.actualizate(datos);
        var datosRespuesta = new DatosDeRespuestaProducto(producto);
        return ResponseEntity.ok(datosRespuesta);
    }

    @GetMapping("/productos/{negocioId}")
    public ResponseEntity<Page<DatosDeListadoProducto>> obtenerTodosProducto(@PathVariable Long negocioId, Pageable paginacion,
                                                                            @RequestHeader("Authorization") String token){
        var negocioOptional = negocioRepository.findById(negocioId);
        if (negocioOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var negocio = negocioOptional.get();
        if (!validacion.validaUsuario(token, negocio.getUser().getId())){
            return ResponseEntity.status(403).build();
        }
        var datosRespuesta = repository.findByNegocioId(negocioId, paginacion);
        return ResponseEntity.ok(datosRespuesta);
    }

    @GetMapping("{id}")
    public ResponseEntity<DatosDeRespuestaProducto> obtenerDatosProducto(@PathVariable Long id,
                                                                       @RequestHeader("Authorization") String token){
        var productoOptional = repository.findById(id);
        if (productoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var producto = productoOptional.get();
        var negocioOptional = negocioRepository.findById(producto.getNegocio().getId());
        if (negocioOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var negocio = negocioOptional.get();
        if (!validacion.validaUsuario(token, negocio.getUser().getId())){
            return ResponseEntity.status(403).build();
        }
        var datosRespuesta = new DatosDeRespuestaProducto(producto);
        return ResponseEntity.ok(datosRespuesta);
    }

    @DeleteMapping("{id}")
    public ResponseEntity eliminarProducto(@PathVariable Long id,
                                          @RequestHeader("Authorization") String token){
        var productoOptional = repository.findById(id);
        if (productoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var producto = productoOptional.get();
        var negocioOptional = negocioRepository.findById(producto.getNegocio().getId());
        if (negocioOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var negocio = negocioOptional.get();
        if (!validacion.validaUsuario(token, negocio.getUser().getId())){
            return ResponseEntity.status(403).build();
        }
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
