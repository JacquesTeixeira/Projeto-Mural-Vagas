package br.edu.ifrs.restinga.grupo_1.mural_api.models;

import br.edu.ifrs.restinga.grupo_1.mural_api.models.enums.Perfil;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

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
    
    @NotEmpty(message = "O campo nome não pode ser vazio!!!")    
    private String nome;
    
    @NotEmpty(message = "O campo e-mail não pode ser vazio!!!")
    @Email(message = "Digite um e-mail válido!!!")
    private String email;
    
    @NotEmpty(message = "O campo senha não pode ser vazio!!!")
    private String senha;

    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable
    private Set<Integer> perfis = new HashSet<>();


    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        perfis.add(perfil.getCod());
    }
}
