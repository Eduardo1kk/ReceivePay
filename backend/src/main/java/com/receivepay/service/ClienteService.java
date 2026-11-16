package com.receivepay.service;

import com.receivepay.model.Cliente;
import com.receivepay.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public void adicionarCliente(Cliente c) {
        Objects.requireNonNull(c, "O cliente não pode ser null");

        // 1. Valida se o CPF é válido matematicamente
        if (!isCpfValido(c.getCpf())) {
            throw new IllegalArgumentException("CPF inválido! Por favor, insira um CPF real.");
        }

        // 2. Valida se já existe cliente com este CPF no banco
        List<Cliente> clientesEncontrados = clienteRepository.findByCpf(c.getCpf());

        if (!clientesEncontrados.isEmpty()) {
            throw new IllegalArgumentException("Já existe um cliente cadastrado com este CPF!");
        }

        clienteRepository.save(c);
    }

    // Método auxiliar para validar o formato e os dígitos verificadores do CPF
    private boolean isCpfValido(String cpf) {
        if (cpf == null) return false;

        // Remove caracteres não numéricos (pontos, traços, espaços)
        cpf = cpf.replaceAll("\\D", "");

        // CPF deve ter 11 dígitos e não pode ser uma sequência de números iguais (ex: 111.111.111-11)
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            // Validação do 1º dígito verificador
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += (cpf.charAt(i) - '0') * (10 - i);
            }
            int resto = 11 - (soma % 11);
            int digito1 = (resto == 10 || resto == 11) ? 0 : resto;

            if (digito1 != (cpf.charAt(9) - '0')) {
                return false;
            }

            // Validação do 2º dígito verificador
            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += (cpf.charAt(i) - '0') * (11 - i);
            }
            resto = 11 - (soma % 11);
            int digito2 = (resto == 10 || resto == 11) ? 0 : resto;

            return digito2 == (cpf.charAt(10) - '0');

        } catch (NumberFormatException e) {
            return false;
        }
    }
}