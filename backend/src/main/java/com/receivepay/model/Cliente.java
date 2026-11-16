package com.receivepay.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String cpf;
    private String senha;

    // Construtor vazio obrigatório para o Hibernate/JPA
    public Cliente() {}

    public Cliente(String cpf, String email, String nome, String senha) {
        if (nome == null || nome.trim().isEmpty()
                || email == null || email.trim().isEmpty()
                || cpf == null || cpf.trim().isEmpty()
                || senha == null || senha.trim().isEmpty()) {

            throw new IllegalArgumentException("Nome, email, CPF e senha são obrigatórios!");
        }

        this.cpf = cpf;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
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
                " || id= " + id +
                " || email: " + email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}