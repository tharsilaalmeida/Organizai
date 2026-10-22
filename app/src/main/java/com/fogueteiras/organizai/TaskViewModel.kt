package com.fogueteiras.organizai

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fogueteiras.organizai.data.Subject
import com.fogueteiras.organizai.data.Task
import com.fogueteiras.organizai.repository.TaskRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    val tasks: StateFlow<List<Task>> = repository.allTasks
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val subjects: StateFlow<List<Subject>> = repository.allSubjects
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun toggleTaskCompletion(task: Task) {
        viewModelScope.launch {
            try {
                repository.updateTaskStatus(task.id, !task.isCompleted)
            } catch (e: Exception) {
                _errorMessage.value = "Não foi possível carregar os dados. Tente novamente."
            }
        }
    }

    fun addTask(title: String, subjectId: Int, color: Color) {
        viewModelScope.launch {
            try {
                val newTask = Task(
                    title = title,
                    subjectId = subjectId,
                    colorHex = color.toArgb(),
                    isCompleted = false
                )
                repository.insertTask(newTask)
            } catch (e: Exception) {
                _errorMessage.value = "Não foi possível carregar os dados. Tente novamente."
            }
        }
    }
    
    fun clearError() {
        _errorMessage.value = null
    }
}
