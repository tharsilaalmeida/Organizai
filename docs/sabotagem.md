# Registro de Testes de Sabotagem - Organizaí

Este documento registra os testes de sabotagem realizados no aplicativo Organizaí, conforme exigido pela Atividade 07 (Os Quatro Estados da Tela).

| Sabotagem | Como provocar | O que apareceu na tela | Status |
| :---- | :---- | :---- | :---- |
| 1. Lista vazia | Limpar os dados locais do banco Room / iniciar sem tarefas cadastradas | Mensagem clara informando que nenhuma tarefa foi cadastrada e botão "Adicionar a primeira" | Sucesso ✅ |
| 2. Sem internet / Falha de Rede | Ativar o modo avião e abrir o aplicativo | Mensagem de erro tratada pelo try/catch: "Não foi possível carregar os dados. Tente novamente." e botão "Tentar novamente" | Sucesso ✅ |
| 3. Carregando visível | Simular latência na busca de dados / estado inicial Carregando | Indicador de progresso circular (`CircularProgressIndicator`) centralizado na tela | Sucesso ✅ |
| 4. Campo obrigatório vazio | Tentar adicionar nova tarefa sem preencher o título | O botão de confirmação permanece desativado (`title.isNotBlank()`), impedindo salvamento inválido | Sucesso ✅ |
| 5. Erro do Servidor / Exceção no DAO | Forçar falha no bloco try/catch da ViewModel | Mensagem de erro amigável em português e botão "Tentar novamente" funcionando perfeitamente | Sucesso ✅ |

Conclusão: Em nenhum dos 5 cenários de sabotagem o aplicativo apresentou tela em branco ou fechou abruptamente. Todos os quatro estados (Carregando, Vazio, Erro e Sucesso) foram tratados com sucesso.
