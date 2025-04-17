    package com.francisco.easybiz.controller;
    
    import com.francisco.easybiz.domain.usuario.*;
    import com.francisco.easybiz.infra.security.UserValidation;
    import jakarta.transaction.Transactional;
    import jakarta.validation.Valid;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.web.bind.annotation.*;
    
    @RestController
    @RequestMapping("/usuarios")
    public class UsuarioController {
    
        @Autowired
        private UsuarioRepository repository;
    
        @Autowired
        private BCryptPasswordEncoder encoder;
    
        @Autowired
        private UserValidation validacion;
    
        @PostMapping
        public ResponseEntity<DatosDeRespuestaUsuario> registraUsuario(@Valid @RequestBody DatosDeRegistroUsuario datos){
            var contrasenaEncriptada = encoder.encode(datos.password());
            var usuario = new Usuario(datos, contrasenaEncriptada);
            repository.save(usuario);
            var datosRespuesta = new DatosDeRespuestaUsuario(usuario);
            return ResponseEntity.ok(datosRespuesta);
        }
    
        @PutMapping
        @Transactional
        public ResponseEntity<DatosDeRespuestaUsuario> actualizaUsuario(@Valid @RequestBody DatosDeActualizacionUsuario datos,
                                                                        @RequestHeader("Authorization") String token){
            if (!validacion.validaUsuario(token, datos.id())){
                return ResponseEntity.status(403).build();
            }
            var usuario = repository.getReferenceById(datos.id());
            if (datos.password() != null && !datos.password().equals("")){
                usuario.setPassword(encoder.encode(datos.password()));
            }
            usuario.actualizate(datos);
            var datosRespuesta = new DatosDeRespuestaUsuario(usuario);
            return ResponseEntity.ok(datosRespuesta);
        }
    
        @GetMapping("{id}")
        public ResponseEntity<DatosDeRespuestaUsuario> obtenerDatosUsuario(@PathVariable Long id,
                                                                           @RequestHeader("Authorization") String token){
    
            if (!validacion.validaUsuario(token, id)){
                return ResponseEntity.status(403).build();
            }
            var usuario = repository.getReferenceById(id);
            var datosRespuesta = new DatosDeRespuestaUsuario(usuario);
            return ResponseEntity.ok(datosRespuesta);
        }
    
        @DeleteMapping("{id}")
        public ResponseEntity eliminarUsuario(@PathVariable Long id,
                                              @RequestHeader("Authorization") String token){
            if (!validacion.validaUsuario(token, id)){
                return ResponseEntity.status(403).build();
            }
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }
