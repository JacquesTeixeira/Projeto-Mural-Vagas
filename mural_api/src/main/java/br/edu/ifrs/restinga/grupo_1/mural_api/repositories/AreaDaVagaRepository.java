package br.edu.ifrs.restinga.grupo_1.mural_api.repositories;

import br.edu.ifrs.restinga.grupo_1.mural_api.models.AreaDaVaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaDaVagaRepository extends JpaRepository<AreaDaVaga, Long> {
    List<AreaDaVaga> findByAreaDaVagaLike(String area);
}