package br.edu.ifrs.restinga.grupo_1.mural_api.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ifrs.restinga.grupo_1.mural_api.models.Vaga;
import br.edu.ifrs.restinga.grupo_1.mural_api.services.VagaService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/vagas")
public class VagaController {
	
	@Autowired
	private VagaService vagaService;
	
	@RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<Vaga>> all() {
        List<Vaga> vagas = this.vagaService.buscarTodas();
        return ResponseEntity.ok().body(vagas);

    }

    @RequestMapping(value="/paginado", method=RequestMethod.GET)
    public ResponseEntity<Page<Vaga>> paginate(
            @RequestParam(value="pagina", defaultValue="0") Integer pagina,
            @RequestParam(value="linhasPorPagina", defaultValue="24") Integer linhasPorPagina,
            @RequestParam(value="ordem", defaultValue="nome") String ordem,
            @RequestParam(value="direcao", defaultValue="ASC") String direcao) {
        Page<Vaga> vagas = this.vagaService.buscarPaginado(pagina, linhasPorPagina, ordem, direcao);
        return ResponseEntity.ok().body(vagas);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Vaga> show(@PathVariable("id") Long id) {
	    return ResponseEntity.ok().body(this.vagaService.buscarPorId(id));

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Vaga vaga) {
	    Vaga obj = this.vagaService.cadastrar(vaga);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody Vaga vaga, @PathVariable Long id) {
        this.vagaService.editar(vaga, id);
        return ResponseEntity.noContent().build();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> destroy(@PathVariable Long id) {
	    this.vagaService.excluir(id);
	    return ResponseEntity.noContent().build();

    }
	
	

}
