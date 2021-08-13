package br.edu.ifrs.restinga.grupo_1.mural_api.models;

import br.edu.ifrs.restinga.grupo_1.mural_api.models.enums.Perfil;
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

    private String imagem;

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

    public Candidato(){
        addPerfil(Perfil.CANDIDATO);
    }
}
