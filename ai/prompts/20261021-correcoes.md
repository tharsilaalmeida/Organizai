# Prompt base — Correções gerais do projeto mobile

## 1. Contexto do projeto
Você está trabalhando no projeto Android do Organizaí, em Kotlin com Jetpack Compose, dentro da pasta `app/`.

Objetivo geral do projeto:
- auxiliar estudantes na organização de estudos;
- centralizar tarefas, matérias, calendário e rotina acadêmica;
- manter a aplicação simples, funcional e coerente com o PRD e com a proposta do app.

Arquivos relevantes para análise:
- `app/README.md`
- `README.md`
- `docs/PRD.md`
- `app/app/src/main/java/...` (código do app)
- `app/app/src/main/res/...` (recursos visuais)
- `ai/prompts/` para prompts executados
- `ai/outputs/` para os planos gerados

## 2. Objetivo do prompt
Realizar uma revisão geral do projeto mobile e identificar correções técnicas, de UX, de consistência e de usabilidade que devem ser feitas antes da próxima etapa de desenvolvimento.

O foco é detectar problemas reais no código, na navegação, na lógica de dados, na estrutura de telas e na coerência com o MVP do projeto.

## 3. Direção da execução
Este prompt deve ser usado como base para uma revisão do projeto em vez de uma implementação direta.

A sua tarefa é:
1. analisar o app em relação à estrutura atual do projeto;
2. identificar correções prioritárias;
3. classificar os pontos por impacto e urgência;
4. sugerir uma solução prática e viável;
5. gerar um plano detalhado de execução para a equipe de desenvolvimento.

## 4. Escopo de análise
Revise, no mínimo, os seguintes itens:
- arquitetura e organização das classes;
- consistência entre telas e navegação;
- estados da UI e comportamento de eventos;
- modelagem de dados e uso de ViewModel/StateFlow;
- erros de compilação ou de lógica observáveis;
- inconsistências entre design, requisitos e funcionalidade;
- problemas de UX, textos, labels, telas vazias ou ações confusas;
- casos de falha no carregamento de dados;
- uso de padrões simples e coerentes com o escopo do app.

## 5. Regras de revisão
- priorize correções que impactam funcionamento real do app;
- mantenha a solução simples e alinhada ao escopo do MVP;
- evite adicionar recursos que não estejam no PRD;
- preserve a identidade visual e a proposta educacional do projeto;
- quando houver incerteza, escolha a solução mais segura e mais fácil de manter;
- respeite o contexto de app Android com Kotlin e Compose;
- use linguagem clara, técnica e objetiva;
- não invente requisitos fora do projeto.

## 6. Critérios de qualidade esperados
A revisão deve resultar em:
- lista de problemas detectados;
- prioridade por severidade;
- justificativa de cada correção;
- impacto esperado na experiência do usuário;
- sugestões de implementação;
- ordem lógica de execução para resolver os itens.

## 7. Produto esperado
Ao final da execução, gere um arquivo de planejamento detalhado no formato Markdown em:
`ai/outputs/`

Nome do arquivo sugerido:
`YYYYMMDD-correcoes-gerais-mobile.md`

Esse arquivo deve conter:
1. resumo executivo;
2. problemas identificados;
3. correções priorizadas;
4. plano de implementação por etapa;
5. riscos e pontos de atenção;
6. critérios de validação final.

## 8. Estrutura obrigatória da saída em `ai/outputs/`
Use este formato:

```md
# Plano de correções gerais — Organizaí

## 1. Objetivo
Descreva o objetivo da revisão.

## 2. Contexto
Descreva brevemente o app, o escopo e o estado atual do projeto.

## 3. Problemas identificados
- [Problema 1]
- [Problema 2]
- [Problema 3]

## 4. Correções priorizadas
### Prioridade alta
- item
- item

### Prioridade média
- item
- item

### Prioridade baixa
- item
- item

## 5. Plano de execução
### Etapa 1 — Diagnóstico
- ação
- validação

### Etapa 2 — Core fixes
- ação
- validação

### Etapa 3 — UI/UX e consistência
- ação
- validação

### Etapa 4 — Testes e revisão final
- ação
- validação

## 6. Riscos
- risco
- mitigação

## 7. Critérios de pronto
- item
- item
- item
```

## 9. Instrução final
Antes de responder, confirme que a análise foi feita com foco no projeto real e não em sugestões genéricas.

Então, gere um plano de correções detalhado e organizado em `ai/outputs/`.

## 10. Versão reutilizável para outros prompts
Este prompt pode ser reaproveitado para outros temas, trocando apenas os seguintes campos:
- tema da revisão: `[NOME_DO_TEMA]`
- foco principal: `[UI | DADOS | NAVEGAÇÃO | PERFORMANCE | UX | TESTES | ENTREGABLE]`
- objetivo: `[DESCREVER O QUE PRECISA SER CORRIGIDO]`
- pasta de análise: `[CAMINHO_DA_AREA_DE_ESTUDO]`

Template de adaptação:

```md
# Prompt base — [NOME_DO_TEMA]

## Contexto
Projeto: Organizaí
Área: [ÁREA]
Objetivo: [OBJETIVO]

## Objetivo
[DESCREVER META DA REVISÃO]

## Escopo
- [item]
- [item]
- [item]

## Regras
- [regra]
- [regra]
- [regra]

## Saída esperada
Gerar um plano detalhado em `ai/outputs/[NOME_DO_ARQUIVO].md`.
```

## 11. Regra de ouro
A execução deste tipo de prompt deve sempre produzir um plano acionável, concreto e pronto para implementação, em vez de apenas apontar problemas sem organização.

Esse fluxo é o padrão recomendado para o projeto:
- prompts em `ai/prompts/`
- planos executáveis em `ai/outputs/`
- decisões e refinamentos baseados em contexto real do app
