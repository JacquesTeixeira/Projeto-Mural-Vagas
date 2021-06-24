package br.edu.ifrs.restinga.grupo_1.mural_api.utils;

import br.edu.ifrs.restinga.grupo_1.mural_api.models.Administrador;
import br.edu.ifrs.restinga.grupo_1.mural_api.repositories.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InsertData implements ApplicationRunner {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Administrador admin = new Administrador();
        admin.setNome("Admin");
        admin.setEmail("admin@email.mail");
        admin.setSenha("123");
        administradorRepository.save(admin);
    }
}
