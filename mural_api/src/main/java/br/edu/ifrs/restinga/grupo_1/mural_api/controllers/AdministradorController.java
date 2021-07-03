package br.edu.ifrs.restinga.grupo_1.mural_api.controllers;

import br.edu.ifrs.restinga.grupo_1.mural_api.models.Administrador;
import br.edu.ifrs.restinga.grupo_1.mural_api.services.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Administrador>> all() {
        List<Administrador> administradores = this.administradorService.buscarTodos();
        return ResponseEntity.ok().body(administradores);
    }

    @RequestMapping(value = "/paginado", method = RequestMethod.GET)
    public ResponseEntity<Page<Administrador>> paginate(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "linhasPorPagina", defaultValue = "24") Integer linhasPorPagina,
            @RequestParam(value = "ordem", defaultValue = "nome") String ordem,
            @RequestParam(value = "direcao", defaultValue = "ASC") String direcao) {
        Page<Administrador> administradores = this.administradorService.buscarPaginado(pagina, linhasPorPagina, ordem, direcao);
        return ResponseEntity.ok().body(administradores);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Administrador> show(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.administradorService.buscarPorId(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Administrador administrador) {
        Administrador obj = this.administradorService.cadastrar(administrador);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody Administrador administrador, @PathVariable Long id) {
        this.administradorService.editar(administrador, id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> destroy(@PathVariable Long id) {
        this.administradorService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
