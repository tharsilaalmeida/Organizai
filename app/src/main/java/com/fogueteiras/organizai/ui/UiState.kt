// O que faz: Define os quatro estados possíveis da tela (Carregando, Vazio, Erro, Sucesso).
// Por que existe: Garante tratamento robusto de UI e elimina telas em branco.
// O que quebra se sumir: A máquina de estados da UI e a compilação do when exaustivo.

package com.fogueteiras.organizai.ui

import com.fogueteiras.organizai.data.Task

sealed interface UiState {
    data object Carregando : UiState
    data object Vazio : UiState
    data class Erro(val mensagem: String) : UiState
    data class Sucesso(val itens: List<Task>) : UiState
}
