package br.edu.ifrs.restinga.grupo_1.mural_api.models.dtos;
import java.io.Serializable;

public class CredencialDTO {
    private static final long serialVersionUID = 1L;

    private String nome;
    private String email;
    private String senha;

    public CredencialDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
