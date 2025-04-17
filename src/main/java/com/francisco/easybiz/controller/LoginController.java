package com.francisco.easybiz.controller;

import com.francisco.easybiz.domain.usuario.DatosDeAuteticacionUsuario;
import com.francisco.easybiz.domain.usuario.Usuario;
import com.francisco.easybiz.domain.usuario.UsuarioRepository;
import com.francisco.easybiz.infra.security.DatosJWT;
import com.francisco.easybiz.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity logIn(@RequestBody @Valid DatosDeAuteticacionUsuario datosAutenticacion){
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacion.email(),datosAutenticacion.password());
        var usuario = repository.getReferenceByEmail(datosAutenticacion.email());
        var usuarioAuth = authenticationManager.authenticate(authToken);
        var token = tokenService.generarToken((Usuario) usuarioAuth.getPrincipal());
        return ResponseEntity.ok(new DatosJWT(usuario.getId(), token));
    }
}

