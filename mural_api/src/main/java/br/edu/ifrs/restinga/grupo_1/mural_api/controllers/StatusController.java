package br.edu.ifrs.restinga.grupo_1.mural_api.controllers;

import br.edu.ifrs.restinga.grupo_1.mural_api.models.Candidato;
import br.edu.ifrs.restinga.grupo_1.mural_api.services.CandidatoService;
import br.edu.ifrs.restinga.grupo_1.mural_api.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;

@RestController
@RequestMapping("/")
public class StatusController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private CandidatoService candidatoService;

    @GetMapping
    public String status() throws UnknownHostException {
        return "V1 - API MUral de Vagas rodando " + Instant.now() + " Servidor [" + InetAddress.getLocalHost() + "]";
    }

    @RequestMapping(path = "/notificar", method = RequestMethod.GET)
    public ResponseEntity<Void> sendMail() {
        Candidato candidato = this.candidatoService.buscarPorId(3L);
        return ResponseEntity.ok().build();
    }
}
