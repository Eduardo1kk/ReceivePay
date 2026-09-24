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


    public Pagamento(Cliente cliente, String idExterno, TipoPagamento tipo, double valor) {
        if (valor <= 0){
            throw new IllegalArgumentException("O valor não pode igual a 0 ou negativo !");
        }

        //verifica se o atributo do objeto é null , se for null ele lança um NullPointerException com a mensagem "O cliente não pode ser nulo!"
        this.cliente = Objects.requireNonNull(cliente, "O cliente não pode ser nulo !");

        this.tipo = Objects.requireNonNull(tipo, "O tipo de pagamento não pode ser nulo !");

        this.status = StatusPagamento.PENDENTE;

        // Geramos um ID aleatório positivo entre 1 e 1.000.000
        this.id = ThreadLocalRandom.current().nextLong(1,1_000_000L);

        this.tipo = tipo;

        this.idExterno = null;

        this.valor = BigDecimal.valueOf(valor);

    }

    //Aprova pagamentos
    public void aprovar(){
        if (this.status != StatusPagamento.PENDENTE){
            throw new IllegalStateException("Apenas pagamentos PENDENTES podem ser aprovados !");
        }
        this.status = StatusPagamento.APROVADO;
    }

    public void cancelar(){
        if (this.status == StatusPagamento.APROVADO){
            throw new IllegalStateException("Pagamentos já Aprovadosão pode ser cancelado diretamente.");
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(String idExterno) {
        this.idExterno = idExterno;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }

    public TipoPagamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoPagamento tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
