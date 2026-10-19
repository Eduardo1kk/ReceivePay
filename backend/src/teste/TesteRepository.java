package teste;

import model.Cliente;
import repository.ClienteRepository;

import java.util.List;

public class TesteRepository {
    public static void main(String[] args) {

        ClienteRepository  clienteRepository = new ClienteRepository();

        System.out.println("--- 1. Criando primeiro cliente ---");
        Cliente cliente = new Cliente("123.55.666.554","Edu@gmail","Eduardo");
        System.out.println("Cliente: " + cliente.getNome()+ "\n");
        //adiciona o cliente em uma lista
        clienteRepository.adiciona(cliente);


        System.out.println("--- 2. Criando o segundo cliente ---");
        Cliente cliente2 = new Cliente("12355666556","joao@gmail","João");
        System.out.println("Cliente: " + cliente2.getNome()+ "\n");
        //adiciona o cliente em uma lista
        clienteRepository.adiciona(cliente2);


        System.out.println("--- 3. Criando o terceiro cliente ---");
        Cliente cliente3 = new Cliente("123.55.666.558","Mari@gmail","Maria");
        System.out.println("Cliente: " + cliente3.getNome()+ "\n");
        //adiciona o cliente em uma lista
        clienteRepository.adiciona(cliente3);




        // Chamar o método e guardar o resultado numa lista
        List<Cliente> encontrados = clienteRepository.buscaPorCPF("123.55.666.558");

        System.out.println("---4. Resultado da Busca ---");
        System.out.println("Total encontrados: " + encontrados.size());

        for (Cliente c : encontrados){
            System.out.println("Nome: " + c.getNome() + " | CPF: " + c.getCpf()+"\n");
        }

        //teste de tratamentos
        List<Cliente> encontrado = clienteRepository.buscaPorCPF("123.55.666.550");

        System.out.println("---4. Resultado da Busca ---");
        System.out.println("Total encontrados: " + encontrado.size());

        for (Cliente c : encontrado){
            System.out.println("Nome: " + c.getNome() + " | CPF: " + c.getCpf());
        }

    }
}
