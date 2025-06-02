
-- Criação do esquema financeiro
CREATE SCHEMA IF NOT EXISTS finance;

CREATE TABLE IF NOT EXISTS finance.notes (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    description TEXT
);

-- Tabela de usuários
CREATE TABLE IF NOT EXISTS finance.usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de categorias
CREATE TABLE IF NOT EXISTS finance.categorias (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    tipo VARCHAR(10) NOT NULL CHECK (tipo IN ('RECEITA', 'DESPESA'))
);

-- Tabela de contas bancárias
CREATE TABLE IF NOT EXISTS finance.contas (
    id SERIAL PRIMARY KEY,
    usuario_id INT NOT NULL REFERENCES finance.usuarios(id) ON DELETE CASCADE,
    nome VARCHAR(100) NOT NULL,
    saldo DECIMAL(10,2) DEFAULT 0.00,
    tipo VARCHAR(20) NOT NULL CHECK (tipo IN ('CARTEIRA', 'CONTA_CORRENTE', 'POUPANCA', 'INVESTIMENTO')),
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de transações
CREATE TABLE IF NOT EXISTS finance.transacoes (
    id SERIAL PRIMARY KEY,
    conta_id INT NOT NULL REFERENCES finance.contas(id) ON DELETE CASCADE,
    categoria_id INT REFERENCES finance.categorias(id) ON DELETE SET NULL,
    valor DECIMAL(10,2) NOT NULL,
    data DATE NOT NULL DEFAULT CURRENT_DATE,
    descricao TEXT,
    tipo VARCHAR(10) NOT NULL CHECK (tipo IN ('RECEITA', 'DESPESA')),
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela para orçamentos mensais
CREATE TABLE IF NOT EXISTS finance.orcamentos (
    id SERIAL PRIMARY KEY,
    usuario_id INT NOT NULL REFERENCES finance.usuarios(id) ON DELETE CASCADE,
    categoria_id INT NOT NULL REFERENCES finance.categorias(id) ON DELETE CASCADE,
    mes DATE NOT NULL,
    valor_limite DECIMAL(10,2) NOT NULL,
    valor_utilizado DECIMAL(10,2) DEFAULT 0.00
);

INSERT INTO finance.categorias (nome, tipo) VALUES
  ('Salário', 'RECEITA'),
  ('Investimentos', 'RECEITA'),
  ('Freelance', 'RECEITA'),
  ('Alimentação', 'DESPESA'),
  ('Transporte', 'DESPESA'),
  ('Moradia', 'DESPESA'),
  ('Lazer', 'DESPESA'),
  ('Saúde', 'DESPESA'),
  ('Educação', 'DESPESA')
ON CONFLICT (nome) DO NOTHING;
