# Conformidade com o Projeto Final

Este documento descreve como o projeto MALDBANK atende aos requisitos do arquivo `Projeto_Final-1.pdf`.

## 1. Processo de Software

O desenvolvimento seguiu uma abordagem incremental e ágil. As implementações foram entregues em etapas:

- Definição do fluxo de cadastro e autenticação
- Separação de responsabilidades entre controller, serviços e repositório
- Implementação das operações financeiras e histórico de transações
- Documentação dos requisitos e arquitetura
- Verificação por meio de testes unitários

## 2. Requisitos de Software

### Requisitos funcionais atendidos

- Cadastro de usuário com escolha de tipo de conta no momento do cadastro
- Autenticação de usuário por CPF e senha
- Atualização e exclusão de cadastro disponíveis apenas após login
- Depósito, saque, transferência por CPF e visualização de saldo
- Histórico de movimentações com registro de depósitos, saques, PIX e investimentos
- Investimentos com cálculo de retorno estimado
- Menu subdividido em opções de cadastro e operações financeiras para o usuário autenticado

### Requisitos não funcionais atendidos

- Código em Java com compilação via Gradle
- Arquitetura modular separando controller, serviços, repositório e modelo
- Persistência em memória para este protótipo
- Uso de testes automatizados com JUnit

## 3. Modelo de Software

A estrutura do projeto está organizada em:

- `app/src/main/java/org/example/Controller`: lógica de menu e fluxo da aplicação
- `app/src/main/java/org/example/Service`: regras de negócio para usuário e finanças
- `app/src/main/java/org/example/Repository`: repositório em memória para `User`
- `app/src/main/java/org/example/Model`: modelo de domínio `User`
- `app/src/main/java/org/example/Entities`: classes de conta e investimento auxiliares

## 4. Princípios de Projeto

O projeto aplica os seguintes princípios descritos no PDF:

- Integridade conceitual: cada classe tem responsabilidade clara
- Coesão: serviços implementam operações específicas e não misturam funcionalidades
- Baixo acoplamento: dependências são injetadas nos construtores dos serviços
- Ocultação de informação: atributos privados expostos por meio de getters/setters
- SOLID: separação de responsabilidades e uso de injeção de dependência
- Composição: `User` compõe listas de `Transacao` e `Investimento`
- Lei de Demeter: os métodos operam diretamente sobre objetos do contexto próximo

## 5. Testes de Software

O sistema já possui testes unitários existentes no módulo `app`.

- Os arquivos de teste estão localizados em `app/src/test/java/org/example`
- Foi adicionada a dependência `Mockito` para permitir o uso de mocks em testes futuros

## 6. Status Atual

- Compilação e execução de testes: **sucesso** (`./gradlew :app:clean :app:test`)
- Documentação de requisitos e arquitetura: `README.md` e `docs/project_compliance.md`
- Organização de diretórios e pacotes conforme padrão Java
