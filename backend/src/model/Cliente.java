package model;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Cliente {

    private String nome;
    private Long id;
    private String email;
    private String cpf;


    public Cliente(String cpf, String email, String nome) {

        //verificar se foi passado algum valor nulo , esse metodo "isBlank()" -> bloqueia a passagem de espaços
        if (nome == null || nome.isBlank() || email == null || email.isBlank() || cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("Nome, email e CPF são obrigatórios!");
        }

        // Geramos um ID aleatório positivo entre 1 e 1.000.000
        this.id = ThreadLocalRandom.current().nextLong(1, 1_000_000L);
        this.cpf = cpf;
        this.email = email;
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) && Objects.equals(cpf, cliente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf);
    }

    @Override
    public String toString() {
        return "Cliente: " + nome +
                " || CPF= " + cpf +
                " || Nome= " + nome  +
                " || id= " + id +
                " || gmail: " + email ;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String gmail) {
        this.email = gmail;
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
