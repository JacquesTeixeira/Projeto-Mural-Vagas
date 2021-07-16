package br.edu.ifrs.restinga.grupo_1.mural_api.services;

import br.edu.ifrs.restinga.grupo_1.mural_api.models.Candidato;
import br.edu.ifrs.restinga.grupo_1.mural_api.models.Vaga;
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

    public void notificarNovaVaga(Candidato c, Vaga v) {
        SimpleMailMessage sm = this.prepareNotificarNovaVaga(c, v);
        enviarEmail(sm);
    }

    protected SimpleMailMessage prepareNotificarNovaVaga(Candidato candidato, Vaga vaga) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(candidato.getEmail());
        sm.setSubject("Há uma nova vaga que pode lhe interessar!");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Olá, " + candidato.getNome() + ", a vaga condiz com sua área de interesse, veja informações abaixo:");
        sm.setText(vaga.toString());//TODO Formatar mensagem para notificação
        return sm;
    }

    public void notificarAtualizacaoVaga(Candidato c, Vaga v) {
        SimpleMailMessage sm = this.prepareNotificarNovaVaga(c, v);
        enviarEmail(sm);
    }

    protected SimpleMailMessage prepareNotificarAtualizacaoVaga(Candidato candidato, Vaga vaga) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(candidato.getEmail());
        sm.setSubject("Atualização de vaga!");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Olá, " + candidato.getNome() + ", a vaga " + vaga.getTitulo() + " acabou de ser atualizada!");
        sm.setText(vaga.toString());//TODO Formatar mensagem para notificação
        return sm;
    }

}
