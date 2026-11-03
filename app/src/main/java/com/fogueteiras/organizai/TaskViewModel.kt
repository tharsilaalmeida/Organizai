// O que faz: Gerencia o estado e as operações de tarefas aplicando os quatro estados (Carregando, Vazio, Erro, Sucesso).
// Por que existe: Separa a lógica de negócios da UI e garante tratamento de exceções com try/catch.
// O que quebra se sumir: A ViewModel deixa de emitir os estados e a tela não consegue processar os dados nem erros.

package com.fogueteiras.organizai

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fogueteiras.organizai.data.Task
import com.fogueteiras.organizai.data.local.OrganizaiDao
import com.fogueteiras.organizai.ui.UiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException

class TaskViewModel(private val organizaiDao: OrganizaiDao) : ViewModel() {

    private val _estado = MutableStateFlow<UiState>(UiState.Carregando)
    val estado: StateFlow<UiState> = _estado.asStateFlow()

    init {
        carregar()
    }

    fun carregar() {
        viewModelScope.launch {
            _estado.value = UiState.Carregando
            try {
                organizaiDao.getAllTasks().collect { lista ->
                    _estado.value = if (lista.isEmpty()) {
                        UiState.Vazio
                    } else {
                        UiState.Sucesso(lista)
                    }
                }
            } catch (e: IOException) {
                _estado.value = UiState.Erro("Não foi possível carregar os dados. Tente novamente.")
            } catch (e: Exception) {
                _estado.value = UiState.Erro("Não foi possível carregar os dados. Tente novamente.")
            }
        }
    }

    fun toggleTaskCompletion(task: Task) {
        viewModelScope.launch {
            try {
                organizaiDao.updateTask(task.copy(isCompleted = !task.isCompleted))
            } catch (e: Exception) {
                _estado.value = UiState.Erro("Não foi possível carregar os dados. Tente novamente.")
            }
        }
    }

    fun addTask(title: String, subject: String, color: Color) {
        viewModelScope.launch {
            try {
                val newTask = Task(
                    title = title,
                    subject = subject,
                    dueDate = "2026-10-10",
                    isCompleted = false,
                    colorHex = color.toArgb()
                )
                organizaiDao.insertTask(newTask)
            } catch (e: Exception) {
                _estado.value = UiState.Erro("Não foi possível carregar os dados. Tente novamente.")
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            try {
                organizaiDao.deleteTask(task)
            } catch (e: Exception) {
                _estado.value = UiState.Erro("Não foi possível carregar os dados. Tente novamente.")
            }
        }
    }
}
