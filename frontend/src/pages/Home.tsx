import { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';

interface Produto {
  id: number;
  nome: string;
  preco: number;
  descricao: string;
}

export function Home() {
  const navigate = useNavigate();

  const produtosDisponiveis: Produto[] = [
    { id: 1, nome: "Curso Completo de Java Spring Boot", preco: 97.00, descricao: "Do zero ao avançado com APIs REST e Banco de Dados." },
    { id: 2, nome: "E-book de Arquitetura Limpa", preco: 49.90, descricao: "Boas práticas de código, SOLID e Domain-Driven Design." },
    { id: 3, nome: "Guia Prático de SQL para Desenvolvedores", preco: 35.00, descricao: "Modelagem, joins, otimização e consultas avançadas." },
    { id: 4, nome: "Curso de React.js & TypeScript Moderno", preco: 89.90, descricao: "Criação de interfaces web responsivas e componentizadas." },
    { id: 5, nome: "Dominando JavaScript ES6+ e DOM", preco: 59.90, descricao: "Fundamentos essenciais e manipulação avançada no navegador." },
    { id: 6, nome: "Docker & Docker Compose na Prática", preco: 45.00, descricao: "Containerização de aplicações back-end e bancos de dados." },
    { id: 7, nome: "Git e GitHub Profissional para Devs", preco: 29.90, descricao: "Controle de versão, branches, merge requests e fluxo ágil." }
  ];

  const [carrinho, setCarrinho] = useState<{ [key: number]: number }>({});

  const adicionarAoCarrinho = (id: number) => {
    setCarrinho(prev => ({
      ...prev,
      [id]: (prev[id] || 0) + 1
    }));
  };

  const removerDoCarrinho = (id: number) => {
    setCarrinho(prev => {
      const novoCarrinho = { ...prev };
      if (novoCarrinho[id] > 1) {
        novoCarrinho[id] -= 1;
      } else {
        delete novoCarrinho[id];
      }
      return novoCarrinho;
    });
  };

  const calcularTotal = () => {
    return Object.entries(carrinho).reduce((total, [idStr, qtd]) => {
      const produto = produtosDisponiveis.find(p => p.id === Number(idStr));
      return total + (produto ? produto.preco * qtd : 0);
    }, 0);
  };

  const valorTotal = calcularTotal();

  const irParaPagamento = () => {
    if (valorTotal <= 0) {
      alert("Adicione pelo menos um item ao carrinho para prosseguir!");
      return;
    }

    // Filtra os produtos selecionados para enviar junto com o valor
    const itensSelecionados = Object.entries(carrinho)
      .filter(([_, qtd]) => qtd > 0)
      .map(([idStr, qtd]) => {
        const produto = produtosDisponiveis.find(p => p.id === Number(idStr));
        return {
          id: produto?.id,
          nome: produto?.nome,
          preco: produto?.preco,
          quantidade: qtd
        };
      });

    // Envia o valor total e os itens para a tela de transação/pagamento
    navigate('/transacao', { 
      state: { 
        valorTotal, 
        itens: itensSelecionados 
      } 
    });
  };

  return (
    <div style={{ minHeight: "100vh", backgroundColor: "#f8fafc", fontFamily: "'Inter', sans-serif" }}>
      <nav style={{ backgroundColor: "#1e293b", padding: "16px 32px", display: "flex", justifyContent: "space-between", alignItems: "center", boxShadow: "0 2px 4px rgba(0,0,0,0.08)" }}>
        <div style={{ color: "#fff", fontWeight: "bold", fontSize: "20px", letterSpacing: "-0.5px" }}>
          Receive<span style={{ color: "#38bdf8" }}>Pay</span>
        </div>
        <div style={{ display: "flex", gap: "20px", alignItems: "center" }}>
          <span style={{ color: "#94a3b8", fontSize: "14px" }}>Painel Principal (PDV)</span>
          <Link to="/" style={{ color: "#f8fafc", textDecoration: "none", fontSize: "14px", fontWeight: "500" }}>Sair</Link>
        </div>
      </nav>

      <main style={{ maxWidth: "750px", margin: "40px auto", padding: "0 20px" }}>
        <div style={{ backgroundColor: "#ffffff", padding: "32px", borderRadius: "16px", boxShadow: "0 4px 12px rgba(0,0,0,0.05)", border: "1px solid #e2e8f0" }}>
          <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", marginBottom: "8px" }}>
            <h2 style={{ fontSize: "24px", fontWeight: "bold", color: "#1e293b", margin: 0 }}>
              🛒 Seleção de Produtos / Carrinho
            </h2>
            <div style={{ backgroundColor: "#eff6ff", padding: "6px 12px", borderRadius: "20px", border: "1px solid #bfdbfe" }}>
              <span style={{ fontSize: "12px", color: "#1d4ed8", fontWeight: "600" }}>Asaas Sandbox Ativo</span>
            </div>
          </div>
          <p style={{ color: "#64748b", fontSize: "14px", marginBottom: "24px" }}>
            Monte o carrinho escolhendo os cursos ou materiais abaixo para gerar a cobrança automática.
          </p>

          <div style={{ display: "flex", flexDirection: "column", gap: "16px", marginBottom: "32px", maxHeight: "420px", overflowY: "auto", paddingRight: "4px" }}>
            {produtosDisponiveis.map(produto => {
              const qtd = carrinho[produto.id] || 0;
              return (
                <div key={produto.id} style={{ display: "flex", justifyContent: "space-between", alignItems: "center", padding: "16px", backgroundColor: "#f8fafc", borderRadius: "12px", border: "1px solid #e2e8f0" }}>
                  <div style={{ flex: 1, paddingRight: "16px" }}>
                    <h4 style={{ margin: "0 0 4px 0", fontSize: "16px", fontWeight: "600", color: "#0f172a" }}>{produto.nome}</h4>
                    <p style={{ margin: "0 0 8px 0", fontSize: "13px", color: "#64748b" }}>{produto.descricao}</p>
                    <span style={{ fontSize: "15px", fontWeight: "bold", color: "#0284c7" }}>R$ {produto.preco.toFixed(2)}</span>
                  </div>

                  <div style={{ display: "flex", alignItems: "center", gap: "10px" }}>
                    {qtd > 0 && (
                      <>
                        <button 
                          onClick={() => removerDoCarrinho(produto.id)}
                          style={{ width: "32px", height: "32px", borderRadius: "8px", border: "1px solid #cbd5e1", backgroundColor: "#fff", cursor: "pointer", fontWeight: "bold" }}
                        >
                          -
                        </button>
                        <span style={{ fontSize: "16px", fontWeight: "600", minWidth: "20px", textAlign: "center" }}>{qtd}</span>
                      </>
                    )}
                    <button 
                      onClick={() => adicionarAoCarrinho(produto.id)}
                      style={{ padding: "8px 16px", borderRadius: "8px", border: "none", backgroundColor: "#009ee3", color: "#fff", cursor: "pointer", fontSize: "13px", fontWeight: "600" }}
                    >
                      {qtd > 0 ? "Adicionar Mais" : "Adicionar"}
                    </button>
                  </div>
                </div>
              );
            })}
          </div>

          <div style={{ borderTop: "2px solid #e2e8f0", paddingTop: "20px", display: "flex", justifyContent: "space-between", alignItems: "center", marginBottom: "24px" }}>
            <span style={{ fontSize: "18px", fontWeight: "600", color: "#1e293b" }}>Saldo / Valor Total:</span>
            <span style={{ fontSize: "26px", fontWeight: "bold", color: "#16a34a" }}>
              R$ {valorTotal.toFixed(2)}
            </span>
          </div>

          <button 
            onClick={irParaPagamento}
            disabled={valorTotal === 0}
            style={{ 
              width: "100%", 
              backgroundColor: valorTotal === 0 ? "#94a3b8" : "#16a34a", 
              color: "#fff", 
              border: "none", 
              padding: "16px", 
              fontSize: "16px", 
              fontWeight: "bold", 
              borderRadius: "10px", 
              cursor: valorTotal === 0 ? "not-allowed" : "pointer",
              boxShadow: valorTotal > 0 ? "0 4px 12px rgba(22, 163, 74, 0.2)" : "none",
              transition: "all 0.2s"
            }}
          >
            {valorTotal > 0 ? `Prosseguir para Pagamento (R$ ${valorTotal.toFixed(2)})` : 'Carrinho Vazio'}
          </button>
        </div>
      </main>
    </div>
  );
}

export default Home;