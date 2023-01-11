package br.com.geradordedevs.pagarme.controllers;

import br.com.geradordedevs.pagarme.dtos.requests.TransacoesRequestDTO;
import br.com.geradordedevs.pagarme.dtos.responses.SaldoResponseDTO;
import br.com.geradordedevs.pagarme.dtos.responses.TransacoesResponseDTO;
import br.com.geradordedevs.pagarme.facades.TransacoesFacade;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacoesController {
    @Autowired
    private TransacoesFacade transacoesFacade;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "find all successfuly"),
            @ApiResponse(code = 400, message = "bad request")
    })
    @GetMapping
    public ResponseEntity<List<TransacoesResponseDTO>> listarTransacoes(){
        return new ResponseEntity<>(transacoesFacade.listaTransacoes(), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "save successfuly"),
            @ApiResponse(code = 400, message = "bad request"),
    })
    @PostMapping
    public ResponseEntity<TransacoesResponseDTO> cadastrarTransacao(@Valid @RequestBody TransacoesRequestDTO transacoesRequestDTO){
        return new ResponseEntity<>(transacoesFacade.cadastrarTransacao(transacoesRequestDTO), HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "delete successfuly"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 404, message = "id not found")
    })
    @DeleteMapping("/{id}")
    public void deletarTransacao(@PathVariable Long id){
        transacoesFacade.deletarTransacao(id);
    }


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "find balance successfuly"),
            @ApiResponse(code = 400, message = "bad request")
    })
    @GetMapping("/saldo")
    public ResponseEntity<SaldoResponseDTO> consultarSaldo(TransacoesRequestDTO requestDTO){
        SaldoResponseDTO saldoResponseDTO = transacoesFacade.consultarSaldo(requestDTO.getValorTransacao());
        return new ResponseEntity<>(saldoResponseDTO, HttpStatus.OK);
    }

}
