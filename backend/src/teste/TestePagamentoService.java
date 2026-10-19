package teste;

import model.Cliente;
import model.Pagamento;
import model.TipoPagamento;
import repository.ClienteRepository;
import service.PagamentoService;

import java.math.BigDecimal;


public class TestePagamentoService {
    public static void main(String[] args) {

        ClienteRepository clienteRepository = new ClienteRepository();

        System.out.println("--- 1. Criando primeiro cliente ---");
        Cliente cliente = new Cliente("123.55.666.554", "Edu@gmail", "Eduardo");
        System.out.println("Cliente: " + cliente.getNome() + "\n");
        clienteRepository.adiciona(cliente);



        System.out.println("--- 2. Criando um pagamento inicial ---");
        Pagamento pagamento = new Pagamento(
                cliente,
                TipoPagamento.PIX,
                BigDecimal.valueOf(150.80)
        );
        System.out.println("ID interno gerado: " + pagamento.getId());
        System.out.println("Status inicial: " + pagamento.getStatus());




        System.out.println("\n--- 3. Testando o PagamentoService ---");

        PagamentoService pagamentoService = new PagamentoService();
        pagamentoService.adicionarPagamento(pagamento);

        System.out.println("Quantidade após primeiro cadastro: "
                + pagamentoService.getPagamentos().size());

        pagamentoService.adicionarPagamento(pagamento);

        System.out.println("Quantidade após segundo cadastro: "
                + pagamentoService.getPagamentos().size());
    }
}
