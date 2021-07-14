package br.edu.ifrs.restinga.grupo_1.mural_api.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Vaga {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private String empresa;
    private String enderecoEmpresa;
    private String requisitos;
    private String desejavel;
    private String diferenciais;
    private String salario;
    private String beneficios;

    @ManyToMany
    private List<AreaDaVaga> areasDaVaga = new ArrayList<>();

    @ManyToOne
    private Administrador administrador;

}
