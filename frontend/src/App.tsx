import { useState } from "react";
import './index.css';
import './App.css';




function App() {
  const [saldo, setSaldo] = useState(0);

  return (
<>


    <div className="container-header">
      <h1>Saldo: {saldo}</h1>

      <button onClick={() => {
        setSaldo((saldoAtual) => saldoAtual + 10);
      }}>
        Adicionar 10
      </button>
    </div>



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

</>  
  );
}

export default App;