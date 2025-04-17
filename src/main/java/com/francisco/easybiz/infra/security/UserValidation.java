package com.francisco.easybiz.infra.security;

import com.francisco.easybiz.domain.usuario.UsuarioRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidation {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    public boolean validaUsuario(String token, @NotNull Long id) {
        var usuarioOptional = repository.findById(id);
        if (usuarioOptional.isEmpty()){
            return false;
        }
        var usuario = usuarioOptional.get();
        var emailUsuarioAutenticado = tokenService.getSubject(token.replace("Bearer ", ""));
        return usuario.getEmail().equals(emailUsuarioAutenticado);
    }
}
