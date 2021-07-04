package br.edu.ifrs.restinga.grupo_1.mural_api.controllers;

import br.edu.ifrs.restinga.grupo_1.mural_api.models.Candidato;
import br.edu.ifrs.restinga.grupo_1.mural_api.models.Portfolio;
import br.edu.ifrs.restinga.grupo_1.mural_api.services.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {

    @Autowired
    private CandidatoService candidatoService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Candidato>> all() {
        List<Candidato> candidatos = this.candidatoService.buscarTodos();
        return ResponseEntity.ok().body(candidatos);
    }

    @RequestMapping(value = "/paginado", method = RequestMethod.GET)
    public ResponseEntity<Page<Candidato>> paginate(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "linhasPorPagina", defaultValue = "24") Integer linhasPorPagina,
            @RequestParam(value = "ordem", defaultValue = "nome") String ordem,
            @RequestParam(value = "direcao", defaultValue = "ASC") String direcao) {
        Page<Candidato> candidatos = this.candidatoService.buscarPaginado(pagina, linhasPorPagina, ordem, direcao);
        return ResponseEntity.ok().body(candidatos);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Candidato> show(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.candidatoService.buscarPorId(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Candidato candidato) {
        Candidato obj = this.candidatoService.cadastrar(candidato);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody Candidato candidato, @PathVariable Long id) {
        this.candidatoService.editar(candidato, id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> destroy(@PathVariable Long id) {
        this.candidatoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{candidatoId}/portfolios", method = RequestMethod.POST)
    public ResponseEntity<Void> createPortfolio(@RequestBody Portfolio portfolio, @PathVariable Long candidatoId) {
        Portfolio obj = this.candidatoService.cadastrarPortfolio(portfolio, candidatoId);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{candidatoId}/portfolios/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updatePortfolio(@RequestBody Portfolio portfolio,
                                                @PathVariable Long id, @PathVariable Long candidatoId) {
        this.candidatoService.editarPortfolio(portfolio, id, candidatoId);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{candidatoId}/portfolios", method = RequestMethod.GET)
    public ResponseEntity<Portfolio> findPortfolio(@PathVariable Long candidatoId) {
        return ResponseEntity.ok().body(this.candidatoService.buscarPortfolioCandidato(candidatoId));
    }

    @RequestMapping(value="/{candidatoId}/imageUpload", method = RequestMethod.PUT)
    public ResponseEntity<Void> imageUpload(@RequestParam("imagem") MultipartFile multipartFile,
                                            @RequestParam Long candidatoId) throws IOException {
        this.candidatoService.uploadImagemCandidato(multipartFile, candidatoId);
        return ResponseEntity.noContent().build();
    }
}
