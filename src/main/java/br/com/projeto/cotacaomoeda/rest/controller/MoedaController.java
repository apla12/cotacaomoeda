package br.com.projeto.cotacaomoeda.rest.controller;


import br.com.projeto.cotacaomoeda.entity.Moeda;
import br.com.projeto.cotacaomoeda.service.MoedaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/moedas")
public class MoedaController {

    @Autowired
    private MoedaService service;

    @GetMapping
    public Moeda obtercontacao(@RequestParam String data) throws IOException {
        return service.obterContacao(data);
    }




}
