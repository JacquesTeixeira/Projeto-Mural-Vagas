package br.edu.ifrs.restinga.grupo_1.mural_api.services;

import br.edu.ifrs.restinga.grupo_1.mural_api.models.Administrador;
import br.edu.ifrs.restinga.grupo_1.mural_api.models.Candidato;
import br.edu.ifrs.restinga.grupo_1.mural_api.repositories.CandidatoRepository;
import br.edu.ifrs.restinga.grupo_1.mural_api.services.exceptions.DataIntegrityException;
import br.edu.ifrs.restinga.grupo_1.mural_api.services.exceptions.ObjectNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CandidatoService {

    @Autowired
    private CandidatoRepository candidatoRepository;

    public List<Candidato> buscarTodos() {
        List<Candidato> candidatos = this.candidatoRepository.findAll();
        if (candidatos.isEmpty()) {
            throw new ObjectNotFound("Base de dados vazia!");
        }
        return candidatos;
    }

    public Page<Candidato> buscarPaginado(Integer pagina, Integer linhasPorPagina, String ordem, String direcao) {
        PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina, Direction.valueOf(direcao), ordem);
        Page<Candidato> candidatos = this.candidatoRepository.findAll(pageRequest);
        if (candidatos.isEmpty()) {
            throw new ObjectNotFound("Base de dados vazia!");
        }
        return candidatos;
    }

    public Candidato buscarPorId(Long id) {
        try {
            return this.candidatoRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Não existe candidato com id informado"));
        } catch (NoSuchElementException e) {
            throw new ObjectNotFound("Não existe candidato com id informado");
        }
    }

    public Candidato cadastrar(Candidato candidato) {
        try {
            return this.candidatoRepository.save(candidato);
        } catch (Exception e) {
            throw new DataIntegrityException("Não foi possível cadastrar, verifique os dados informados!");
        }
    }

    public Candidato editar(Candidato candidato, Long id) {
        try {
            Candidato candidatoDb = this.buscarPorId(id);
            candidatoDb.setNome(candidato.getNome());
            candidatoDb.setSenha(candidato.getSenha());
            candidatoDb.setEmail(candidato.getEmail());
            candidatoDb.setCpf(candidato.getCpf());
            candidatoDb.setTelefones(candidato.getTelefones());
            return this.candidatoRepository.save(candidatoDb);
        } catch (Exception e) {
            throw new DataIntegrityException("Não foi possível atualizar, verifique os dados informados!");
        }
    }

    public void excluir(Long id) {
        this.buscarPorId(id);
        try {
            this.candidatoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir candidato");
        }
    }
}
