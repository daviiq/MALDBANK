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
## Processo de Software

Este projeto foi desenvolvido com uma abordagem ágil incremental, seguindo práticas de divisão de responsabilidades e entregas iterativas. O grupo repartiu as atividades entre:

- Modelagem e design de requisitos
- Implementação do fluxo de cadastro e autenticação
- Separação de serviços e repositórios
- Adição de histórico de transações e investimentos
- Testes unitários e ajustes de arquitetura

## Requisitos de Software

### Requisitos funcionais
- Cadastro de usuário com escolha do tipo de conta no momento da criação
- Autenticação por CPF e senha
- Atualização e exclusão de cadastro apenas após login
- Depósito, saque, transferência por CPF e visualização de saldo
- Histórico de movimentações completo (depósito, saque, PIX, investimento)
- Investimentos com cálculo simples de retorno e registro em extrato
- Menu subdividido em opções de cadastro e operações financeiras

### Requisitos não funcionais
- Implementação em Java com Gradle
- Arquitetura modular com camadas de Controller, Service, Repository e Model
- Persistência em memória para o protótipo
- Código com alta coesão e baixo acoplamento

## Modelo de Software

### Diagrama de fluxo

```mermaid
graph TD
    Start([Início]) --> MenuPrincipal
    MenuPrincipal -->|1| Cadastro
    MenuPrincipal -->|2| Login
    MenuPrincipal -->|3| Sair
    Cadastro --> FimCadastro
    Login --> SessaoUsuario
    SessaoUsuario -->|Opções de cadastro| MenuCadastro
    SessaoUsuario -->|Operações financeiras| MenuFinanceiro
    MenuFinanceiro --> Historico
    MenuFinanceiro --> Historico
    MenuFinanceiro --> Investimentos
    MenuFinanceiro --> Logout
```

### Diagrama de classes

```mermaid
classDiagram
    class User {
        +String name
        +String cpf
        +String password
        +TipoConta tipoDeConta
        +double saldo
        +List<Transacao> transacoes
        +List<Investimento> investimentos
        +deposit(double)
        +withdraw(double)
        +addTransacao(Transacao)
        +addInvestimento(Investimento)
    }
    class UsuarioService {
        +cadastrarUsuario(...)
        +authenticate(...)
        +atualizarUsuario(...)
        +deletarUsuario(...)
    }
    class FinancaService {
        +depositar(...)
        +sacar(...)
        +transferir(...)
        +investir(...)
    }
    class UserRepository {
        +saveUser(User)
        +getUser(String)
        +updateUser(String, User)
        +deleteUser(String)
    }
    class Transacao {
        +TipoTransacao tipo
        +double valor
        +LocalDateTime dataHora
        +String descricao
    }
    class Investimento {
        +String nome
        +double valor
        +LocalDate dataDeRetirada
        +double retorno
        +calcularRetorno()
    }

    User --> UserRepository
    UsuarioService --> UserRepository
    FinancaService --> UserRepository
    User --> Transacao
    User --> Investimento
```

## Princípios e Propriedades de Projeto

O projeto aplica os princípios a seguir:

- Integridade conceitual: cada classe representa um elemento de domínio claro
- Coesão: `UsuarioService` trata apenas de cadastro e autenticação, `FinancaService` gerencia finanças
- Baixo acoplamento: serviços dependem de `UserRepository` por injeção no construtor
- Ocultação de informação: atributos privados com getters/setters controlados
- Princípios SOLID: separação de responsabilidades e injeção de dependência
- Preferência por composição: `User` compõe `Transacao` e `Investimento`
- Lei de Demeter: métodos operam sobre objetos próximos e evitam navegação profunda

## Testes de Software

O projeto utiliza JUnit para testes unitários. A dependência Mockito foi adicionada para suportar mocks em futuras validações de serviço contra dependências externas.

| Cobertura | Status |
| :--- | :--- |
| Testes de modelo de usuário | existente em `app/src/test/java` |
| Testes de contas e investimentos | existente em `app/src/test/java` |
| Suporte a mocks | dependência adicionada |

---

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
