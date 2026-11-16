
package com.receivepay.dto;

import com.receivepay.model.Pagamento;
import com.receivepay.model.StatusPagamento;

import java.math.BigDecimal;

public class PagamentoResponseDTO {

    private Long idPagamento;
    private String idExterno;
    private BigDecimal valor;
    private StatusPagamento status;
    private String qrCodePayload;
    private String encodedImage;
    private String invoiceUrl;

    public PagamentoResponseDTO() {}

    public PagamentoResponseDTO(
            Pagamento pagamento,
            String idExterno,
            String qrCodePayload,
            String encodedImage,
            String invoiceUrl) {

        this.idPagamento = pagamento.getId();
        this.idExterno = idExterno;
        this.qrCodePayload = qrCodePayload;
        this.encodedImage = encodedImage;
        this.status = pagamento.getStatus();
        this.valor = pagamento.getValor();
        this.invoiceUrl = invoiceUrl;
    }

    public Long getIdPagamento() {
        return idPagamento;
    }

    public String getIdExterno() {
        return idExterno;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public String getQrCodePayload() {
        return qrCodePayload;
    }

    public String getEncodedImage() {
        return encodedImage;
    }

    public String getInvoiceUrl() {
        return invoiceUrl;
    }
}
