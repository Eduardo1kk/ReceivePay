
package service;

import model.Cliente;
import repository.ClienteRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Validação de cliente para poder adicionar
    public void adicionarCliente(Cliente c) {

        Objects.requireNonNull(c, "O cliente não pode ser null");

        List<Cliente> clientesEncontrados =
                clienteRepository.buscaPorCPF(c.getCpf());

        // Verifica se a lista está vazia
        if (!clientesEncontrados.isEmpty()) {
            throw new IllegalArgumentException(
                    "Já existe um cliente cadastrado com este CPF!"
            );
        }

        clienteRepository.adiciona(c);
    }
}
