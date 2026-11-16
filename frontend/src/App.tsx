import { Routes, Route } from "react-router-dom";

import Login from "./pages/Login";
import Cadastro from "./pages/Cadastro";
import Home from "./pages/Home";
import Transacao from "./pages/Transacao"; // <-- Importando a tela de transação

function App() {
  return (
    <Routes>
      <Route path="/" element={<Login />} />
      <Route path="/cadastro" element={<Cadastro />} />
      <Route path="/home" element={<Home />} />
      <Route path="/transacao" element={<Transacao />} /> {/* <-- Rota da transação adicionada */}
    </Routes>
  );
}

export default App;