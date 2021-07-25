package br.edu.ifrs.restinga.grupo_1.mural_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.NumberFormat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Candidato extends Usuario {

    @Transient
    @JsonProperty("tipo")
    private final String tipo = "candidato";

    @NotEmpty(message="O campo CPF não pode estar vazio!!!")
    private String cpf;

    private String imagem;

    @ManyToOne
    @JsonIgnore
    private Portfolio portfolio;    
    
    @ManyToOne
    @NotEmpty(message="O campo endereço não pode ser vazio!!!")
    private Endereco endereco;

    @ManyToMany
    @JsonIgnore
    private List<Vaga> vagas = new ArrayList<>();

    @ElementCollection
    @CollectionTable
    @NotEmpty(message="O campo telefone não pode ser vazio!!!")
    private Set<String> telefones = new HashSet<>();
}
