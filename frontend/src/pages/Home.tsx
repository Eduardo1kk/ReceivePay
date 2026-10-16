import { useState } from "react";
import { Link } from "react-router-dom";
import "../App.css";

function Home() {
  const [saldo, setSaldo] = useState(0);

  return (
    <>
{/* Barra de navegação */}

      <nav className="navbar">
        <ul>
          <li><Link to="/">Início</Link></li>
          <li><Link to="/pix">Transferência</Link></li>
          <li><Link to="#">Extrato</Link></li>
        </ul>
      </nav>

{/* Header saldo */}

<div className="container-header">
  <h1>Saldo: {saldo}</h1>

    <button onClick={() => {setSaldo((saldoAtual) => saldoAtual + 10);
          }}>
          Adicionar 10
        </button>
</div>

{/* Opções */}

<div className="container-body">
  <ul className="options">
    <li>
      <Link to="/pix">Realizar Transferência</Link>
    </li>
    <li>
      <Link to="#">Pagar com QR Code</Link>
    </li>
    <li>
       <Link to="#">Pagamento com Cartão</Link>
    </li>
  </ul>
</div>

<div className="container-cards">
  <button>Trocar de Cartão</button>
</div>

{/* Seções */}

      <section className="secao-conteudos">
        <div className="container-conteudos"></div>
      </section>
    </>
  );
}

export default Home;