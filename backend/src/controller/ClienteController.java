
package controller;

// importando os pacotes do back

import dto.ClienteDTO;
import model.Cliente;
import service.ClienteService;

// importando spring boot

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody ClienteDTO dto) {

        Cliente cliente = new Cliente(
                dto.getCpf(),
                dto.getEmail(),
                dto.getNome()
        );

        clienteService.adicionarCliente(cliente);

        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }
}
