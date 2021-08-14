package br.edu.ifrs.restinga.grupo_1.mural_api.services;

import br.edu.ifrs.restinga.grupo_1.mural_api.models.Administrador;
import br.edu.ifrs.restinga.grupo_1.mural_api.models.Usuario;
import br.edu.ifrs.restinga.grupo_1.mural_api.repositories.UsuarioRepository;
import br.edu.ifrs.restinga.grupo_1.mural_api.security.UserSpringSecurity;
import br.edu.ifrs.restinga.grupo_1.mural_api.services.exceptions.AuthorizationException;
import br.edu.ifrs.restinga.grupo_1.mural_api.services.exceptions.ObjectNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario buscarPorId(Long id) {
        UserSpringSecurity user = UsuarioService.autenticado();
        if (user == null || id != user.getId()) {
            throw new AuthorizationException("Acesso negado");
        }
        try {
            return this.usuarioRepository.findById(id).orElseThrow(()
                    -> new ObjectNotFound("Não existe administrador com id informado"));
        } catch (NoSuchElementException e) {
            throw new ObjectNotFound("Não existe administrador com id informado");
        }
    }

    public Usuario buscarPorEmail(String email) {
        UserSpringSecurity user = UsuarioService.autenticado();
        if (user == null || !email.equals(user.getUsername())) {
            throw new AuthorizationException("Acesso negado");
        }
        try {
            return this.usuarioRepository.findByEmail(email);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFound("Não existe administrador com id informado");
        }
    }

    public static UserSpringSecurity autenticado() {
        try {
            return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}
