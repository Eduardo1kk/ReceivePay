package repository;

import model.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClienteRepository {

    //Temporário

    //Cria uma lista de clientes usando a class do model (Objeto) cliente
    private List<Cliente> clientes = new ArrayList<>();


    //método para add cliente
    public  void  adiciona(Cliente p){
        Objects.requireNonNull(p,"O Cliente  não pode ser nulo !");
        clientes.add(p);
    }


    public List<Cliente> buscaPorId(String resultado){

        List<Cliente> clienteList = new ArrayList<>();

        for (Cliente p : clienteList){
            if (p.getId().toString().contains(resultado)){
                clienteList.add(p);
            }

    }
        return clienteList;

    }

    public List<Cliente> buscaPorCPF(String resposta){

        List<Cliente> clienteList = new ArrayList<>();

        for (Cliente p : clienteList){
            if (p.getCpf().contains(resposta)){
                clienteList.add(p);
            }
        }
        return clienteList;
    }


    public List<Cliente> getClientes() {
        return clientes;
    }
}
