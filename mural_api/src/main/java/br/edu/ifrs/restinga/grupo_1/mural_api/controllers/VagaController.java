package br.edu.ifrs.restinga.grupo_1.mural_api.controllers;

import br.edu.ifrs.restinga.grupo_1.mural_api.models.Vaga;
import br.edu.ifrs.restinga.grupo_1.mural_api.services.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
            @RequestParam(value="ordem", defaultValue="area") String ordem,
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
//
//    @RequestMapping(value = "/{vagaId}/areadasvagas", method = RequestMethod.POST)
//    public ResponseEntity<Void> createAreaDaVaga(@RequestBody AreaDaVaga areadavaga, @PathVariable Long vagaId) {
//        AreaDaVaga obj = this.vagaService.cadastrarAreaDaVaga(areadavaga, vagaId);
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}").buildAndExpand(obj.getId()).toUri();
//        return ResponseEntity.created(uri).build();
//    }
//
//    @RequestMapping(value = "/{vagaId}/areadasvagas/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<Void> updateAreaDaVaga(@RequestBody AreaDaVaga areadavaga,
//                @PathVariable Long id, @PathVariable Long vagaId) {
//        this.vagaService.editarAreaDaVaga(areadavaga, id, vagaId);
//        return ResponseEntity.noContent().build();
//    }
//
//    @RequestMapping(value = "/{vagaId}/areadasvagas", method = RequestMethod.GET)
//    public ResponseEntity<AreaDaVaga> findAreaDaVaga(@PathVariable Long vagaId) {
//        return ResponseEntity.ok().body(this.vagaService.buscarAreasDaVaga_Vaga(vagaId));
//    }
//
//    /*
//    @RequestMapping(value = "/{vagaId}/areadasvagas/{areaDaVagaId}", method = RequestMethod.DELETE)
//    public ResponseEntity<Void> destroyAreaDaVaga(@PathVariable Long areaDaVagaId, @PathVariable Long vagaId) {
//	    this.vagaService.excluir(areaDaVagaId);
//	    return ResponseEntity.noContent().build();
//
//    } */
    
}
