package br.edu.ifrs.restinga.grupo_1.mural_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/index")
public class IndexController {

    @RequestMapping(method= RequestMethod.GET)
    public String index(){
        return "API Rodando!";
    }
}
