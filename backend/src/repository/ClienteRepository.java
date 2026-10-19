package repository;

import model.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClienteRepository {

    //Temporário

    //Cria uma lista de clientes usando a class do model (Objeto) cliente
    private  List<Cliente> clientes = new ArrayList<>();


    //método para add cliente
    public void  adiciona(Cliente p){
        Objects.requireNonNull(p,"O Cliente  não pode ser nulo !");

        for (Cliente t : clientes){
            if (t.getId().equals(p.getId()) || t.getCpf().equals(p.getCpf())){
                throw new ObjetosParecidosExepition("Clientes com o mesmo id ou cpf!");
            }
        }

        clientes.add(p);
    }


    public List<Cliente> buscaPorId(String resultado){

        List<Cliente> clienteList = new ArrayList<>();

        if (resultado == null) {
            return clienteList;
        }
        for (Cliente p : clientes){
            if (p.getId().toString().equals(resultado)){
                clienteList.add(p);
            }

    }
        return clienteList;

    }

    public List<Cliente> buscaPorCPF(String resposta){
//crio uma lista para armazenar quantos cpf iguais tem
        List<Cliente> clienteList = new ArrayList<>();

        if (resposta == null) {
            return clienteList;
        }
        for (Cliente p : clientes){
            if (p.getCpf().equals(resposta)){
                clienteList.add(p);
            }
        }
        return clienteList;
    }
}
