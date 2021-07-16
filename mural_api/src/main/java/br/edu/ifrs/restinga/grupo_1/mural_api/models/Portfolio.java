package br.edu.ifrs.restinga.grupo_1.mural_api.models;

import lombok.Data;

import javax.persistence.*;
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
    private String escolaridade;

    @ElementCollection
    @CollectionTable
    private Set<String> habilidades = new HashSet<>();

    @ElementCollection
    @CollectionTable
    private Set<String> conhecimentos = new HashSet<>();

    @ManyToMany
    private List<AreaDaVaga> areasDeInteresse = new ArrayList<>();
}

