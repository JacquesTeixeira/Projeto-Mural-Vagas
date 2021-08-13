package br.edu.ifrs.restinga.grupo_1.mural_api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private String titulo;
    private String descricao;
    private String empresa;
    private String enderecoEmpresa;
    private Double salario;
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
