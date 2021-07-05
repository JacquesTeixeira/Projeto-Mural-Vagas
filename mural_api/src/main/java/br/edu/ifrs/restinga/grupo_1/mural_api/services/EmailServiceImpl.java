package br.edu.ifrs.restinga.grupo_1.mural_api.services;

import br.edu.ifrs.restinga.grupo_1.mural_api.models.Candidato;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class EmailServiceImpl implements EmailServiceInterface {

    public void confirmacaoCadastro(Candidato obj) {
        SimpleMailMessage sm = this.prepareConfirmacaoCadastro(obj);
        enviarEmail(sm);
    }

    protected SimpleMailMessage prepareConfirmacaoCadastro(Candidato obj) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(obj.getEmail());
        sm.setSubject("Seu cadastro foi realizado com sucesso!");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Olá, " + obj.getNome() + " cadastre agora seu portfólio " +
                "para receber informações sobre as vagas que melhor se enquadram com seu perfil!");
        return sm;
    }
}
