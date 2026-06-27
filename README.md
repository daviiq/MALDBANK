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
flowchart TD
    Start([Inicio: executar App.main]) --> Main[Main cria Scanner, UserRepository e UsuarioService]
    Main --> MenuPrincipal[Exibir menu principal]
    MenuPrincipal --> Opcao{Opcao selecionada}

    Opcao -->|1. Cadastre-se| DadosCadastro[Solicitar nome, CPF, tipo de conta e senha]
    DadosCadastro --> ServiceCadastro[UsuarioService.cadastrarUsuario]
    ServiceCadastro --> RepoCadastro[UserRepository.newUser]
    RepoCadastro --> MsgCadastro[Exibir usuario cadastrado com sucesso]
    MsgCadastro --> MenuPrincipal

    Opcao -->|2. Atualizar cadastro| SolicitaCpfAtualizar[Solicitar CPF]
    SolicitaCpfAtualizar --> ServiceAtualizar[UsuarioService.atualizarUsuario]
    ServiceAtualizar --> RepoAtualizar[UserRepository.upDateUser]
    RepoAtualizar --> MsgAtualizar[Exibir usuario atualizado com sucesso]
    MsgAtualizar --> MenuPrincipal

    Opcao -->|3. Deletar cadastro| SolicitaCpfDeletar[Solicitar CPF]
    SolicitaCpfDeletar --> ServiceDeletar[UsuarioService.deletarUsuario]
    ServiceDeletar --> RepoDeletar[UserRepository.deleteUser]
    RepoDeletar --> MsgDeletar[Exibir usuario deletado com sucesso]
    MsgDeletar --> MenuPrincipal

    Opcao -->|4. Visualizar cadastro| SolicitaCpfVisualizar[Solicitar CPF]
    SolicitaCpfVisualizar --> ServiceBuscar[UsuarioService.getUsuario]
    ServiceBuscar --> UsuarioExiste{Usuario encontrado?}
    UsuarioExiste -->|Sim| MostrarDados[Exibir nome, CPF e tipo de conta]
    UsuarioExiste -->|Nao| MostrarNaoEncontrado[Exibir usuario nao encontrado]
    MostrarDados --> MenuPrincipal
    MostrarNaoEncontrado --> MenuPrincipal

    Opcao -->|5. Criar conta bancaria| MenuContas[Exibir tipos de conta]
    MenuContas --> TipoConta{Tipo selecionado}
    TipoConta -->|1. Corrente| DadosContaCorrente[Solicitar dados do titular]
    TipoConta -->|2. Investimento| DadosContaInvestimento[Solicitar dados do titular]
    TipoConta -->|3. Poupanca| DadosContaPoupanca[Solicitar dados do titular]
    TipoConta -->|4. Salario| DadosContaSalario[Solicitar dados do titular]
    TipoConta -->|5. Sair| MenuPrincipal
    DadosContaCorrente --> ConfirmarDados{Dados corretos?}
    DadosContaInvestimento --> ConfirmarDados
    DadosContaPoupanca --> ConfirmarDados
    DadosContaSalario --> ConfirmarDados
    ConfirmarDados -->|S| ContaCriada[Exibir conta criada]
    ConfirmarDados -->|N| MenuContas
    ContaCriada --> MenuPrincipal

    Opcao -->|6. Acessar conta| SemImplementacao[Opcao listada, sem case implementado no switch]
    SemImplementacao --> MenuPrincipal

    Opcao -->|7. Sair| Fim([Fechar Scanner e encerrar])
    Opcao -->|Entrada invalida| ErroOpcao[Exibir opcao invalida]
    ErroOpcao --> MenuPrincipal


    class Start,Fim endpoint
    class Opcao,UsuarioExiste,TipoConta,ConfirmarDados decision
    class Main,MenuPrincipal,DadosCadastro,ServiceCadastro,RepoCadastro,MsgCadastro,SolicitaCpfAtualizar,ServiceAtualizar,RepoAtualizar,MsgAtualizar,SolicitaCpfDeletar,ServiceDeletar,RepoDeletar,MsgDeletar,SolicitaCpfVisualizar,ServiceBuscar,MostrarDados,MostrarNaoEncontrado,MenuContas,DadosContaCorrente,DadosContaInvestimento,DadosContaPoupanca,DadosContaSalario,ContaCriada,SemImplementacao,ErroOpcao process
