package com.receivepay.controller;

// importando os pacotes do back

import com.receivepay.dto.ClienteDTO;
import com.receivepay.model.Cliente;
import com.receivepay.repository.ClienteRepository;
import com.receivepay.service.ClienteService;

// importando spring boot

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteService clienteService, ClienteRepository clienteRepository) {
        this.clienteService = clienteService;
        this.clienteRepository = clienteRepository;
    }

    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody ClienteDTO dto) {

        // Ajustado para passar a senha no construtor de 4 parâmetros
        Cliente cliente = new Cliente(
                dto.getCpf(),
                dto.getEmail(),
                dto.getNome(),
                dto.getSenha()
        );

        clienteService.adicionarCliente(cliente);

        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> fazerLogin(@RequestBody ClienteDTO dto) {
        try {
            // Busca o cliente pelo e-mail
            List<Cliente> clientes = clienteRepository.findByEmail(dto.getEmail());

            if (clientes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-mail não cadastrado!");
            }

            Cliente cliente = clientes.get(0);

            // Valida se a senha informada confere com a salva no banco
            if (!cliente.getSenha().equals(dto.getSenha())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta!");
            }

            return ResponseEntity.ok(cliente); // Login aprovado com sucesso!
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao realizar login: " + e.getMessage());
        }
    }
}