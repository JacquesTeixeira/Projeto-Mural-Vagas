package br.edu.ifrs.restinga.grupo_1.mural_api.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String logradouro;
    private String cep;
    private String complemento;
    private String cidade;
    private String estado;
    private Integer numero;

}
