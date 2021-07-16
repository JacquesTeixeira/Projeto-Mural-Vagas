package br.edu.ifrs.restinga.grupo_1.mural_api.services;

import br.edu.ifrs.restinga.grupo_1.mural_api.models.AreaDaVaga;
import br.edu.ifrs.restinga.grupo_1.mural_api.models.Candidato;
import br.edu.ifrs.restinga.grupo_1.mural_api.models.Vaga;
import br.edu.ifrs.restinga.grupo_1.mural_api.repositories.VagaRepository;
import br.edu.ifrs.restinga.grupo_1.mural_api.services.exceptions.DataIntegrityException;
import br.edu.ifrs.restinga.grupo_1.mural_api.services.exceptions.ObjectNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class VagaService {

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AreaVagaService areaVagaService;

    @Autowired
    private CandidatoService candidatoService;

    public List<Vaga> buscarTodas() {
        List<Vaga> vagas = this.vagaRepository.findAll();
        if (vagas.isEmpty()) {
            throw new ObjectNotFound("Base de dados vazia!");
        }
        return vagas;
    }

    public Vaga buscarPorId(Long id) {
        try {
            return this.vagaRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Não existe vaga com o id informado!"));
        } catch (NoSuchElementException e) {
            throw new ObjectNotFound("Não existe vaga com o id informado!");
        }
    }

    public Vaga cadastrar(Vaga vaga) {
        try {
            vaga = this.vagaRepository.save(vaga);
            this.notificaCandidatoNovaVaga(vaga);
        } catch (Exception e) {
            throw new DataIntegrityException("Não foi possível cadastrar, verifique os dados informados!");
        }
        return vaga;
    }

    @Transactional
    public Vaga editar(Vaga vaga, Long id) {
        try {
            Vaga vagaDb = this.buscarPorId(id);
            vagaDb.setTitulo(vaga.getTitulo());
            vagaDb.setDescricao(vaga.getDescricao());
            vagaDb.setAreasDaVaga(vaga.getAreasDaVaga());
            vagaDb.setEmpresa(vaga.getEmpresa());
            vagaDb.setRequisitos(vaga.getRequisitos());
            vagaDb.setDesejavel(vaga.getDesejavel());
            vagaDb.setDiferenciais(vaga.getDiferenciais());
            vagaDb.setSalario(vaga.getSalario());
            vagaDb.setBeneficios(vaga.getBeneficios());
            this.vagaRepository.save(vagaDb);
            this.notificarCandidatosAtualizacaoVaga(vagaDb);
            return vagaDb;
        } catch (Exception e) {
            throw new DataIntegrityException("Não foi possível atualizar, verifique os dados informados!");
        }
    }

    public void excluir(Long id) {
        this.buscarPorId(id);
        try {
            this.vagaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir a vaga!");
        }
    }

    public Page<Vaga> buscarPaginado(Integer pagina, Integer linhasPorPagina, String ordem, String direcao) {
        PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina, Direction.valueOf(direcao), ordem);
        Page<Vaga> vagas = this.vagaRepository.findAll(pageRequest);
        if (vagas.isEmpty()) {
            throw new ObjectNotFound("Base de dados vazia!");
        }
        return vagas;
    }

    private void notificaCandidatoNovaVaga(Vaga vaga) {
        for (Candidato c : this.getCandidatosParaNotificarNovaVaga(vaga)) {
            this.emailService.notificarNovaVaga(c, vaga);
        }
    }

    private List<Candidato> getCandidatosParaNotificarNovaVaga(Vaga vaga) {
        List<AreaDaVaga> areasDaVaga = new ArrayList<>();
        for (AreaDaVaga a : vaga.getAreasDaVaga()) {
            areasDaVaga.add(this.areaVagaService.buscarPorId(a.getId()));
        }
        return this.candidatoService.buscarCandidatosPorAreaDaVaga(areasDaVaga);
    }

    private void notificarCandidatosAtualizacaoVaga(Vaga vaga) {
        for (Candidato c : this.candidatoService.buscarCandidatosPorVaga(vaga)) {
            this.emailService.notificarAtualizacaoVaga(c, vaga);
        }
    }
}
