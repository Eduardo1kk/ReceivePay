package model;


import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Pagamento {

    private Long id;
    private BigDecimal valor;
    private TipoPagamento tipo;
    private StatusPagamento status;
    private Cliente cliente;
    private String idExterno;


    public Pagamento(Cliente cliente, TipoPagamento tipo, BigDecimal valor) {

        //verificar se o valor é null
        Objects.requireNonNull(valor, "O valor não pode ser nulo!");
        //verificar se o valor é menor que ou igual a 0
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero!");
        }

        this.cliente = Objects.requireNonNull(cliente, "O cliente não pode ser nulo!");
        this.tipo = Objects.requireNonNull(tipo, "O tipo de pagamento não pode ser nulo!");
        this.status = StatusPagamento.PENDENTE;
        this.id = ThreadLocalRandom.current().nextLong(1, 1_000_000L);
        this.valor = valor;
    }

    //Aprova pagamentos
    public void aprovar(){
        if (this.status != StatusPagamento.PENDENTE){
            throw new IllegalStateException("Apenas pagamentos PENDENTES podem ser aprovados !");
        }
        this.status = StatusPagamento.APROVADO;
    }

    public void cancelar() {
        if (this.status != StatusPagamento.PENDENTE) {
            throw new IllegalStateException("Apenas pagamentos PENDENTES podem ser cancelados!");
        }
        this.status = StatusPagamento.CANCELADO;
    }

    public boolean isAprovado() {
        return this.status == StatusPagamento.APROVADO;
    }

    public boolean isPendente() {
        return this.status == StatusPagamento.PENDENTE;
    }

    @Override
    public String toString() {
        return "Valor: " + valor +
                " || id= " + id +
                " || Status= " + status  +
                " || id= " + id +
                " || Cliente: " + cliente.getNome() ;
    }


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public TipoPagamento getTipo() {
        return tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
