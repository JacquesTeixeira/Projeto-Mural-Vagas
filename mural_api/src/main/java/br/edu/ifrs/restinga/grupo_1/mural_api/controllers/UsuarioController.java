package br.edu.ifrs.restinga.grupo_1.mural_api.controllers;

import br.edu.ifrs.restinga.grupo_1.mural_api.models.Administrador;
import br.edu.ifrs.restinga.grupo_1.mural_api.models.Usuario;
import br.edu.ifrs.restinga.grupo_1.mural_api.services.UsuarioService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<Usuario>> buscarTodos() {
        List<Usuario> usuarios = this.usuarioService.buscarTodos();
        return ResponseEntity.ok().body(usuarios);
    }

    @RequestMapping(value="/paginado", method=RequestMethod.GET)
    public ResponseEntity<Page<Usuario>> buscarPorPagina(
            @RequestParam(value="pagina", defaultValue="0") Integer pagina,
            @RequestParam(value="linhasPorPagina", defaultValue="24") Integer linhasPorPagina,
            @RequestParam(value="ordem", defaultValue="nome") String ordem,
            @RequestParam(value="direcao", defaultValue="ASC") String direcao) {
        Page<Usuario> usuarios = this.usuarioService.buscarPaginado(pagina, linhasPorPagina, ordem, direcao);
        return ResponseEntity.ok().body(usuarios);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Usuario> show(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.usuarioService.show(id));
    }

}
