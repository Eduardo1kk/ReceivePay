import { useState } from "react";
import './index.css';
import './App.css';




function App() {
  const [saldo, setSaldo] = useState(0);

  return (
<>
 {/* barra de navegação*/}

      <nav className="navbar">
        <ul>
          <li><a href="#">Início</a></li>
          <li><a href="#">Transferência</a></li>
          <li><a href="#">Extrato</a></li>
        </ul>
      </nav>

{/* Header saldo*/}

    <div className="container-header">
      <h1>Saldo: {saldo}</h1>

      <button onClick={() => {
        setSaldo((saldoAtual) => saldoAtual + 10);
      }}>
        Adicionar 10
      </button>
    </div>


{/* Opções*/}
    <div className="container-body">
      <ul className="options">
        <li><button> Realizar Transferência </button></li>
        <li><button> Pagar com QR Code </button></li>
        <li><button> Pagamento com Cartão</button></li>
      </ul>
    </div>

    <div className="container-cards">
      <button> Trocar de Cartão </button>
    </div>

{/* Seções*/}
    <section className="secao-conteudos">

      <div className="container-conteudos">

      </div>
    </section>

</>  
  )
}

export default App;