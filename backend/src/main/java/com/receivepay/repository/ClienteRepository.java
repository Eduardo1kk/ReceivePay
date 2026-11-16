package com.receivepay.repository;

import com.receivepay.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findById(Long id);

    // Método para buscar cliente por CPF (já estava aí)
    List<Cliente> findByCpf(String cpf);

    // ADICIONE ESTA LINHA ABAIXO PARA O LOGIN FUNCIONAR:
    List<Cliente> findByEmail(String email);
}