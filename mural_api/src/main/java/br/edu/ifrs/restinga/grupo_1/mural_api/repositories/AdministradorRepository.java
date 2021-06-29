package br.edu.ifrs.restinga.grupo_1.mural_api.repositories;


import br.edu.ifrs.restinga.grupo_1.mural_api.models.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

}
