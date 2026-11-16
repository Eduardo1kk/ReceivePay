import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

export function Login() {
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();
    
    if (!email || !senha) {
      alert('Por favor, preencha o e-mail e a senha!');
      return;
    }

    try {
      const response = await fetch('http://localhost:8080/api/clientes/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, senha }), // <-- Enviando email e senha para o backend
      });

      if (response.ok) {
        const cliente = await response.json();
        // Salva os dados do usuário logado no localStorage
        localStorage.setItem('@ReceivePay:user', JSON.stringify(cliente));
        navigate('/home'); // Vai para a tela principal (Terminal de Cobrança)
      } else {
        const erroMsg = await response.text();
        alert('Falha no login: ' + erroMsg);
      }
    } catch (error) {
      console.error('Erro de conexão com o backend:', error);
      alert('Não foi possível conectar ao servidor backend.');
    }
  };

  return (
    <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', minHeight: '100vh', backgroundColor: '#f8f9fa' }}>
      <div style={{ background: '#fff', padding: '40px', borderRadius: '12px', boxShadow: '0 4px 12px rgba(0,0,0,0.1)', maxWidth: '400px', width: '100%' }}>
        
        <div style={{ textAlign: 'center', marginBottom: '24px' }}>
          <span style={{ backgroundColor: '#e1f5fe', color: '#0288d1', padding: '6px 12px', borderRadius: '20px', fontSize: '13px', fontWeight: '500' }}>
            🔒 ReceivePay • Acesso
          </span>
          <h2 style={{ fontSize: '24px', fontWeight: 'bold', color: '#333', marginTop: '12px' }}>
            Entrar no Sistema
          </h2>
        </div>

        <form onSubmit={handleLogin} style={{ display: 'flex', flexDirection: 'column', gap: '16px' }}>
          <div>
            <label style={{ display: 'block', fontSize: '14px', fontWeight: '500', color: '#555', marginBottom: '6px' }}>
              E-mail
            </label>
            <input 
              type="email" 
              placeholder="seu@email.com"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              style={{ width: '100%', padding: '10px 12px', borderRadius: '8px', border: '1px solid #ccc', fontSize: '14px', boxSizing: 'border-box' }}
            />
          </div>

          <div>
            <label style={{ display: 'block', fontSize: '14px', fontWeight: '500', color: '#555', marginBottom: '6px' }}>
              Senha
            </label>
            <input 
              type="password" 
              placeholder="********"
              value={senha}
              onChange={(e) => setSenha(e.target.value)}
              style={{ width: '100%', padding: '10px 12px', borderRadius: '8px', border: '1px solid #ccc', fontSize: '14px', boxSizing: 'border-box' }}
            />
          </div>

          <button 
            type="submit"
            style={{ 
              backgroundColor: '#009ee3', 
              color: '#fff', 
              border: 'none', 
              padding: '12px', 
              fontSize: '16px', 
              fontWeight: '600', 
              borderRadius: '8px', 
              cursor: 'pointer',
              marginTop: '10px'
            }}
          >
            Entrar
          </button>

          <button 
            type="button"
            onClick={() => navigate('/cadastro')}
            style={{ 
              backgroundColor: 'transparent', 
              color: '#009ee3', 
              border: 'none', 
              fontSize: '14px', 
              fontWeight: '500',
              cursor: 'pointer',
              textAlign: 'center',
              marginTop: '5px'
            }}
          >
            Não tem uma conta? Cadastre-se
          </button>
        </form>

      </div>
    </div>
  );
}

export default Login;