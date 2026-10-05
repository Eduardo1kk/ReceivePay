package service;

import model.Cliente;
import repository.ClienteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClienteService {

    private Boolean isValidar(String cpf){

        if (cpf == null || cpf.isBlank()){
            return false;
        }

        //Ele remove tudo que é '.'
        cpf = cpf.replace(".","");
        cpf = cpf.replace("-","");

        int n = cpf.length();

        if (n != 11){
            return false;
        }

        //percorre cada posição do CPF , pega os caracteres e verifica de é um dígito
        for (int i = 0; i < cpf.length(); i++) {
            if (!Character.isDigit(cpf.charAt(i))) {
                return false;
            }
        }
        // 1. Pega apenas os 9 primeiros dígitos
        String primeirosNove = cpf.substring(0, 9);



        int soma = 0;
        for (int i = 0; i < primeirosNove.length(); i++) {


            int digito = Character.getNumericValue(primeirosNove.charAt(i));
            int peso = 10 - i;

            int valor = peso * digito;


            soma = soma + valor;

            int primeiroDigito;

            if (resto < 2) {
                resto = 1;
            } else {

                resto -= 11;
            }

        }

            return true;
    }

    ClienteRepository clienteRepository = new ClienteRepository();

//validação de cliente para poder adicionar
    public void adicionarCliente(Cliente c){

        Objects.requireNonNull(c,"O cliente não pode ser null");

       List<Cliente> a = clienteRepository.buscaPorCPF(c.getCpf());

       //verifica se a lista ta vazia
        if (!a.isEmpty()){
            throw new IllegalArgumentException("Já existe um cliente cadastrado com este CPF!");
        }
        clienteRepository.adiciona(c);
    }
}
