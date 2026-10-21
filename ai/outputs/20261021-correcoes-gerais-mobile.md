# Plano de correções gerais — Organizaí

## 1. Objetivo
Revisar o app Android em desenvolvimento para identificar problemas de estrutura, navegação, consistência de UI e viabilidade do MVP, além de priorizar correções que tornem o projeto funcional, coerente e pronto para evolução em etapas.

## 2. Contexto
O projeto Organizaí é um app mobile para organização de estudos, com foco em estudantes. O código atual está em Kotlin com Jetpack Compose e apresenta telas prototipadas, dados simulados e vários pontos de interface sem funcionalidade real. O app está em estágio inicial de protótipo, mas ainda precisa de alinhamento entre estrutura, fluxo e regras do MVP.

Durante a análise, observou-se que o foco principal ainda está na interface visual e na demonstração de telas, sem integração real de navegação, persistência e lógica de regras do domínio.

## 3. Problemas identificados
- O fluxo principal do app ainda não está integrado de forma real; várias telas possuem botões e ações vazias (`/* ... */`), indicando protótipo visual sem navegação funcional.
- A navegação entre telas depende de `selectedItem` em `MainContainerScreen`, mas não existe um sistema de rotas, navegação por pilha ou passagem de estados consistente.
- O `TaskViewModel` possui dados mockados fixos em memória e não implementa persistência local, dificultando uso real no app.
- A tela de calendário usa dados estáticos e filtro fixo para "2026-10-10", sem seleção real de data, o que compromete a consistência do recurso.
- `AddTaskScreen` coleta dados, mas não salva nem valida a entrada, então a criação de tarefa não funciona de fato.
- Login e cadastro possuem somente UI; não há autenticação, validação de formulário ou integração com banco/armazenamento.
- Há inconsistência de nomenclatura e estratégia entre telas: algumas usam `TopAppBar`, outras `CenterAlignedTopAppBar`; alguns botões possuem ações simuladas e outras não conectam com estado real.
- O estado dos dados e o feedback de erro são limitados, com mensagem fixa e sem mecanismo real de fallback para falhas de carregamento.
- O projeto ainda não apresenta um fluxo claro de edição, exclusão e atualização de tarefas, mesmo sendo funcionalidade essencial do MVP.
- O código está pronto para demonstração, mas não para uso real de usuário; há risco de baixa manutenção e de funcionalidade fragmentada.
- A estrutura pode sofrer com acoplamento visual e lógico em telas separadas; mais tarde, qualquer mudança de modelo exigirá revisão em diversos pontos.
- O ambiente de build não foi validado de forma completa no workspace atual, pois o gradlew não está presente na estrutura do projeto, o que indica risco de entrega e baixa automação de build.

## 4. Correções priorizadas

### Prioridade alta
- Definir e implementar uma navegação consistente entre telas, com fluxo real de login, home, calendário, matérias, perfil e detalhes.
- Conectar a criação de tarefas à lógica de dados, com validação de título, data e matéria.
- Implementar persistência simples para tarefas e matérias, em memória ou local, para que o app deixe de depender apenas de dados mockados.
- Ajustar a lógica do `TaskViewModel` para suportar CRUD real e estado reativo sem depender de valores fixos.
- Corrigir o calendário para usar data real, seleção de dia e exibição de tarefas por data dinâmica.

### Prioridade média
- Padronizar a UI para um mesmo estilo visual e uso de componentes, reduzindo inconsistências entre telas.
- Implementar validação de formulários em login, cadastro e criação de tarefas.
- Melhorar as mensagens de erro e estado vazio para cenários reais do usuário.
- Criar separação mais clara entre telas de apresentação e regras de negócio, seguindo arquitetura mais simples e sustentável.
- Revisar textos, labels, ações e feedbacks para deixar o fluxo mais compreensível para estudantes.

### Prioridade baixa
- Ajustar detalhes de estética e espaçamento para uma experiência mais refinada.
- Expandir a lógica de progresso e estatísticas para refletir dados reais do usuário.
- Preparar estrutura para futuras telas avançadas sem comprometer o MVP.

## 5. Plano de execução

