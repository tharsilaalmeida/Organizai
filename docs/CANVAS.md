# Canvas do Projeto Final — Organizaí

|                            |                                                                 |
| -------------------------- | --------------------------------------------------------------- |
| **Grupo**                  | As Fogueteiras                                                  |
| **Integrantes**            | Tharsila Caroline, Lavinya Hadassa, Ingrid Vitória, Maria Isadora |
| **Turma**                  | 3º ano — Ensino Médio                                           |
| **Repositório**            | *(preencher)*                                                   |
| **Data de preenchimento**  | 09/09/2026                                                      |
| **Entrega final**          | **10/12/2026**                                                  |

---

## Bloco 1 — Nome e pitch do app

**Nome do app:** Organizaí

**Pitch:**

> O Organizaí é uma plataforma digital criada para auxiliar estudantes na organização de sua rotina de estudos, reunindo em um único ambiente ferramentas de planejamento, acompanhamento de tarefas e visualização do progresso acadêmico.

---

## Bloco 2 — Problema

Muitos estudantes têm dificuldade para organizar seus horários, controlar suas atividades e manter uma rotina constante de estudos. Essas informações costumam ficar espalhadas em cadernos, agendas, aplicativos diferentes ou anotações, dificultando o acompanhamento da rotina.

O Organizaí resolve isso centralizando essas informações em uma única plataforma, facilitando o planejamento e o acompanhamento dos estudos.

---

## Bloco 3 — Público-alvo

* **Perfil principal:** estudantes do Ensino Fundamental, do Ensino Médio, de cursos técnicos e pessoas que desejam organizar melhor sua rotina de estudos.
* **Quando/onde usam:** durante a rotina diária de estudos, em casa, na escola ou em outros ambientes onde o estudante realize suas atividades.
* **Principal necessidade:** planejar horários, registrar atividades e acompanhar o desempenho ao longo do tempo.
* **Pessoa que vai testar:** *(definir um estudante fora do grupo para testar o `.apk`)*

---

## Bloco 4 — Solução em uma tela

* **A tela principal apresenta:** a organização de estudos do usuário, incluindo as atividades e tarefas cadastradas.
* **A ação principal:** cadastrar, visualizar e organizar tarefas e atividades de estudo.
* **Depois de agir:** o usuário acompanha quais atividades já foram realizadas, quais ainda estão pendentes, e visualiza seu progresso ao longo do tempo.

---

## Bloco 5 — Funcionalidades do MVP

| #  | Funcionalidade                          | Essencial? |
| -- | ----------------------------------------- | ---------- |
| F1 | Criar uma conta e realizar login          | Sim        |
| F2 | Cadastrar materiais de estudo             | Sim        |
| F3 | Criar, editar e excluir tarefas           | Sim        |
| F4 | Marcar tarefas como concluídas            | Sim        |
| F5 | Visualizar atividades em um calendário    | Sim        |
| F6 | Acompanhar o progresso das atividades     | Sim        |

---

## Bloco 6 — Fora do escopo

*(a definir pelo grupo — o PRD ainda não especifica o que fica de fora da primeira versão; recomenda-se registrar aqui o que não entra até 10/12, por exemplo notificações, gamificação ou recursos sociais, se decidido pelo grupo)*

---

## Bloco 7 — Caminho técnico

* [ ] **Opção A — Room**
* [ ] **Opção B — Retrofit**
* [ ] **Opção C — Ambas**

*(o PRD ainda não define a estrutura de armazenamento — "os dados serão armazenados de acordo com a estrutura definida durante o desenvolvimento"; o grupo precisa decidir e marcar a opção acima)*

**Bibliotecas previstas:**

* Kotlin
* Android Studio

**`try/catch`:**

* **Pode falhar:** cadastro, login, leitura, gravação ou carregamento de dados.
* **Mensagem ao usuário:** "Não foi possível carregar os dados. Tente novamente."

---

## Bloco 8 — Identidade visual

| Item                | Definição                       |
| -------------------- | ---------------------------------- |
| **Nome exibido**     | Organizaí                          |
| **Tecnologia**       | Kotlin / Android Studio            |
| **Plataforma**       | Android                            |
| **Versão inicial**   | 1.0                                |
| **Interface**        | Simples e intuitiva                |

**Identidade:** visual jovem, simples e organizada, transmitindo a ideia de planejamento, estudos e produtividade. *(cores e ícone ainda não definidos no PRD)*

---

## Bloco 9 — Equipe, papéis e riscos

| Integrante          | Papel principal   | Responsável por                            |
| --------------------- | ------------------ | --------------------------------------------- |
| Tharsila Caroline      | Desenvolvimento     | Interfaces e funcionalidades                  |
| Lavinya Hadassa        | Desenvolvimento     | Organização e implementação dos dados         |
| Ingrid Vitória         | Design              | Aparência e identidade visual                 |
| Maria Isadora          | Documentação        | Documentação, build e entrega                 |

> Observação: todos os integrantes programam e revisam o trabalho dos demais. Os papéis indicam quem acompanha principalmente cada área.

**Riscos:**

| Risco                                             | Plano B                                                              |
| ---------------------------------------------------- | ------------------------------------------------------------------------ |
| Falha no carregamento dos dados                       | Exibir mensagem clara e permitir nova tentativa                          |
| Erro ao salvar uma tarefa                             | Tratar com `try/catch` e não encerrar o app inesperadamente              |
| Problemas durante o acesso às informações do usuário  | Validar autenticação e tratar erros de acesso                            |
| Falhas relacionadas ao armazenamento dos dados        | Definir estrutura de armazenamento simples e testada desde o início      |

---

## Bloco 10 — Acordo de trabalho com IA

**Regras para o `AGENTS.md`:**

1. A IA segue as funcionalidades definidas no Canvas e no PRD.
2. Todo código gerado é revisado e entendido pelo grupo antes de aceitar.
3. Nenhuma alteração importante entra sem um integrante ler e compreender o código.
4. A IA não deve adicionar funcionalidades que estejam fora do escopo sem autorização do grupo.
5. Toda funcionalidade implementada deve ser testada antes de ser considerada concluída.

O grupo segue os combinados padrão do curso (revisar antes de aceitar, comentário de fronteira de quem aceitou, revisão conjunta antes de cada marco e nenhuma chave de API no prompt).

---

## Bloco 11 — Definição de pronto

* [ ] O usuário consegue criar uma conta.
* [ ] O usuário consegue acessar sua conta.
* [ ] O usuário consegue cadastrar matérias ou materiais de estudo.
* [ ] O usuário consegue criar tarefas.
* [ ] O usuário consegue editar e excluir tarefas.
* [ ] O usuário consegue marcar tarefas como concluídas.
* [ ] O usuário consegue organizar suas atividades.
* [ ] O usuário consegue visualizar suas atividades em um calendário.
* [ ] O usuário consegue acompanhar seu progresso de estudos.
* [ ] O app não fecha sozinho e nunca mostra tela branca — erros sempre viram mensagens claras.
* [ ] App com nome, ícone e cores próprias.
* [ ] `.apk` testado por pessoas de fora do grupo.
* [ ] `README.md`, `docs/USO_DE_IA.md` e `AGENTS.md` preenchidos.

---

## Validação do professor

|                  |                                                    |
| ----------------- | ---------------------------------------------------- |
| **Data**           |                                                       |
| **Situação**       | ( ) Aprovado ( ) Aprovado com ajustes ( ) Refazer     |
| **Observações**    |                                                       |
