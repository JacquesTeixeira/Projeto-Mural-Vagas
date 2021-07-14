package br.edu.ifrs.restinga.grupo_1.mural_api.services;


import br.edu.ifrs.restinga.grupo_1.mural_api.models.AreaDaVaga;
import br.edu.ifrs.restinga.grupo_1.mural_api.models.Vaga;
import br.edu.ifrs.restinga.grupo_1.mural_api.repositories.AreaDaVagaRepository;
import br.edu.ifrs.restinga.grupo_1.mural_api.services.exceptions.DataIntegrityException;
import br.edu.ifrs.restinga.grupo_1.mural_api.services.exceptions.ObjectNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AreaVagaService {

    @Autowired
    private AreaDaVagaRepository areaDaVagaRepository;

    public List<AreaDaVaga> buscarTodas() {
        List<AreaDaVaga> areas = this.areaDaVagaRepository.findAll();
        if(areas.isEmpty()) {
            throw new ObjectNotFound("Base de dados vazia!");
        }
        return areas;
    }

    public AreaDaVaga buscarPorId(Long id) {
        try {
            return this.areaDaVagaRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Não existe área de vaga com o id informado"));
        } catch (NoSuchElementException e) {
            throw new ObjectNotFound("Não existe área de vaga com o id informado");
        }
    }

    public AreaDaVaga cadastrarAreaDaVaga(AreaDaVaga areaDaVaga) {
        try {
            return this.areaDaVagaRepository.save(areaDaVaga);
        } catch (Exception e) {
            throw new DataIntegrityException("Não foi possível cadastar a área da vaga, verifique os dados informados!");
        }
    }

    public AreaDaVaga editarAreaDaVaga(AreaDaVaga areaDaVaga, Long id) {
        AreaDaVaga areaDb = this.buscarPorId(id);
        try {
            return this.areaDaVagaRepository.save(areaDb);
        } catch (Exception e) {
            throw new DataIntegrityException("Não foi possível editar a área da vaga, verifique os dados informados!");
        }
    }

    public void excluirAreaDaVaga(Long id) {
        this.buscarPorId(id);
        try {
            this.areaDaVagaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir a área!");
        }
    }
}
