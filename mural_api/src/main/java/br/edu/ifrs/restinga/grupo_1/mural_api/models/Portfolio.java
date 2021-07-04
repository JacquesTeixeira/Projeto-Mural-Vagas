package br.edu.ifrs.restinga.grupo_1.mural_api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
public class Portfolio {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String escolaridade;

    @ElementCollection
    @CollectionTable
    private Set<String> habilidades = new HashSet<>();

    @ElementCollection
    @CollectionTable
    private Set<String> conhecimentos = new HashSet<>();

    @ElementCollection
    @CollectionTable
    private Set<String> areasDeInteresse = new HashSet<>();
}

