package teste;

import model.Cliente;
import model.Pagamento;
import model.TipoPagamento;
import repository.ClienteRepository;
import service.PagamentoService;

import java.math.BigDecimal;

public class TestePagamentoServise {
    public static void main(String[] args) {

        ClienteRepository clienteRepository = new ClienteRepository();

        PagamentoService pagamentoService = new PagamentoService();

        System.out.println("---1. Cadastra cliente");
        Cliente cliente = new Cliente("1254565465568","juju@gmail.com","Julião");
        System.out.println("Cliente: " + cliente.getNome() + "\n");
        clienteRepository.adiciona(cliente);


        System.out.println("---2. Cadastra pagamento!");
        Pagamento pagamento = new Pagamento(cliente, TipoPagamento.PIX, BigDecimal.valueOf(150));
        System.out.println("Status de pagamento: " + pagamento.getStatus() + "\n");


        System.out.println("---4. Cliente cancelou!");
        pagamentoService.adicionarPagamento(pagamento);
        pagamentoService.cancelaPagamento(pagamento.getId());
        System.out.println("Status: " + pagamento.getStatus());

        System.out.println("---3. Cliente pagou!");
        pagamentoService.adicionarPagamento(pagamento);
        pagamentoService.aprovarPagamento(pagamento.getId());
        System.out.println("Status: " + pagamento.getStatus());








    }
}
