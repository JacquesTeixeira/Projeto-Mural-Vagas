package br.edu.ifrs.restinga.grupo_1.mural_api.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@Entity
public class Portfolio {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String escolaridade;
//    private Set<String> habilidades = new HashSet<>();
//    private Set<String> conhecimentos = new HashSet<>();
    //private Set<String> areasDeInteresse = new HashSet<>();
}

