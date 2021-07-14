package br.edu.ifrs.restinga.grupo_1.mural_api.services;

import br.edu.ifrs.restinga.grupo_1.mural_api.models.Candidato;
import br.edu.ifrs.restinga.grupo_1.mural_api.models.Portfolio;
import br.edu.ifrs.restinga.grupo_1.mural_api.models.Vaga;
import br.edu.ifrs.restinga.grupo_1.mural_api.repositories.CandidatoRepository;
import br.edu.ifrs.restinga.grupo_1.mural_api.repositories.EnderecoRepository;
import br.edu.ifrs.restinga.grupo_1.mural_api.repositories.PortfolioRepository;
import br.edu.ifrs.restinga.grupo_1.mural_api.repositories.VagaRepository;
import br.edu.ifrs.restinga.grupo_1.mural_api.services.exceptions.DataIntegrityException;
import br.edu.ifrs.restinga.grupo_1.mural_api.services.exceptions.InvalidRequest;
import br.edu.ifrs.restinga.grupo_1.mural_api.services.exceptions.ObjectNotFound;
import br.edu.ifrs.restinga.grupo_1.mural_api.utils.ImagemUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CandidatoService {

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private EmailService emailService;

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

    @Transactional
    public Candidato cadastrar(Candidato candidato) {
        try {
            candidato = this.candidatoRepository.save(candidato);
            this.enderecoRepository.save(candidato.getEndereco());
            //this.emailService.confirmacaoCadastro(candidato);
        } catch (Exception e) {
            throw new DataIntegrityException("Não foi possível cadastrar, verifique os dados informados!");
        }
        return candidato;
    }

    @Transactional
    public Candidato editar(Candidato candidato, Long id) {
        try {
            Candidato candidatoDb = this.buscarPorId(id);
            candidatoDb.setNome(candidato.getNome());
            candidatoDb.setSenha(candidato.getSenha());
            candidatoDb.setEmail(candidato.getEmail());
            candidatoDb.setCpf(candidato.getCpf());
            candidatoDb.setTelefones(candidato.getTelefones());
            candidatoDb.setEndereco(candidato.getEndereco());
            this.candidatoRepository.save(candidatoDb);
            this.enderecoRepository.save(candidatoDb.getEndereco());
            return candidatoDb;
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

    public Portfolio buscarPortfolioPorId(Long id) {
        try {
            return this.portfolioRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Não existe portfólio com id informado"));
        } catch (NoSuchElementException e) {
            throw new ObjectNotFound("Não existe portfólio com id informado");
        }
    }

    @Transactional
    public Portfolio cadastrarPortfolio(Portfolio portfolio, Long candidatoId) {
        Candidato candidatoDb = this.buscarPorId(candidatoId);
        try {
            Portfolio portfolioSalvo = this.portfolioRepository.save(portfolio);
            candidatoDb.setPortfolio(portfolioSalvo);
            this.candidatoRepository.save(candidatoDb);
        } catch (Exception e) {
            throw new DataIntegrityException("Não foi possível cadastar portfólio, verifique os dados informados!");
        }
        return portfolio;
    }

    @Transactional
    public Portfolio editarPortfolio(Portfolio portfolio, Long id, Long candidatoId) {
        Candidato candidatoDb = this.buscarPorId(candidatoId);
        Portfolio portfolioDb = this.buscarPortfolioPorId(id);
        try {
            portfolioDb.setConhecimentos(portfolio.getConhecimentos());
            portfolioDb.setEscolaridade(portfolio.getEscolaridade());
            portfolioDb.setHabilidades(portfolio.getHabilidades());
            portfolioDb.setAreasDeInteresse(portfolio.getAreasDeInteresse());
            Portfolio portfolioSalvo = this.portfolioRepository.save(portfolioDb);
            candidatoDb.setPortfolio(portfolioSalvo);
            this.candidatoRepository.save(candidatoDb);
            return portfolioSalvo;
        } catch (Exception e) {
            throw new DataIntegrityException("Não foi possível cadastar portfólio, verifique os dados informados!");
        }
    }

    public Portfolio buscarPortfolioCandidato(Long candidatoId) {
        Candidato candidato = this.buscarPorId(candidatoId);
        return candidato.getPortfolio();
    }

    public String uploadImagemCandidato(MultipartFile multipartFile, Long candidatoId) throws IOException {
        Candidato candidatoDb = buscarPorId(candidatoId);
        String nomeArquivo = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        if (!nomeArquivo.contains(".jpg")) {
            throw new InvalidRequest("Somente são aceitos arquivos com extensão .jpg!");
        }
        candidatoDb.setImagem(nomeArquivo);
        this.candidatoRepository.save(candidatoDb);
        String diretorioUpload = "storage/";
        ImagemUploadUtil.salvarImagem(diretorioUpload, nomeArquivo, multipartFile);
        return candidatoDb.getImagem();
    }

    public Candidato favoritar(Long candidatoId, Long vagaId) {
        Candidato candidatoDb = this.buscarPorId(candidatoId);
        Vaga vagaDb = this.vagaRepository.findById(vagaId).orElseThrow(
                () -> new ObjectNotFound("Não existe vaga com id informado"));
        candidatoDb.getVagas().add(vagaDb);
        this.candidatoRepository.save(candidatoDb);
        return candidatoDb;
    }

    public Candidato removerFavorito(Long candidatoId, Long vagaId) {
        Candidato candidatoDb = this.buscarPorId(candidatoId);
        Vaga vagaDb = this.vagaRepository.findById(vagaId).orElseThrow(
                () -> new ObjectNotFound("Não existe vaga com id informado"));
        candidatoDb.getVagas().remove(vagaDb);
        this.candidatoRepository.save(candidatoDb);
        return candidatoDb;
    }
}
