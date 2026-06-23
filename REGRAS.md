# Boas Práticas e Diretrizes de Desenvolvimento

Este documento estabelece as regras e princípios arquiteturais que todos os membros da equipe devem seguir para garantir a qualidade, sustentabilidade e escalabilidade do nosso código.

---

## Princípios Fundamentais

### 1. Alta Coesão
Cada módulo, classe ou função deve ter **uma única responsabilidade bem definida**. 
* **Regra:** Se você tiver dificuldade em explicar o que uma função faz sem usar a palavra "e", ela provavelmente precisa ser dividida.
* **Benefício:** Código mais fácil de testar, ler e manter.

### 2. Baixo Acoplamento
Os componentes do sistema devem depender o mínimo possível uns dos outros.
* **Regra:** Programe para interfaces/abstrações, não para implementações de software específicas. Alterar o funcionamento interno de uma classe não deve quebrar outras partes do sistema.
* **Benefício:** Flexibilidade para alterar tecnologias ou lógicas sem gerar um efeito dominó de bugs.

### 3. Princípios SOLID
* **S**ingle Responsibility (Responsabilidade Única)
* **O**pen/Closed (Aberto para extensão, fechado para modificação)
* **L**iskov Substitution (Substituição de Liskov)
* **I**nterface Segregation (Segregação de Interfaces)
* **D**ependency Inversion (Inversão de Dependência)

---

## Regras do Repositório e Fluxo de Trabalho

### Git & Branching
* **Padrão de Nomenclatura:** Use prefixos claros para suas branches e commits:
  * `feat: nome-da-feature` para novos recursos.
  * `fix: nome-do-bug` para correção de erros.
  * `senhaConta ou senha_conta` para padronização de variaveis de forma objetiva.
* **Siga o padrãos:**  *Conventional Commits* (ex: `feat: adiciona sistema de login`, `fix: corrige quebra de layout no mobile`).

### Code Review (Revisão de Código)
* **Critérios de Aprovação:** O revisor deve atentar-se a:
  * O código está limpo e legível?
  * Existem testes unitários cobrindo a nova lógica?
  * O princípio de baixo acoplamento foi respeitado?

### Testes Automatizados
* **Não quebre a esteira (CI):** Garanta que todos os testes passem localmente antes de finalizar.

---

## Clean Code (Código Limpo)

> "Código limpo é aquele que parece ter sido escrito por alguém que se importa." — Michael Feathers

* **Nomes Significativos:** Variáveis e funções devem revelar sua intenção. Evite nomes genéricos como `data`, `aux`, `info`.
* **Funções Pequenas:** Funções devem ser pequenas e fazer apenas uma coisa.
* **Evite Comentários Óbvios:** O código deve ser autoexplicativo. Use comentários apenas para explicar o *porquê* de uma decisão de design complexa, não o *o que* o código faz.

---

## 💡 Checklist do Desenvolvedor (Antes de abrir um PR)

- [ ] O código foi testado localmente e funciona.
- [ ] O código segue os princípios de Alta Coesão e Baixo Acoplamento.
- [ ] Não há código morto (comentado ou sem uso).
- [ ] As variáveis, funções e classes têm nomes claros (seguir o padrão do projeto).
- [ ] Os testes unitários foram criados/atualizados.
