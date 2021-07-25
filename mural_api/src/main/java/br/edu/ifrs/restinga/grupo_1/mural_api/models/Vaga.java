package br.edu.ifrs.restinga.grupo_1.mural_api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="O campo título não pode ser vazio!!!")
    private String titulo;
    
    @NotEmpty(message="O campo descrição não pode ser vazio!!!")
    private String descricao;
    
    @NotEmpty(message="O campo empresa não pode ser vazio!!!")
    private String empresa;
    
    @NotEmpty(message="O campo endereço da empresa não pode ser vazio!!!")
    private String enderecoEmpresa;
    
    @NotEmpty(message="O campo salário não pode ser vazio!!!")
    private Double salario;
    
    @NotEmpty(message="O campo salário desejável não pode ser vazio!!!")
    private String desejavel;

    @ManyToOne
    private AreaDaVaga areaDaVaga;

    @ManyToOne
    private Administrador administrador;

    @ElementCollection
    @CollectionTable
    private Set<String> requisitos = new HashSet<>();

    @ElementCollection
    @CollectionTable
    private Set<String> diferenciais = new HashSet<>();

    @ElementCollection
    @CollectionTable
    private Set<String> beneficios = new HashSet<>();
}
