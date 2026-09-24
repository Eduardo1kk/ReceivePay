package model;

import java.util.concurrent.ThreadLocalRandom;

public class Cliente {

    private String nome;
    private Long id;
    private String gmail;
    private String cpf;


    public Cliente(String cpf, String gmail, String nome) {

        //verificar se foi passado algum valor nulo , esse metodo "isBlank()" -> bloqueia a passagem de espaços
        if (gmail == null || gmail.isBlank() || nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Não pode cadastrar clientes sem nome ou sem gmail!");
        }

        // Geramos um ID aleatório positivo entre 1 e 1.000.000
        this.id = ThreadLocalRandom.current().nextLong(1, 1_000_000L);

        this.cpf = cpf;
        this.gmail = gmail;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Cliente: " + nome +
                " || CPF= " + cpf +
                " || Nome= " + nome  +
                " || id= " + id +
                " || gmail: " + gmail ;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
