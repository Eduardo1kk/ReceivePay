
package service;

import model.Cliente;
import model.Pagamento;
import model.TipoPagamento;
import repository.ClienteRepository;
import repository.PagamentoRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class PagamentoService {

    private final ClienteRepository clienteRepository;
    private final PagamentoRepository pagamentoRepository;

    public PagamentoService(
            ClienteRepository clienteRepository,
            PagamentoRepository pagamentoRepository) {
        this.clienteRepository = clienteRepository;
        this.pagamentoRepository = pagamentoRepository;
    }

    // Validação de pagamento para poder adicionar
    public Pagamento adicionarPagamento(
            String cpfCliente,
            BigDecimal valor,
            TipoPagamento tipo) {

        Objects.requireNonNull(cpfCliente, "O CPF do cliente não pode ser nulo!");
        Objects.requireNonNull(valor, "O valor não pode ser nulo!");
        Objects.requireNonNull(tipo, "O tipo de pagamento não pode ser nulo!");

        // Busca o cliente pelo CPF
        List<Cliente> clientes = clienteRepository.buscaPorCPF(cpfCliente);

        if (clientes.isEmpty()) {
            throw new IllegalArgumentException("Cliente não encontrado!");
        }

        // Cria o pagamento para o cliente encontrado
        Cliente cliente = clientes.get(0);

        Pagamento pagamento = new Pagamento(cliente, tipo, valor);

        // Adiciona o pagamento ao repositório
        pagamentoRepository.adiciona(pagamento);

        return pagamento;
    }

    // Adiciona um pagamento existente
    public void adicionarPagamento(Pagamento p) {
        Objects.requireNonNull(p, "O pagamento não pode ser nulo!");
        pagamentoRepository.adiciona(p);
    }

    // Cancela pagamento
    public void cancelaPagamento(Long id) {
        Objects.requireNonNull(id, "O ID não pode ser nulo!");

        List<Pagamento> resultado = pagamentoRepository.buscaPorId(id);

        if (resultado.isEmpty()) {
            throw new IllegalArgumentException("Pagamento não encontrado!");
        }

        resultado.get(0).cancelar();
    }

    // Aprova pagamento
    public void aprovarPagamento(Long id) {
        Objects.requireNonNull(id, "O ID não pode ser nulo!");

        List<Pagamento> resultado = pagamentoRepository.buscaPorId(id);

        if (resultado.isEmpty()) {
            throw new IllegalArgumentException("Pagamento não encontrado!");
        }

        resultado.get(0).aprovar();
    }

    public List<Pagamento> getPagamentos() {
        return pagamentoRepository.getPagamentos();
    }
}

