package model;

public enum StatusPagamento {
    APROVADO(1),
    CANCELADO( 2),
    PENDENTE(3);

    StatusPagamento( int tipo) {
        this.tipo = tipo;
    }

    private int tipo;
    private String situacao;
}
