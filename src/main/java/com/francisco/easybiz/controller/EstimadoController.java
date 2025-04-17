package com.francisco.easybiz.controller;

import com.francisco.easybiz.domain.estimado.*;
import com.francisco.easybiz.domain.usuario.*;
import com.francisco.easybiz.infra.security.UserValidation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estimado")
public class EstimadoController {
    @Autowired
    private EstimadoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UserValidation validacion;

    @PostMapping
    public ResponseEntity<DatosDeRespuestaEstimado> registraEstimado(@Valid @RequestBody DatosDeRegistroEstimado datos,
                                                                     @RequestHeader("Authorization") String token){
        if (!validacion.validaUsuario(token, datos.usuarioId())){
            return ResponseEntity.status(403).build();
        }
        var usuario = usuarioRepository.getReferenceById(datos.usuarioId());
        var estimado = new Estimado(datos, usuario);
        repository.save(estimado);
        var datosRespuesta = new DatosDeRespuestaEstimado(estimado);
        return ResponseEntity.ok(datosRespuesta);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosDeRespuestaEstimado> actualizaEstimado(@Valid @RequestBody DatosDeActualizacionEstimado datos,
                                                                    @RequestHeader("Authorization") String token){
        if (!validacion.validaUsuario(token, datos.usuarioId())){
            return ResponseEntity.status(403).build();
        }
        var estimado = repository.getReferenceById(datos.id());
        estimado.actualizate(datos);
        var datosRespuesta = new DatosDeRespuestaEstimado(estimado);
        return ResponseEntity.ok(datosRespuesta);
    }

    @GetMapping("/lista-estimados/{usuarioId}")
    public ResponseEntity<Page<DatosDeListadoEstimado>> obtenerTodosEstimado(@PathVariable Long usuarioId, Pageable paginacion,
                                                                             @RequestHeader("Authorization") String token){
        if (!validacion.validaUsuario(token, usuarioId)){
            return ResponseEntity.status(403).build();
        }
        var datosRespuesta = repository.findByUsuarioId(usuarioId, paginacion);
        return ResponseEntity.ok(datosRespuesta);
    }

    @GetMapping("{id}")
    public ResponseEntity<DatosDeRespuestaEstimado> obtenerDatosEstimado(@PathVariable Long id,
                                                                       @RequestHeader("Authorization") String token){
        var estimadoOptional = repository.findById(id);
        if (estimadoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var estimado = estimadoOptional.get();
        if (!validacion.validaUsuario(token, estimado.getUsuario().getId())){
            return ResponseEntity.status(403).build();
        }
        var datosRespuesta = new DatosDeRespuestaEstimado(estimado);
        return ResponseEntity.ok(datosRespuesta);
    }

    @DeleteMapping("{id}")
    public ResponseEntity eliminarEstimado(@PathVariable Long id,
                                          @RequestHeader("Authorization") String token){
        var estiadoOptional = repository.findById(id);
        if (estiadoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var estimado = estiadoOptional.get();
        if (!validacion.validaUsuario(token, estimado.getUsuario().getId())){
            return ResponseEntity.status(403).build();
        }
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
