import { Routes, Route } from "react-router-dom";

import Home from "./pages/Home";
import Pix from "./pages/Pix";
import Cartao from "./pages/Cartao";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/pix" element={<Pix />} />
      <Route path="/cartao" element={<Cartao />} />
    </Routes>
  );
}

export default App;
