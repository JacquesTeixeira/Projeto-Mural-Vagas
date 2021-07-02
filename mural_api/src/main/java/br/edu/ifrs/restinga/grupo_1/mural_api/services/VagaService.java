package br.edu.ifrs.restinga.grupo_1.mural_api.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.edu.ifrs.restinga.grupo_1.mural_api.models.Vaga;
import br.edu.ifrs.restinga.grupo_1.mural_api.repositories.VagaRepository;

import java.util.List;

@Service
public class VagaService {
	
	@Autowired
	private VagaRepository vagaRepository;
	
	public List<Vaga> buscarTodas() {
		return vagaRepository.findAll();
	}
	
	public Page<Vaga> buscarPaginado(Integer pagina, Integer linhasPorPagina, String ordem, String direcao) {
        PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina, Direction.valueOf(direcao), ordem);
        return vagaRepository.findAll(pageRequest);
    }
}
