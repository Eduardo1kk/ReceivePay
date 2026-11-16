import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

export function Cadastro() {
  const [nome, setNome] = useState('');
  const [email, setEmail] = useState('');
  const [cpf, setCpf] = useState('');
  const [senha, setSenha] = useState(''); // <-- Estado da senha adicionado
  const navigate = useNavigate();

  const handleCadastro = async (e: React.FormEvent) => {
    e.preventDefault();
    
    try {
      const response = await fetch('http://localhost:8080/api/clientes', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ nome, email, cpf, senha }), // <-- Enviando a senha junto
      });

      if (response.ok) {
        alert('Cadastro realizado com sucesso!');
        navigate('/'); // Volta para a tela de login
      } else {
        const erroMsg = await response.text();
        alert('Erro ao cadastrar: ' + erroMsg);
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
            📝 ReceivePay • Novo Usuário
          </span>
          <h2 style={{ fontSize: '24px', fontWeight: 'bold', color: '#333', marginTop: '12px' }}>
            Cadastrar Conta
          </h2>
        </div>

        <form onSubmit={handleCadastro} style={{ display: 'flex', flexDirection: 'column', gap: '16px' }}>
          <div>
            <label style={{ display: 'block', fontSize: '14px', fontWeight: '500', color: '#555', marginBottom: '6px' }}>
              Nome Completo
            </label>
            <input 
              type="text" 
              placeholder="Seu nome"
              value={nome}
              onChange={(e) => setNome(e.target.value)}
              style={{ width: '100%', padding: '10px 12px', borderRadius: '8px', border: '1px solid #ccc', fontSize: '14px', boxSizing: 'border-box' }}
            />
          </div>

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
              CPF
            </label>
            <input 
              type="text" 
              placeholder="000.000.000-00"
              value={cpf}
              onChange={(e) => setCpf(e.target.value)}
              style={{ width: '100%', padding: '10px 12px', borderRadius: '8px', border: '1px solid #ccc', fontSize: '14px', boxSizing: 'border-box' }}
            />
          </div>

          {/* Campo de Senha Adicionado */}
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
            Cadastrar
          </button>

          <button 
            type="button"
            onClick={() => navigate('/')}
            style={{ 
              backgroundColor: 'transparent', 
              color: '#009ee3', 
              border: 'none', 
              fontSize: '14px', 
              cursor: 'pointer',
              textAlign: 'center',
              marginTop: '5px'
            }}
          >
            Já tem uma conta? Faça login
          </button>
        </form>

      </div>
    </div>
  );
}

export default Cadastro;