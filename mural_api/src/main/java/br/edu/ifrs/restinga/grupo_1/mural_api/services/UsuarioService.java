package br.edu.ifrs.restinga.grupo_1.mural_api.services;

import br.edu.ifrs.restinga.grupo_1.mural_api.security.UserSpringSecurity;
import org.springframework.security.core.context.SecurityContextHolder;

public class UsuarioService {
    public static UserSpringSecurity autenticado() {
        try {
            return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}
