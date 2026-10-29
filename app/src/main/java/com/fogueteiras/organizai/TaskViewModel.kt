package com.fogueteiras.organizai

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fogueteiras.organizai.data.Task
import com.fogueteiras.organizai.data.local.OrganizaiDao
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TaskViewModel(private val organizaiDao: OrganizaiDao) : ViewModel() {

    val tasks: StateFlow<List<Task>> = organizaiDao.getAllTasks()
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
                organizaiDao.updateTask(task.copy(isCompleted = !task.isCompleted))
                _hasError.value = false
            } catch (e: Exception) {
                _hasError.value = true
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
                _hasError.value = false
            } catch (e: Exception) {
                _hasError.value = true
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            try {
                organizaiDao.deleteTask(task)
                _hasError.value = false
            } catch (e: Exception) {
                _hasError.value = true
            }
        }
    }
}
