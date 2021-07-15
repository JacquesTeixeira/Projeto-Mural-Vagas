package br.edu.ifrs.restinga.grupo_1.mural_api.repositories;

import br.edu.ifrs.restinga.grupo_1.mural_api.models.AreaDaVaga;
import br.edu.ifrs.restinga.grupo_1.mural_api.models.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    List<Candidato> findByPortfolio_AreasDeInteresseIn(Collection<AreaDaVaga> areas);
}

