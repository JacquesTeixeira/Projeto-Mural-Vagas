package br.edu.ifrs.restinga.grupo_1.mural_api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Candidato extends Usuario{

    @Transient
    @JsonProperty("tipo")
    private final String tipo = "candidato";
    private String cpf;
   // private Set<String> telefones = new HashSet<>();
   // private Portfolio portfolio;
   // private Endereco endereco;
    //private List<Vaga> vagas = new ArrayList<>();
}
