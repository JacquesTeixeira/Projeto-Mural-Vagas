package br.edu.ifrs.restinga.grupo_1.mural_api.repositories;

import br.edu.ifrs.restinga.grupo_1.mural_api.models.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {
 }
