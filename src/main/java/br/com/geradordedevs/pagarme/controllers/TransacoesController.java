package br.com.geradordedevs.pagarme.controllers;

import br.com.geradordedevs.pagarme.dtos.requests.TransacoesRequestDTO;
import br.com.geradordedevs.pagarme.dtos.responses.TransacoesResponseDTO;
import br.com.geradordedevs.pagarme.entities.TransacoesEntity;
import br.com.geradordedevs.pagarme.repositories.TransacoesRepository;
import br.com.geradordedevs.pagarme.services.PagamentoService;
import br.com.geradordedevs.pagarme.services.TransacoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacoesController {
    @Autowired
    private TransacoesService transacoesService;

    @GetMapping
    public List<TransacoesResponseDTO> listarTransacoes(){
        return transacoesService.listaTransacoes();
    }

    @PostMapping
    public TransacoesResponseDTO cadastrarTransacao(@RequestBody TransacoesRequestDTO transacoesRequestDTO){
        return transacoesService.cadastrarTransacao(transacoesRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deletarTransacao(@PathVariable Long id){
        transacoesService.deletarTransacao(id);
    }

}
