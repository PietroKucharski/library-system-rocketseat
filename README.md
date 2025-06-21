# 📚 Sistema de Livraria

Desenvolvido como parte do desafio da formação Java da [Rocketseat](https://www.rocketseat.com.br/), este projeto simula um sistema básico de gerenciamento de biblioteca, reforçando os conceitos de Programação Orientada a Objetos (POO) em Java.

## 🚀 Funcionalidades

- ✅ Listagem de livros disponíveis para empréstimo
- ✅ Realização de empréstimos com registro do nome do usuário
- ✅ Marcação de livros como indisponíveis após empréstimo
- ✅ Estrutura com classes `Livro`, `Autor`, `Biblioteca` e `Emprestimo`
- ✅ Loop de interação com o usuário via console

## 🔧 Estrutura do Projeto

O projeto é organizado em classes Java que representam os principais elementos do sistema:

### 📘 Livro
- `id`: Identificador único
- `titulo`: Título do livro
- `autor`: Objeto do tipo `Autor`
- `disponivel`: Disponibilidade para empréstimo
- `dataCadastro`: Data de adição à biblioteca
- `dataAtualizacao`: Última data de modificação

### 🖊 Autor
- `id`: Identificador único
- `nome`: Nome do autor
- `dataNascimento`: Data de nascimento

### 🏛 Biblioteca
- Lista de livros
- Lista de autores
- Lista de empréstimos

### 📄 Emprestimo
- `livro`: Livro emprestado
- `nomeUsuario`: Nome da pessoa que realizou o empréstimo
- `dataEmprestimo`: Data do empréstimo

## 🎯 Regras de Negócio

- Apenas livros disponíveis podem ser emprestados
- Um livro emprestado não pode ser emprestado novamente durante a execução
- O usuário deve informar seu nome no ato do empréstimo

## 🧠 O que foi aprendido

- Conceitos de orientação a objetos em Java
- Criação e manipulação de listas (`ArrayList`)
- Utilização de loops e condicionais
- Interação com o usuário via terminal
- Modelagem de classes e encapsulamento

## 🛠 Tecnologias Utilizadas

- Java 17+
- IDE: IntelliJ IDEA / VSCode / Eclipse (qualquer uma pode ser usada)
- Terminal / Console para entrada de dados

## 📌 Como executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/sistema-livraria-java.git
   
2. Importe o projeto na sua IDE Java preferida.

3. Execute o método main da classe principal.
