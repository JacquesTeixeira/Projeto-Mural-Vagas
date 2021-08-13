package br.edu.ifrs.restinga.grupo_1.mural_api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;

@RestController
@RequestMapping("/")
public class StatusController {

    @GetMapping
    public String status() throws UnknownHostException {
        return "V1 - API MUral de Vagas rodando " + Instant.now() + " Servidor [" + InetAddress.getLocalHost() + "]";
    }
}
