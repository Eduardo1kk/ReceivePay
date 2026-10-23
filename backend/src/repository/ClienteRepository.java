
package repository;

import model.Cliente;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ClienteRepository {

    //Temporário

    //Cria uma lista de clientes usando a class do model (Objeto) cliente
    private List<Cliente> clientes = new ArrayList<>();

    //método para add cliente
    public void adiciona(Cliente p) {
        Objects.requireNonNull(p, "O Cliente não pode ser nulo!");

        for (Cliente t : clientes) {
            if (t.getId().equals(p.getId()) || t.getCpf().equals(p.getCpf())) {
                throw new IllegalArgumentException("Clientes com o mesmo ID ou CPF!");
            }
        }

        clientes.add(p);
    }

    public List<Cliente> buscaPorId(String resultado) {

        List<Cliente> clienteList = new ArrayList<>();

        if (resultado == null) {
            return clienteList;
        }

        for (Cliente p : clientes) {
            if (p.getId().toString().equals(resultado)) {
                clienteList.add(p);
            }
        }

        return clienteList;
    }

    public List<Cliente> buscaPorCPF(String resposta) {

        List<Cliente> clienteList = new ArrayList<>();

        if (resposta == null) {
            return clienteList;
        }

        for (Cliente p : clientes) {
            if (p.getCpf().equals(resposta)) {
                clienteList.add(p);
            }
        }

        return clienteList;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
}
