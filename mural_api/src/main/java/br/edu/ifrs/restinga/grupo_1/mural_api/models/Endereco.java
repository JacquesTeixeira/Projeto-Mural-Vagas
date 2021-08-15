package br.edu.ifrs.restinga.grupo_1.mural_api.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="O campo logradouro não pode ser vazio!!!")
    private String logradouro;
    
    @NotEmpty(message="O campo CEP não pode ser vazio!!!")
    private String cep;
    
    @NotEmpty(message="O campo complemento não pode ser vazio!!!")
    private String complemento;
    
    @NotEmpty(message="O campo cidade não pode ser vazio!!!")
    private String cidade;
    
    @NotEmpty(message="O campo estado não pode ser vazio!!!")
    private String estado;
    
    private Integer numero;
}
