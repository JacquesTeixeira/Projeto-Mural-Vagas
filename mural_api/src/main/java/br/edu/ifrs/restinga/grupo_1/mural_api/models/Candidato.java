package br.edu.ifrs.restinga.grupo_1.mural_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
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

    private String cpf;

    @ManyToOne
    @JsonIgnore
    private Portfolio portfolio;

    @ManyToOne
    private Endereco endereco;

    @ManyToMany
    @JsonIgnore
    private List<Vaga> vagas = new ArrayList<>();

    @ElementCollection
    @CollectionTable
    private Set<String> telefones = new HashSet<>();
}
