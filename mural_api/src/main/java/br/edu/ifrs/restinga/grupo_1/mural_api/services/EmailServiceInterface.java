package br.edu.ifrs.restinga.grupo_1.mural_api.services;

import br.edu.ifrs.restinga.grupo_1.mural_api.models.Candidato;
import br.edu.ifrs.restinga.grupo_1.mural_api.models.Vaga;
import org.springframework.mail.SimpleMailMessage;

public interface EmailServiceInterface {

    void enviarEmail(SimpleMailMessage msg);
    void notificacaConfirmarCadastro(Candidato candidato);
    void notificacaoVagaAtualizada(Vaga vaga);
    void notificacaoVagaNova(Candidato candidato, Vaga vaga);
}
