package br.edu.ifrs.restinga.grupo_1.mural_api.repositories;

import br.edu.ifrs.restinga.grupo_1.mural_api.models.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
}
