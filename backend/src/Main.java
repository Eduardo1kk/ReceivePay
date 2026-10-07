import model.Cliente;
import model.Pagamento;
import model.TipoPagamento;

public class Main {
    public static void main(String[] args) {

        System.out.println("--- 1. Criando um Cliente ---");
        Cliente cliente = new Cliente("123.55.666.555","Edu@gmail","Eduardo");
        System.out.println("Cliente: " + cliente.getNome());

        System.out.println("\n--- 2. Criando um Pagamento Inicial ---");
        // O pagamento nasce como PENDENTE por padrão e com o ID interno gerado aleatoriamente
        Pagamento pagamento = new Pagamento(cliente, null, TipoPagamento.PIX, 150.00);

        System.out.println("ID Interno Gerado: " + pagamento.getId());
        System.out.println("Status Inicial: " + pagamento.getStatus()); // Esperado: PENDENTE

        System.out.println("\n--- 3. Simulando a resposta do banco (Preenchendo o idExterno) ---");
        // O banco processou e devolveu a chave/ID deles
        pagamento.setIdExterno("PIX-BANCO-987654321");

        System.out.println("\n--- 4. Aprovando o Pagamento ---");
        // Usando o método de negócio para mudar o status
        pagamento.aprovar();
        System.out.println("Novo Status: " + pagamento.getStatus()); // Esperado: APROVADO
        System.out.println("Está aprovado? " + pagamento.isAprovado()); // Esperado: true

        System.out.println("\n--- 5. Testando Validação (Tentando criar valor negativo) ---");
        try {
            Pagamento pagamentoInvalido = new Pagamento(cliente, null, TipoPagamento.CARTAO, -50.00);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro capturado com sucesso: " + e.getMessage());
        }
    }
}
