package br.edu.ifrs.restinga.grupo_1.mural_api.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="O campo escolaridade não pode estar vazio!!!")
    private String escolaridade;

    @ElementCollection
    @CollectionTable
    @NotEmpty(message="O campo habilidade não pode estar vazio!!!")
    private Set<String> habilidades = new HashSet<>();

    @ElementCollection
    @CollectionTable
    @NotEmpty(message="O campo conhecimento não pode estar vazio!!!")
    private Set<String> conhecimentos = new HashSet<>();

    @ManyToMany
    @NotEmpty(message="O campo área de interesse não pode estar vazio!!!")
    private List<AreaDaVaga> areasDeInteresse = new ArrayList<>();
}

