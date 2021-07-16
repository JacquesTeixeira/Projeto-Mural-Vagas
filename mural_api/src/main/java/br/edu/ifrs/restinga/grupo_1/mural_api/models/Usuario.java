package br.edu.ifrs.restinga.grupo_1.mural_api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "tipo")
@JsonTypeName("usuario")
@JsonSubTypes({
        @JsonSubTypes.Type(name = "administrador", value = Administrador.class),
        @JsonSubTypes.Type(name = "candidato", value = Candidato.class)})
public abstract class Usuario {

    @Transient
    @JsonProperty("tipo")
    private final String tipo = "usuario";

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String nome;
    private String email;
    private String senha;
}
