package model;

public enum TipoPagamento {

    PIX( 1),
    CARTAO( 2);

    TipoPagamento(int tipo) {
        this.tipo = tipo;
    }

    private int tipo;
}
