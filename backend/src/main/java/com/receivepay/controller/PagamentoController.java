package com.receivepay.controller;

import com.receivepay.dto.PagamentoDTO;
import com.receivepay.dto.PagamentoResponseDTO;
import com.receivepay.service.PagamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagamentos")
@CrossOrigin(origins = "*") // Libera o acesso para qualquer porta do front-end (como 5173, 5174, etc.)
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping
    public ResponseEntity<PagamentoResponseDTO> criarPagamento(@RequestBody PagamentoDTO dto) {
        PagamentoResponseDTO resposta = pagamentoService.adicionarPagamento(
                dto.getCpfCliente(),
                dto.getValor(),
                dto.getPagamento(),
                dto.getDescricao()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }
}