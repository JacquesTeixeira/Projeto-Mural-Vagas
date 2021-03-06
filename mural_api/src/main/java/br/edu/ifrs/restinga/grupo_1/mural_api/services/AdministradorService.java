package br.edu.ifrs.restinga.grupo_1.mural_api.services;

import br.edu.ifrs.restinga.grupo_1.mural_api.models.Administrador;
import br.edu.ifrs.restinga.grupo_1.mural_api.models.enums.Perfil;
import br.edu.ifrs.restinga.grupo_1.mural_api.repositories.AdministradorRepository;
import br.edu.ifrs.restinga.grupo_1.mural_api.security.UserSpringSecurity;
import br.edu.ifrs.restinga.grupo_1.mural_api.services.exceptions.AuthorizationException;
import br.edu.ifrs.restinga.grupo_1.mural_api.services.exceptions.DataIntegrityException;
import br.edu.ifrs.restinga.grupo_1.mural_api.services.exceptions.ObjectNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;


    @Autowired
    private BCryptPasswordEncoder pe;

    public List<Administrador> buscarTodos() {
        List<Administrador> administradores = this.administradorRepository.findAll();
        if (administradores.isEmpty()) {
            throw new ObjectNotFound("Base de dados vazia!");
        }
        return administradores;
    }

    public Page<Administrador> buscarPaginado(Integer pagina, Integer linhasPorPagina, String ordem, String direcao) {
        PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina, Direction.valueOf(direcao), ordem);
        Page<Administrador> administradores = this.administradorRepository.findAll(pageRequest);
        if (administradores.isEmpty()) {
            throw new ObjectNotFound("Base de dados vazia!");
        }
        return administradores;
    }

    public Administrador buscarPorId(Long id) {
        UserSpringSecurity user = UsuarioService.autenticado();
        if (user == null || id != user.getId()) {
            throw new AuthorizationException("Acesso negado");
        }
           try {
            return this.administradorRepository.findById(id).orElseThrow(() -> new ObjectNotFound("N??o existe administrador com id informado"));
        } catch (NoSuchElementException e) {
            throw new ObjectNotFound("N??o existe administrador com id informado");
        }
    }

    public Administrador cadastrar(Administrador administrador) {
        try {
            administrador.setSenha(pe.encode(administrador.getSenha()));
            return this.administradorRepository.save(administrador);
        } catch (Exception e) {
            throw new DataIntegrityException("N??o foi poss??vel cadastrar, verifique os dados informados!");
        }
    }

    public Administrador editar(Administrador administrador, Long id) {
        try {
            Administrador administradorDb = this.buscarPorId(id);
            administradorDb.setNome(administrador.getNome());
            administradorDb.setSenha(pe.encode(administrador.getSenha()));
            administradorDb.setEmail(administrador.getEmail());
            return this.administradorRepository.save(administradorDb);
        } catch (Exception e) {
            throw new DataIntegrityException("N??o foi poss??vel atualizar, verifique os dados informados!");
        }
    }

    public void excluir(Long id) {
        this.buscarPorId(id);
        try {
            this.administradorRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("N??o ?? poss??vel excluir uma categoria que possui produtos");
        }
    }
}
