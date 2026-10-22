package com.fogueteiras.organizai.repository

import com.fogueteiras.organizai.data.Subject
import com.fogueteiras.organizai.data.Task
import com.fogueteiras.organizai.data.local.OrganizaiDao
import com.fogueteiras.organizai.data.remote.OrganizaiApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

class TaskRepository(
    private val organizaiDao: OrganizaiDao,
    private val apiService: OrganizaiApiService
) {
    val allTasks: Flow<List<Task>> = organizaiDao.getAllTasks()
        .catch {
            throw Exception("Não foi possível carregar os dados. Tente novamente.")
        }

    val allSubjects: Flow<List<Subject>> = organizaiDao.getAllSubjects()
        .catch {
            throw Exception("Não foi possível carregar os dados. Tente novamente.")
        }

    suspend fun insertTask(task: Task) {
        try {
            organizaiDao.insertTask(task)
            // Opcional: Sincronizar com remote no futuro
            // apiService.createTask(task)
        } catch (e: Exception) {
            throw Exception("Não foi possível carregar os dados. Tente novamente.")
        }
    }

    suspend fun updateTaskStatus(taskId: Int, isCompleted: Boolean) {
        try {
            organizaiDao.updateTaskStatus(taskId, isCompleted)
        } catch (e: Exception) {
            throw Exception("Não foi possível carregar os dados. Tente novamente.")
        }
    }

    suspend fun deleteTask(task: Task) {
        try {
            organizaiDao.deleteTask(task)
        } catch (e: Exception) {
            throw Exception("Não foi possível carregar os dados. Tente novamente.")
        }
    }

    suspend fun insertSubject(subject: Subject) {
        try {
            organizaiDao.insertSubject(subject)
        } catch (e: Exception) {
            throw Exception("Não foi possível carregar os dados. Tente novamente.")
        }
    }

    // Método para sincronizar com API futuramente
    suspend fun refreshTasks() {
        try {
            val remoteTasks = apiService.getTasks()
            remoteTasks.forEach { organizaiDao.insertTask(it) }
        } catch (e: Exception) {
            throw Exception("Não foi possível carregar os dados. Tente novamente.")
        }
    }
}
