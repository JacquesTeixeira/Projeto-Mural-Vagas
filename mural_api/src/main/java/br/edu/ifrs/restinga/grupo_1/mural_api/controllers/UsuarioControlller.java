package br.edu.ifrs.restinga.grupo_1.mural_api.controllers;

import br.edu.ifrs.restinga.grupo_1.mural_api.models.Usuario;
import br.edu.ifrs.restinga.grupo_1.mural_api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControlller {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Usuario> buscarPoId(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.usuarioService.buscarPorId(id));
    }

    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
    public ResponseEntity<Usuario> buscarPorEmail(@PathVariable("email")String email) {
        return ResponseEntity.ok().body(this.usuarioService.buscarPorEmail(email));
    }
}
