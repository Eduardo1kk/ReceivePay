package com.receivepay.dto;

import com.receivepay.model.TipoPagamento;
import java.math.BigDecimal;

public class PagamentoDTO {

    private String cpfCliente;
    private BigDecimal valor;
    private TipoPagamento pagamento;
    private String descricao; // Opcional: título que aparece na tela do Mercado Pago (ex: "Pagamento ReceivePay")

    // --- Getters e Setters ---
    public String getCpfCliente() { return cpfCliente; }
    public void setCpfCliente(String cpfCliente) { this.cpfCliente = cpfCliente; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public TipoPagamento getPagamento() { return pagamento; }
    public void setPagamento(TipoPagamento pagamento) { this.pagamento = pagamento; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}