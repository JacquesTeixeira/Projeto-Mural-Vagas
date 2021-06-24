package br.edu.ifrs.restinga.grupo_1.mural_api.controllers;

import br.edu.ifrs.restinga.grupo_1.mural_api.models.Administrador;
import br.edu.ifrs.restinga.grupo_1.mural_api.services.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<Administrador>> buscarTodos() {
        List<Administrador> administradores = administradorService.buscarTodos();
        return ResponseEntity.ok().body(administradores);
    }

    @RequestMapping(value="/paginado", method=RequestMethod.GET)
    public ResponseEntity<Page<Administrador>> buscarPorPagina(
            @RequestParam(value="pagina", defaultValue="0") Integer pagina,
            @RequestParam(value="linhasPorPagina", defaultValue="24") Integer linhasPorPagina,
            @RequestParam(value="ordem", defaultValue="nome") String ordem,
            @RequestParam(value="direcao", defaultValue="ASC") String direcao) {
        Page<Administrador> administradores = administradorService.buscarPaninado(pagina, linhasPorPagina, ordem, direcao);
        return ResponseEntity.ok().body(administradores);
    }

}
