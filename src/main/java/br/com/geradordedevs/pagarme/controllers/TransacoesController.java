package br.com.geradordedevs.pagarme.controllers;

import br.com.geradordedevs.pagarme.dtos.requests.TransacoesRequestDTO;
import br.com.geradordedevs.pagarme.dtos.responses.SaldoResponseDTO;
import br.com.geradordedevs.pagarme.dtos.responses.TransacoesResponseDTO;
import br.com.geradordedevs.pagarme.facades.TransacoesFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacoesController {
    @Autowired
    private TransacoesFacade transacoesFacade;

    @GetMapping
    public List<TransacoesResponseDTO> listarTransacoes(){
        return transacoesFacade.listaTransacoes();
    }

    @PostMapping
    public TransacoesResponseDTO cadastrarTransacao(@RequestBody TransacoesRequestDTO transacoesRequestDTO){
        return transacoesFacade.cadastrarTransacao(transacoesRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deletarTransacao(@PathVariable Long id){
        transacoesFacade.deletarTransacao(id);
    }

    @GetMapping("/saldo")
    public SaldoResponseDTO consultarSaldo(TransacoesRequestDTO requestDTO){
        SaldoResponseDTO saldoResponseDTO = transacoesFacade.consultarSaldo(requestDTO.getValorTransacao());
        return saldoResponseDTO;
    }

}
