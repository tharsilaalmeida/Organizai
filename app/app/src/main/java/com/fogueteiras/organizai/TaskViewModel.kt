package com.fogueteiras.organizai

import androidx.compose.ui.graphics.Color
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
                Task(1, "Exercícios de Frações", "Matemática", "14:00", "2026-10-10", false, Color(0xFF81C784)),
                Task(2, "Resumo: Segunda Guerra", "História", "16:30", "2026-10-10", true, Color(0xFFFFD54F)),
                Task(3, "Análise de Poemas", "Português", "18:00", "2026-10-11", false, Color(0xFF64B5F6)),
                Task(4, "Mapa Mental: Células", "Biologia", "20:00", "2026-10-12", false, Color(0xFFFF8A65))
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
}