```
## Diagrama de Classes:
```mermaid
classDiagram
    direction TB

    class App {
        +main(args: String[])$ void
    }

    class Main {
        +main(args: String[])$ void
    }

    class User {
        -String name
        -String cpf
        -String password
        -String tipoDeConta
    }

    class UserRepository {
        -Map~String, User~ users
        +newUser(user: User, tipoDeconta: String) User
        +upDateUser(cpf: String, updatedUser: User) boolean
        +deleteUser(cpf: String) boolean
        +getUser(cpf: String) User
    }

    class Usuario {
        -String nome
        -String CPF
        -int idade
        -String telefone
        -String email
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
        -User usuario
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
    }

    class ContaCorrente {
        +depositar(valor: double) void
        +sacar(valor: double) void
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
        -String nome
        -double valor
        ~LocalDate dataDeRetirada
        ~double retorno
        +calcularRetorno() double
    }

    class Transacao {
    }

    %% Relacionamentos do Fluxo Principal
    App ..> Main : chama
    Main --> UserRepository : instancia
    Main --> UsuarioService : instancia
    Main ..> Usuario : cria dados de conta
    UsuarioService --> UserRepository : utiliza
    UsuarioService --> User : gerencia
    UserRepository "1" o--> "*" User : armazena

    %% Core Business e Domínio
    Conta "1" --> "1" Usuario : pertence a
    Conta "1" --> "1" TipoConta : define tipo
    Conta "1" *--> "*" Transacao : possui histórico de

    %% Estrutura de Herança
    ContaCorrente --|> Conta : estende
    ContaInvestimento --|> Conta : estende
    ContaPoupanca --|> Conta : estende
    ContaSalario --|> Conta : estende

    %% Implementações e Dependências de Métodos
    ContaCorrente ..|> Pagamento : implementa
    ContaInvestimento ..> Investimento : utiliza
```
## Diagrama de Casos de Uso:
```mermaid
graph LR
    %% Definição do Ator
    Usuario((Usuário))

    %% Fronteira do Sistema
    subgraph Sistema_Bancario [Sistema Bancário / App]
        UC1(Cadastrar Usuário)
        UC2(Atualizar Cadastro)
        UC3(Deletar Cadastro)
        UC4(Visualizar Cadastro)
        UC5(Criar Conta Bancária)
        UC6(Acessar Conta)
        UC7(Utilizar metodos da conta)

        %% Especializações/Extensões de Criar Conta
        UC5_1(Criar Conta Corrente)
        UC5_2(Criar Conta Investimento)
        UC5_3(Criar Conta Poupança)
        UC5_4(Criar Conta Salário)
    end

    %% Relacionamentos do Ator com os Casos de Uso Principais
    Usuario --> UC1
    Usuario --> UC2
    Usuario --> UC3
    Usuario --> UC4
    Usuario --> UC5
    Usuario --> UC6
    Usuario --> UC7

    %% Relacionamentos de Extensão para os Tipos de Conta
    UC5_1 -.->|"<<extend>>"| UC5
    UC5_2 -.->|"<<extend>>"| UC5
    UC5_3 -.->|"<<extend>>"| UC5
    UC5_4 -.->|"<<extend>>"| UC5

    style Usuario fill:#fff,stroke:#333,stroke-width:2px
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
2. **Rodar o App**
   Pré-requisitos: Certifique-se de ter o JDK 17 ou superior instalado em sua máquina.

## Equipe de Desenvolvimento

Este projeto é um esforço colaborativo dos seguintes membros:

* **Adriel Alves Ferreira**
* **Davi Israel Quirino**
* **Marcos Júnior Lemes**
* **Lucas Luiz Guesser**

