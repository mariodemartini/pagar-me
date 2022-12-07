package br.com.geradordedevs.pagarme.controllers;

import br.com.geradordedevs.pagarme.entities.TransacoesEntity;
import br.com.geradordedevs.pagarme.services.TransacoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacoesController {
    @Autowired
    private TransacoesService transacoesService;

    @GetMapping
    public List<TransacoesEntity> listarTransacoes(){
        return transacoesService.listaTransacoes();
    }

}
