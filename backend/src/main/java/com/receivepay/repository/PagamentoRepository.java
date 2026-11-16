package com.receivepay.repository;

import com.receivepay.model.Cliente;
import com.receivepay.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    Optional<Pagamento> findById(Long id);

    // Adiciona esta linha para o Spring saber procurar por CPF e retornar a Lista
    List<Pagamento> findByClienteCpf(String cpf);
}