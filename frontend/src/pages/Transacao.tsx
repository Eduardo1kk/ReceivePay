import { Link, useNavigate, useLocation } from "react-router-dom";
import "../App.css";

interface ItemCarrinho {
  id: number;
  nome: string;
  preco: number;
  quantidade: number;
}

export function Transacao() {
  const location = useLocation();
  const navigate = useNavigate();
  
  // Recebe os dados enviados pela Home via state (valor total e lista de itens)
  const { valorTotal = 0, itens = [] } = (location.state as { valorTotal?: number; itens?: ItemCarrinho[] }) || {};

  // Função que envia o pagamento por cartão para o backend e redireciona
  const lidarComPagamentoCartao = async () => {
    if (valorTotal <= 0) {
      alert("O valor da compra está zerado!");
      return;
    }

    try {
      const resposta = await fetch("http://localhost:8080/api/pagamentos", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          cpfCliente: "000.000.000-00", // CPF padrão para o PDV
          valor: valorTotal,
          pagamento: "CARTAO", // Padronizado para Cartão (ajuste para "CREDITO" se o seu Enum exigir)
          descricao: `Compra de ${itens.length} item(ns) no ReceivePay`
        }),
      });

      if (!resposta.ok) {
        const erroTexto = await resposta.text();
        console.error("Erro do servidor:", erroTexto);
        alert(`Erro ao processar pagamento: Status ${resposta.status}`);
        return;
      }

      const dados = await resposta.json();

      // Pega o invoiceUrl enviado pelo PagamentoResponseDTO do Java
      const linkRedirect = dados.invoiceUrl || dados.sandboxInitPoint || dados.initPoint;
      
      if (linkRedirect) {
        // Redireciona o navegador diretamente para o Mercado Pago Sandbox
        window.location.href = linkRedirect;
      } else {
        alert("Pagamento registrado, mas o link de redirecionamento não foi retornado.");
      }
    } catch (erro) {
      console.error("Erro ao conectar com o backend:", erro);
      alert("Falha ao conectar com o servidor de pagamento.");
    }
  };

  return (
    <div style={{ minHeight: "100vh", backgroundColor: "#f8fafc", fontFamily: "'Inter', system-ui, sans-serif" }}>
      {/* Barra de navegação padronizada */}
      <nav
        style={{
          backgroundColor: "#1e293b",
          padding: "16px 32px",
          display: "flex",
          justifyContent: "space-between",
          alignItems: "center",
          boxShadow: "0 2px 4px rgba(0,0,0,0.08)",
        }}
      >
        <div style={{ color: "#fff", fontWeight: "bold", fontSize: "20px", letterSpacing: "-0.5px" }}>
          Receive<span style={{ color: "#38bdf8" }}>Pay</span>
        </div>

        <ul style={{ listStyle: "none", display: "flex", gap: "24px", margin: 0, padding: 0 }}>
          <li>
            <Link to="/home" style={{ color: "#94a3b8", textDecoration: "none", fontWeight: 500, fontSize: "15px" }}>
              Início / Carrinho
            </Link>
          </li>
          <li>
            <Link to="/transacao" style={{ color: "#f8fafc", textDecoration: "none", fontWeight: 500, fontSize: "15px" }}>
              Pagamento
            </Link>
          </li>
        </ul>
      </nav>

      {/* Container Principal */}
      <main style={{ maxWidth: "640px", margin: "40px auto", padding: "0 20px" }}>
        <div
          style={{
            backgroundColor: "#ffffff",
            borderRadius: "16px",
            padding: "32px",
            boxShadow: "0 10px 25px -5px rgba(0, 0, 0, 0.05), 0 8px 10px -6px rgba(0, 0, 0, 0.01)",
            border: "1px solid #e2e8f0",
          }}
        >
          <h2 style={{ fontSize: "24px", fontWeight: 800, color: "#0f172a", margin: "0 0 8px 0", letterSpacing: "-0.025em" }}>
            💳 Pagamento Mercado Pago
          </h2>
          <p style={{ color: "#64748b", fontSize: "15px", marginBottom: "24px" }}>
            Confira os itens selecionados e vá direto para o ambiente de testes do Mercado Pago.
          </p>

          {/* Exibição dos itens que vieram do carrinho */}
          <div style={{ backgroundColor: "#f8fafc", borderRadius: "12px", padding: "16px", marginBottom: "24px", border: "1px solid #e2e8f0" }}>
            <h4 style={{ margin: "0 0 12px 0", fontSize: "15px", color: "#1e293b" }}>Itens da Compra:</h4>
            {itens.length === 0 ? (
              <p style={{ color: "#64748b", fontSize: "14px", margin: 0 }}>Nenhum item encontrado no carrinho.</p>
            ) : (
              <ul style={{ listStyle: "none", padding: 0, margin: 0, display: "flex", flexDirection: "column", gap: "8px" }}>
                {itens.map((item, index) => (
                  <li key={index} style={{ display: "flex", justifyContent: "space-between", fontSize: "14px", color: "#334155" }}>
                    <span>{item.quantidade}x {item.nome}</span>
                    <span style={{ fontWeight: 600 }}>R$ {(item.preco * item.quantidade).toFixed(2)}</span>
                  </li>
                ))}
              </ul>
            )}

            <div style={{ borderTop: "1px solid #e2e8f0", marginTop: "16px", paddingTop: "12px", display: "flex", justifyContent: "space-between", alignItems: "center" }}>
              <span style={{ fontSize: "16px", fontWeight: "bold", color: "#0f172a" }}>Total a Pagar:</span>
              <span style={{ fontSize: "20px", fontWeight: "bold", color: "#16a34a" }}>R$ {Number(valorTotal).toFixed(2)}</span>
            </div>
          </div>

          {/* Botão de Redirecionamento */}
          <div style={{ display: "flex", flexDirection: "column", gap: "16px" }}>
            <button
              onClick={lidarComPagamentoCartao}
              style={{
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
                gap: "12px",
                padding: "16px 20px",
                backgroundColor: "#009ee3",
                border: "none",
                borderRadius: "12px",
                cursor: "pointer",
                width: "100%",
                color: "#fff",
                fontWeight: "bold",
                fontSize: "16px",
                boxShadow: "0 4px 12px rgba(0, 158, 227, 0.25)",
                transition: "background 0.2s"
              }}
            >
              <span>🚀 Ir para o Mercado Pago (Sandbox)</span>
            </button>
          </div>

          {/* Link para voltar */}
          <div style={{ marginTop: "24px", textAlign: "center" }}>
            <Link to="/home" style={{ color: "#64748b", textDecoration: "none", fontSize: "14px", fontWeight: 500 }}>
              &larr; Voltar para o carrinho
            </Link>
          </div>
        </div>
      </main>
    </div>
  );
}

export default Transacao;