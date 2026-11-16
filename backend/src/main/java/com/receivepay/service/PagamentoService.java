package com.receivepay.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.receivepay.config.MercadoPagoConfig;
import com.receivepay.dto.PagamentoResponseDTO;
import com.receivepay.model.Cliente;
import com.receivepay.model.Pagamento;
import com.receivepay.model.TipoPagamento;
import com.receivepay.repository.ClienteRepository;
import com.receivepay.repository.PagamentoRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class PagamentoService {

    private final ClienteRepository clienteRepository;
    private final PagamentoRepository pagamentoRepository;
    private final RestTemplate restTemplate;

    public PagamentoService(
            ClienteRepository clienteRepository,
            PagamentoRepository pagamentoRepository,
            RestTemplate restTemplate) {
        this.clienteRepository = clienteRepository;
        this.pagamentoRepository = pagamentoRepository;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public PagamentoResponseDTO adicionarPagamento(
            String cpfCliente,
            BigDecimal valor,
            TipoPagamento tipo,
            String descricao) {

        Objects.requireNonNull(valor, "O valor não pode ser nulo!");
        Objects.requireNonNull(tipo, "O tipo de pagamento não pode ser nulo!");

        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero!");
        }

        // 1. Limpeza e validação do CPF
        String cpfLimpo = cpfCliente != null ? cpfCliente.replaceAll("\\D", "") : "";
        if (cpfLimpo.length() != 11) {
            throw new IllegalArgumentException("CPF inválido!");
        }

        // 2. Busca ou cria o cliente localmente
        List<Cliente> clientesEncontrados = clienteRepository.findByCpf(cpfLimpo);
        Cliente cliente;

        if (clientesEncontrados.isEmpty()) {
            cliente = new Cliente();
            cliente.setCpf(cpfLimpo);
            cliente.setNome("Cliente Balcão PDV");
            cliente.setEmail("pdv.balcao@receivepay.com");
            cliente = clienteRepository.save(cliente);
        } else {
            cliente = clientesEncontrados.get(0);
        }

        // 3. Cria e salva o pagamento localmente como PENDENTE
        Pagamento pagamento = new Pagamento(cliente, tipo, valor);
        pagamento = pagamentoRepository.save(pagamento);

        // 4. Configuração dos cabeçalhos HTTP para o Mercado Pago
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + MercadoPagoConfig.ACCESS_TOKEN);

        // 5. Montagem do item da preferência de pagamento
        String tituloItem = (descricao != null && !descricao.isBlank())
                ? descricao
                : "Pagamento ReceivePay #" + pagamento.getId();

        Map<String, Object> item = new HashMap<>();
        item.put("id", pagamento.getId().toString());
        item.put("title", tituloItem);
        item.put("quantity", 1);
        item.put("currency_id", "BRL");
        item.put("unit_price", valor);

        Map<String, Object> payer = new HashMap<>();
        payer.put("name", cliente.getNome());
        payer.put("email", cliente.getEmail());

        Map<String, String> backUrls = new HashMap<>();
        backUrls.put("success", "https://www.mercadopago.com.br");
        backUrls.put("failure", "https://www.mercadopago.com.br");
        backUrls.put("pending", "https://www.mercadopago.com.br");

        Map<String, Object> corpoRequisicao = new HashMap<>();
        corpoRequisicao.put("items", Collections.singletonList(item));
        corpoRequisicao.put("payer", payer);
        corpoRequisicao.put("external_reference", pagamento.getId().toString());
        corpoRequisicao.put("back_urls", backUrls);
        corpoRequisicao.put("auto_return", "approved");

        var requestPagamento = new HttpEntity<>(corpoRequisicao, headers);
        String urlCheckout = MercadoPagoConfig.API_URL + "/checkout/preferences";

        String idExterno;
        String urlPagamento;

        try {
            // 6. Envia a requisição para o Mercado Pago
            ResponseEntity<JsonNode> resposta = restTemplate.postForEntity(
                    urlCheckout,
                    requestPagamento,
                    JsonNode.class
            );

            if (resposta.getBody() == null) {
                throw new RuntimeException("O Mercado Pago não retornou os dados do checkout!");
            }

            JsonNode jsonResponse = resposta.getBody();
            idExterno = jsonResponse.path("id").asText();

            if (idExterno.isEmpty()) {
                throw new RuntimeException("O Mercado Pago não retornou o ID da preferência!");
            }

            urlPagamento = jsonResponse.path("sandbox_init_point").asText(null);
            if (urlPagamento == null || urlPagamento.isEmpty()) {
                urlPagamento = jsonResponse.path("init_point").asText(null);
            }

            pagamento.setIdExterno(idExterno);
            pagamentoRepository.save(pagamento);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao comunicar com a API do Mercado Pago: " + e.getMessage(), e);
        }

        // 7. Retorna os dados para o Controller mapeando o invoiceUrl corretamente
        return new PagamentoResponseDTO(
                pagamento,
                idExterno,
                null,
                null,
                urlPagamento
        );
    }

    @Transactional
    public void adicionarPagamento(Pagamento p) {
        Objects.requireNonNull(p, "O pagamento não pode ser nulo!");
        pagamentoRepository.save(p);
    }

    @Transactional
    public void cancelaPagamento(Long id) {
        Objects.requireNonNull(id, "O ID não pode ser nulo!");
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pagamento não encontrado!"));
        pagamento.cancelar();
        pagamentoRepository.save(pagamento);
    }

    @Transactional
    public void aprovarPagamento(Long id) {
        Objects.requireNonNull(id, "O ID não pode ser nulo!");
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pagamento não encontrado!"));
        pagamento.aprovar();
        pagamentoRepository.save(pagamento);
    }
}