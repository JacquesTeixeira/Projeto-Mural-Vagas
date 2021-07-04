package br.edu.ifrs.restinga.grupo_1.mural_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.restinga.grupo_1.mural_api.models.Vaga;
import br.edu.ifrs.restinga.grupo_1.mural_api.services.VagaService;

@RestController
@RequestMapping("/vagas")
public class VagaController {
	
	@Autowired
	private VagaService vagaService;
	
	@RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<Vaga>> buscarTodas() {
        List<Vaga> vagas = vagaService.buscarTodas();
        return ResponseEntity.ok().body(vagas);
    }

    @RequestMapping(value="/paginado", method=RequestMethod.GET)
    public ResponseEntity<Page<Vaga>> buscarPorPagina(
            @RequestParam(value="pagina", defaultValue="0") Integer pagina,
            @RequestParam(value="linhasPorPagina", defaultValue="24") Integer linhasPorPagina,
            @RequestParam(value="ordem", defaultValue="nome") String ordem,
            @RequestParam(value="direcao", defaultValue="ASC") String direcao) {
        Page<Vaga> vagas = vagaService.buscarPaginado(pagina, linhasPorPagina, ordem, direcao);
        return ResponseEntity.ok().body(vagas);
    }
	
	

}
