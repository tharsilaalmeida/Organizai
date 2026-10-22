package com.fogueteiras.organizai.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidade Room que representa uma tarefa de estudo.
 * Implementada seguindo os padrões do Material Design 3 e Jetpack Compose.
 */
@Entity(tableName = "tarefas")
data class Tarefa(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val titulo: String,
    val descricao: String,
    val dataEntrega: Long, // Armazenado em milissegundos (Epoch Time) para compatibilidade com MD3 DatePicker
    val materia: String,
    val concluida: Boolean = false
)
