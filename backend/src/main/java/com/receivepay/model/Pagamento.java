package com.receivepay.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "pagamentos")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPagamento tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPagamento status;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    private String idExterno;

    // Construtor vazio obrigatório para o Hibernate/JPA
    public Pagamento() {}

    public Pagamento(Cliente cliente, TipoPagamento tipo, BigDecimal valor) {
        Objects.requireNonNull(valor, "O valor não pode ser nulo!");

        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero!");
        }

        this.cliente = Objects.requireNonNull(cliente, "O cliente não pode ser nulo!");
        this.tipo = Objects.requireNonNull(tipo, "O tipo de pagamento não pode ser nulo!");
        this.status = StatusPagamento.PENDENTE;
        this.valor = valor;
    }

    // Aprova pagamentos
    public void aprovar() {
        if (this.status != StatusPagamento.PENDENTE) {
            throw new IllegalStateException("Apenas pagamentos PENDENTES podem ser aprovados!");
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
                " || Status= " + status +
                " || Cliente: " + (cliente != null ? cliente.getNome() : "N/A");
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

    public String getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(String idExterno) {
        this.idExterno = idExterno;
    }

}