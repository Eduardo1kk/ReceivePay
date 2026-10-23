
package controller;

// importando os pacotes do back
import dto.PagamentoDTO;
import model.Pagamento;
import service.PagamentoService;

// importando spring boot
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping
    public ResponseEntity<Pagamento> criarPagamento(@RequestBody PagamentoDTO dto) {

        Pagamento pagamento = pagamentoService.adicionarPagamento(
                dto.getCpfCliente(),
                dto.getValor(),
                dto.getPagamento()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(pagamento);
    }
}
