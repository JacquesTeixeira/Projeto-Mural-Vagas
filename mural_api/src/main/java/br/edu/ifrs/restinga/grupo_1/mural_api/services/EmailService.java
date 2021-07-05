package br.edu.ifrs.restinga.grupo_1.mural_api.services;

import br.edu.ifrs.restinga.grupo_1.mural_api.models.Candidato;
import br.edu.ifrs.restinga.grupo_1.mural_api.models.Vaga;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService extends EmailServiceImpl{

    @Autowired
    private MailSender mailSender;

    @Override
    public void enviarEmail(SimpleMailMessage msg) {
        log.info("Enviando email...");
        this.mailSender.send(msg);
        log.info("Email enviado");
    }

    @Override
    public void notificacaConfirmarCadastro(Candidato candidato) {

    }

    @Override
    public void notificacaoVagaAtualizada(Vaga vaga) {

    }

    @Override
    public void notificacaoVagaNova(Candidato candidato, Vaga vaga) {

    }
}
