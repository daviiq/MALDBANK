# MALDBANK

Criação do MALDBANK como projeto final da matéria de engenharia de Software

Sobre o Projeto
O MALDBANK é um sistema de simulação bancária que permite o gerenciamento de usuários e suas contas, oferecendo operações financeiras com suporte a diferentes tipos de conta (EX: Corrente e Poupança).

Principais Funcionalidades
Gestão de Usuários: Cadastro e autenticação via terminal.

Gestão de Contas: Abertura de contas com tipos distintos e polimórficos.

Operações Financeiras: Consultar saldo, Saque, Depósito, Transferência e Pagamento.

Histórico: Emissão de extrato de movimentações.

Arquitetura do Sistema
O sistema foi desenhado para manter o baixo acoplamento e alta coesão, utilizando um padrão de repositório em memória para a persistência temporária dos dados durante a execução da sessão.
## Fluxograma do Sistema

```mermaid
graph TD
    
    Start([Inicio: Execucao do Sistema MALDBANK]) --> MenuPrincipal[Exibir Menu Principal: 1. Cadastrar Usuario, 2. Selecionar Usuario, 3. Sair]:::process
    
    MenuPrincipal --> OpcaoMP{Qual a opcao selecionada?}:::decision
    
    %% FLUXO 1: CADASTRAR USUARIO
    OpcaoMP -->|1| SolicitDadosUser[Solicitar Nome e CPF do Usuario]:::process
    SolicitDadosUser --> CheckUserExiste{CPF ja existe no Repository?}:::decision
    CheckUserExiste -->|Sim| ErrUser[Exibir Erro: Usuario ja cadastrado]:::process
    ErrUser --> MenuPrincipal
    CheckUserExiste -->|Não| SalvarUser[Instanciar objeto Usuario e salvar no Repository]:::process
    SalvarUser --> MenuPrincipal
    
    %% FLUXO 2: SELECIONAR USUARIO
    OpcaoMP -->|2| SolicitCPF[Solicitar CPF para Identificacao]:::process
    SolicitCPF --> BuscarUser{Usuario cadastrado no Repository?}:::decision
    BuscarUser -->|Não| ErrNoUser[Exibir Erro: Usuario nao encontrado]:::process
    ErrNoUser --> MenuPrincipal
    BuscarUser -->|Sim| DefinirSessaoUser[Definir objeto Usuario como logado]:::process
    
    DefinirSessaoUser --> MenuUsuario[Exibir Menu do Usuario: 1. Criar Conta, 2. Acessar Conta, 3. Voltar]:::process
    MenuUsuario --> OpcaoMU{Qual a opcao selecionada?}:::decision
    OpcaoMU -->|3| MenuPrincipal
    
    %% SUBFLUXO: CRIAR CONTA
    OpcaoMU -->|1| SolicitTipo[Solicitar Tipo: 1. Corrente, 2. Poupanca]:::process
    SolicitTipo --> InstanciarConta[Instanciar subclasse Conta e salvar no Repository]:::process
    InstanciarConta --> MenuUsuario
    
    %% SUBFLUXO: ACESSAR CONTA
    OpcaoMU -->|2| ListarContas[Listar contas do Usuario]:::process
    ListarContas --> SelecionarNum[Solicitar numero da Conta]:::process
    SelecionarNum --> DefinirSessaoConta[Definir Conta como ativa na sessao]:::process
    
    %% OPERACOES
    DefinirSessaoConta --> MenuOperacoes[Exibir Operacoes: 1. Saldo, 2. Saque, 3. Deposito, 4. Transferencia, 5. Pagamento, 6. Extrato, 7. Voltar]:::process
    MenuOperacoes --> OpcaoMO{Qual operacao?}:::decision
    
    OpcaoMO -->|1| GetSaldo[Exibir Saldo]:::process
    OpcaoMO -->|2| InputSaque[Processar Saque]:::process
    OpcaoMO -->|3| InputDep[Processar Deposito]:::process
    OpcaoMO -->|4| InputTransf[Processar Transferencia]:::process
    OpcaoMO -->|5| InputPag[Processar Pagamento]:::process
    OpcaoMO -->|6| GetHist[Exibir Extrato]:::process
    OpcaoMO -->|7| MenuUsuario
    
    GetSaldo --> MenuOperacoes
    InputSaque --> MenuOperacoes
    InputDep --> MenuOperacoes
    InputTransf --> MenuOperacoes
    InputPag --> MenuOperacoes
    GetHist --> MenuOperacoes
    
    OpcaoMP -->|3| Fim([Fim: Encerrar Programa]):::endpoint
```
## Tecnologias e Metodologias

| Categoria | Tecnologias / Práticas |
| :--- | :--- |
| **Linguagem** | Java |
| **Metodologias** | Scrum, Kanban, XP |
| **Testes** | JUnit 5, Mockito |
| **Documentação** | Mermaid.js |

---

## Como Executar

Para rodar o projeto localmente, siga os passos abaixo:

1. **Clonar o repositório:**
   ```bash
   git clone [https://github.com/daviiq/MALDBANK](https://github.com/daviiq/MALDBANK)
   ```
   Pré-requisitos: Certifique-se de ter o JDK 17 ou superior instalado em sua máquina.

## Equipe de Desenvolvimento

Este projeto é um esforço colaborativo dos seguintes membros:

* **Adriel Alves Ferreira**
* **Davi Israel Quirino**
* **Marcos Júnior Lemes**
* **Lucas Luiz Guesser**

**Colaborador **
* *Monica Cancellier* 
