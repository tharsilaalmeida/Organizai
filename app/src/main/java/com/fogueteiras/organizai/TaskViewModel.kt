package com.fogueteiras.organizai

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import com.fogueteiras.organizai.data.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TaskViewModel : ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    private val _hasError = MutableStateFlow(false)
    val hasError: StateFlow<Boolean> = _hasError.asStateFlow()

    init {
        loadTasks()
    }

    private fun loadTasks() {
        try {
            _tasks.value = listOf(
                Task(1, "Exercícios de Frações", "Revisão cap. 4", "Matemática", "2026-10-10", false, Color(0xFF81C784).toArgb()),
                Task(2, "Resumo: Segunda Guerra", "Focar no Brasil", "História", "2026-10-10", true, Color(0xFFFFD54F).toArgb()),
                Task(3, "Análise de Poemas", "Modernismo", "Português", "2026-10-11", false, Color(0xFF64B5F6).toArgb()),
                Task(4, "Mapa Mental: Células", "Mitocôndrias", "Biologia", "2026-10-12", false, Color(0xFFFF8A65).toArgb())
            )
            _hasError.value = false
        } catch (e: Exception) {
            _hasError.value = true
        }
    }

    fun toggleTaskCompletion(taskId: Int) {
        _tasks.value = _tasks.value.map {
            if (it.id == taskId) it.copy(isCompleted = !it.isCompleted) else it
        }
    }

    fun addTask(title: String, subject: String, color: Color) {
        val newTask = Task(
            id = (_tasks.value.maxOfOrNull { it.id } ?: 0) + 1,
            title = title,
            description = "",
            subject = subject,
            dueDate = "2026-10-10", // Data padrão para o protótipo
            isCompleted = false,
            colorHex = color.toArgb()
        )
        _tasks.value = _tasks.value + newTask
    }
}
