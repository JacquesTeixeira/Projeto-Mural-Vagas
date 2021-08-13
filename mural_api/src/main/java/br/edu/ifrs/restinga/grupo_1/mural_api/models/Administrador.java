package br.edu.ifrs.restinga.grupo_1.mural_api.models;

import br.edu.ifrs.restinga.grupo_1.mural_api.models.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Data
@Entity
public class Administrador extends Usuario {

    @Transient
    @JsonProperty("tipo")
    private final String tipo = "administrador";

    public Administrador(){
        addPerfil(Perfil.ADMIN);
    }
}
