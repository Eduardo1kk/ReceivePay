package model;

public enum StatusPagamento {
    APROVADO(01),
    CANCELADO( 02),
    PENDENTE(03);

    StatusPagamento( int tipo) {
        this.tipo = tipo;
    }

    private int tipo;
    private String situacao;
}