### Etapa 1 — Diagnóstico e mapeamento do MVP
- Revisar cada tela e confirmar quais recursos fazem parte do escopo essencial do app.
- Identificar diferenças entre protótipo e funcionalidade esperada no PRD.
- Validar estado atual do fluxo principal: login, tarefas, calendário e progresso.
- Definir como a aplicação vai armazenar dados no momento inicial.

### Etapa 2 — Corrigir a base de dados e estado da aplicação
- Transformar `TaskViewModel` em estrutura real para adicionar, remover, atualizar e listar tarefas.
- Criar dados iniciais consistentes e remover valores hardcoded que simulam produção.
- Este módulo deve ser a fonte única de verdade para tarefas e o estado de erro.
- Adicionar mecanismos de fallback para falhas de leitura/carga.

### Etapa 3 — Implementar navegação funcional
- Criar fluxo de navegação entre telas do app com rotas ou navegação por estado simples.
- Garantir que ações de botão no app realmente levem a outra tela, sem funções vazias.
- Conectar `MainContainerScreen` com os demais componentes de forma estável.
- Integrar `SubjectsScreen`, `SubjectDetailsScreen`, `ProfileScreen` e `StatisticsScreen` ao fluxo principal.

### Etapa 4 — Corrigir telas de tarefa e calendário
- Implementar criação de tarefa com campos válidos e feedback ao usuário.
- Adicionar seleção de data e horário reais para cada tarefa.
- Ajustar a exibição do calendário para refletir tarefas do dia selecionado.
- Garantir que a lista de tarefas e o progresso do home reflitam o estado atual do app.

### Etapa 5 — Ajustar autenticação e cadastro
- Definir a regra mínima de login/cadastro para a versão inicial.
- Validar e-mail, senha e confirmação de senha antes de prosseguir.
- Criar mensagens de validação concisas e amigáveis.
- Deixar as telas de autenticação consistentes com a proposta escolar e a identidade visual do app.

### Etapa 6 — Revisão visual e consistência
- Padronizar componentes, tamanhos, espaçamentos e cores.
- Revisar textos para garantir coerência de linguagem.
- Ajustar top bar, cards, cards de progresso e botões para um padrão único.
- Garantir que elementos interativos tenham estado visual claro.

### Etapa 7 — Testes e validação final
- Testar fluxo completo: cadastro/login, criação de tarefa, visualização do calendário e acompanhamento do progresso.
- Validar que o app não quebra ao criar tarefas vazias ou dados inválidos.
- Confirmar que a interface funciona em cenários de erro e estado vazio.
- Revisar se o projeto atende ao MVP e às regras do `AGENTS.md`.

## 6. Riscos
- Escopo descontrolado: o app pode crescer visualmente sem cumprir o MVP essencial.
- Persistência insuficiente: sem armazenamento, o app perde utilidade em uso real.
- Navegação incompleta: telas bonitas, mas sem fluxo funcional, geram frustração no usuário.
- Foco excessivo na aparência: estética não substitui estrutura e lógica correta.
- Falta de build e validação: sem automação, bugs e inconsistências podem passar despercebidos.

Mitigações:
- manter o foco no MVP definido pelo PRD;
- priorizar funcionalidade antes de refinamento visual;
- usar fluxo simples e consistente de dados;
- testar as operações principais antes de avançar para telas extras.

## 7. Critérios de pronto
- Todas as telas essenciais do app possuem fluxo funcional e sem ações vazias.
- O usuário consegue criar, visualizar e marcar tarefas como concluídas.
- O calendário exibe tarefas de forma coerente com base em datas reais.
- Login e cadastro têm regras mínimas de validação e feedback.
- O app deixa de depender de dados fixos e passa a refletir estado real de uso.
- A interface está visualmente consistente com a proposta do Organizaí.
- O projeto está pronto para próxima etapa de refinamento ou validação com usuários.

---

## Observação final
O projeto atual está em uma fase de protótipo funcional visual, mas ainda não está pronto como app de uso real. A principal correção é transformar o código de “simulação de telas” em um fluxo de aplicação com dados, navegação e regras válidas para o MVP.
