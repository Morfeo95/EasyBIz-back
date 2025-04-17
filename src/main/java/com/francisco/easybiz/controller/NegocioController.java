package com.francisco.easybiz.controller;

import com.francisco.easybiz.domain.negocio.*;
import com.francisco.easybiz.domain.usuario.UsuarioRepository;
import com.francisco.easybiz.infra.security.UserValidation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/negocios")
public class NegocioController {
    @Autowired
    private NegocioRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UserValidation validacion;

    @PostMapping
    public ResponseEntity<DatosDeRespuestaNegocio> registraNegocio(@Valid @RequestBody DatosDeRegistroNegocio datos,
                                                                    @RequestHeader("Authorization") String token){
        if (!validacion.validaUsuario(token, datos.usuarioId())){
            return ResponseEntity.status(403).build();
        }
        var usuario = usuarioRepository.getReferenceById(datos.usuarioId());
        var negocio = new Negocio(datos, usuario);
        repository.save(negocio);
        var datosRespuesta = new DatosDeRespuestaNegocio(negocio);
        return ResponseEntity.ok(datosRespuesta);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosDeRespuestaNegocio> actualizaNegocio(@Valid @RequestBody DatosDeActualizacionNegocio datos,
                                                                      @RequestHeader("Authorization") String token){
        if (!validacion.validaUsuario(token, datos.usuarioId())){
            return ResponseEntity.status(403).build();
        }
        var negocio = repository.getReferenceById(datos.id());
        negocio.actualizate(datos);
        var datosRespuesta = new DatosDeRespuestaNegocio(negocio);
        return ResponseEntity.ok(datosRespuesta);
    }

    @GetMapping("usuario/{usuarioId}")
    public ResponseEntity<Page<DatosDeListadoNegocio>> obtenerTodosNegocios(@PathVariable Long usuarioId, Pageable paginacion,
                                                                             @RequestHeader("Authorization") String token){
        if (!validacion.validaUsuario(token, usuarioId)){
            return ResponseEntity.status(403).build();
        }
        var datosRespuesta = repository.findByUserId(usuarioId, paginacion).map(DatosDeListadoNegocio::new);
        return ResponseEntity.ok(datosRespuesta);
    }

    @GetMapping("{id}")
    public ResponseEntity<DatosDeRespuestaNegocio> obtenerDatosNegocio(@PathVariable Long id,
                                                                         @RequestHeader("Authorization") String token){
        var negocioOptional = repository.findById(id);
        if (negocioOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var negocio = negocioOptional.get();
        if (!validacion.validaUsuario(token, negocio.getUser().getId())){
            return ResponseEntity.status(403).build();
        }
        var datosRespuesta = new DatosDeRespuestaNegocio(negocio);
        return ResponseEntity.ok(datosRespuesta);
    }

    @DeleteMapping("{id}")
    public ResponseEntity eliminarNegocio(@PathVariable Long id,
                                           @RequestHeader("Authorization") String token){
        var negocioOptional = repository.findById(id);
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
