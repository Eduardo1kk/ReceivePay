import { Link } from "react-router-dom";
import "../App.css";

function Cartao() {
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

{/* trocar cartao*/}
      <div>

      </div>
    </>
  );
}

export default Cartao;