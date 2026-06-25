# MALDBANK

Criação do MALDBANK como projeto final da matéria de engenharia de Software

Sobre o Projeto

O **MALDBANK** é um sistema de simulação bancária robusto que permite o gerenciamento completo de usuários e suas respectivas contas. Desenvolvido inteiramente em Java, o sistema oferece operações financeiras fundamentais com suporte a múltiplos tipos de conta de forma polimórfica (como **Conta Corrente** e **Conta Poupança**).

### Principais Funcionalidades

* **Gestão de Usuários:** Fluxo de cadastro e autenticação simplificada via terminal.
* **Gestão de Contas:** Abertura e vinculação de contas dinâmicas com comportamento polimórfico.
* **Operações Financeiras:** * Consulta de saldo em tempo real
  * Realização de saques e depósitos
  * Transferências entre contas
  * Pagamento de contas/boletos
* **Histórico:** Emissão automatizada de extrato detalhado contendo todas as movimentações da conta ativa.

---

## Arquitetura do Sistema

Alinhado com os princípios de design de software modernos, o sistema foi projetado sob os pilares de **baixo acoplamento** e **alta coesão**. Por se tratar de um protótipo ágil, utiliza o padrão de **repositório em memória** (*In-Memory Repository*), garantindo a persistência temporária e o gerenciamento dos dados de forma segura durante o ciclo de vida da sessão atual.

## Fluxograma de interação:

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
## Diagrama de Classes:
```mermaid
classDiagram
    direction TB

    %% Estilos de Cores para Melhor Leitura
    style App fill:#ececff,stroke:#9370db,stroke-width:2px
    style Main fill:#ececff,stroke:#9370db,stroke-width:2px
    style Pagamento fill:#fff2cc,stroke:#d6b656,stroke-width:2px
    style TipoConta fill:#f8cecc,stroke:#b85450,stroke-width:2px
    style Conta fill:#e1d5e7,stroke:#9673a6,stroke-width:2px,stroke-dasharray: 5 5

    %% Classes fora de subgraph para evitar conflitos de renderização no motor antigo do Mermaid
    class App {
        +main(args: String[])$ void
    }
    class Main {
        +main(args: String[])$ void
    }
    class User {
        private String name
        private String cpf
        private String password
        private String tipoDeConta
    }
    class UserRepository {
        +newUser(usuario: User, CPF: String) void
        +upDateUser(CPF: String, usuario: User) void
        +deleteUser(CPF: String) void
        +getUser(CPF: String) User
    }
    class Usuario {
        private String nome
        private String CPF
        private int idade
        private String telefone
        private String email
        +imprimirDados() void
    }
    class TipoConta {
        <<enumeration>>
        CORRENTE
        INVESTIMENTO
        POUPANCA
        SALARIO
    }
    class Pagamento {
        <<interface>>
        +pagar(valor: double) void
    }
    class UsuarioService {
        ~UserRepository userRepository
        private User usuario
        +cadastrarUsuario(nome, CPF, tipoDeConta, password) void
        +atualizarUsuario(CPF, usuarioAtualizado) void
        +deletarUsuario(CPF) void
        +getUsuario(CPF) User
    }
    class Conta {
        <<abstract>>
        #double saldo
        #String numeroConta
        #String titular
        #Usuario usuario
        #TipoConta tipoConta
        #List~Transacao~ historico
    }
    class ContaCorrente {
        +depositar(valor: double) void
        +sacar(valor: double) void
        +pagar(valor: double) void
    }
    class ContaInvestimento {
        +depositar(valor: double) void
        +sacar(valor: double) void
        +investir(investimento: Investimento) void
    }
    class ContaPoupanca {
        +depositar(valor: double) void
        +renderJuros(taxa: double) void
    }
    class ContaSalario {
        +sacar(valor: double) void
    }
    class Investimento {
        private String nome
        private double valor
        ~LocalDate dataDeRetirada
        ~double retorno
        +calcularRetorno() double
    }
    class Transacao {
    }

    %% Ligações de Infraestrutura e Negócio
    App ..> Main : chama
    Main --> UserRepository : instancia
    Main --> UsuarioService : depende
    UsuarioService --> UserRepository : utiliza
    UsuarioService --> User : gerencia

    %% Core Business (Relacionamentos de Conta)
    Conta "1" --> "1" Usuario : pertence a
    Conta "1" --> "1" TipoConta : define tipo via
    Conta "1" *--> "*" Transacao : possui histórico de

    %% Estrutura de Herança (Polimorfismo)
    ContaCorrente --|> Conta : estende
    ContaInvestimento --|> Conta : estende
    ContaPoupanca --|> Conta : estende
    ContaSalario --|> Conta : estende

    %% Implementações e Dependências de Métodos
    ContaCorrente ..|> Pagamento : implementa
    ContaInvestimento ..> Investimento : utiliza
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
