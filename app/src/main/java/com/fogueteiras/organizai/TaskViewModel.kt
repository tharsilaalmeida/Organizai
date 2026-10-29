package com.fogueteiras.organizai

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fogueteiras.organizai.data.Task
import com.fogueteiras.organizai.data.TaskDao
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TaskViewModel(private val taskDao: TaskDao) : ViewModel() {
    val tasks: StateFlow<List<Task>> = taskDao.getAllTasks()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _hasError = MutableStateFlow(false)
    val hasError: StateFlow<Boolean> = _hasError.asStateFlow()

    fun toggleTaskCompletion(task: Task) {
        viewModelScope.launch {
            try {
                taskDao.updateTask(task.copy(isCompleted = !task.isCompleted))
                _hasError.value = false
            } catch (e: Exception) {
                _hasError.value = true
            }
        }
    }

    fun addTask(title: String, subject: String, colorHex: Int) {
        viewModelScope.launch {
            try {
                val newTask = Task(
                    title = title,
                    subject = subject,
                    dueDate = "2026-10-10", // Data padrão para o protótipo
                    isCompleted = false,
                    colorHex = colorHex
                )
                taskDao.insertTask(newTask)
                _hasError.value = false
            } catch (e: Exception) {
                _hasError.value = true
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            try {
                taskDao.deleteTask(task)
                _hasError.value = false
            } catch (e: Exception) {
                _hasError.value = true
            }
        }
    }
}
