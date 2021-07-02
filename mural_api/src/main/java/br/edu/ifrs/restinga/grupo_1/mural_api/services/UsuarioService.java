package br.edu.ifrs.restinga.grupo_1.mural_api.services;

import br.edu.ifrs.restinga.grupo_1.mural_api.models.Usuario;
import br.edu.ifrs.restinga.grupo_1.mural_api.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> buscarTodos(){
        return usuarioRepository.findAll();
    }

    public Page<Usuario> buscarPaginado(Integer pagina, Integer linhasPorPagina, String ordem, String direcao) {
        PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina, Direction.valueOf(direcao), ordem);
        return this.usuarioRepository.findAll(pageRequest);
    }

    public Usuario show(Long id) {
        Optional<Usuario> usuario = Optional.of(this.usuarioRepository.findById(id).get());
        return usuario.orElseThrow();
    }
}
