package dto;

import model.Pagamento;
import model.StatusPagamento;

import java.math.BigDecimal;

public class PagamentoResponseDTO {

    private Long idPagamento;
    private String idExterno;
    private BigDecimal valor;
    private StatusPagamento status;
    private String qrCodePayload;


    public PagamentoResponseDTO() {}


    public PagamentoResponseDTO(Pagamento pagamento, String qrCodePayload) {
        this.idExterno = pagamento.getIdExterno();
        this.idPagamento = pagamento.getId();
        this.qrCodePayload = qrCodePayload;
        this.status = pagamento.getStatus();
        this.valor = pagamento.getValor();
    }

    public String getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(String idExterno) {
        this.idExterno = idExterno;
    }

    public Long getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(Long idPagamento) {
        this.idPagamento = idPagamento;
    }

    public String getQrCodePayload() {
        return qrCodePayload;
    }

    public void setQrCodePayload(String qrCodePayload) {
        this.qrCodePayload = qrCodePayload;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
