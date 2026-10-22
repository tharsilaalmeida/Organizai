package com.fogueteiras.organizai.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Representa uma disciplina ou matéria de estudo (ex: Matemática, História).
 * Utilizada para categorizar tarefas e organizar o cronograma acadêmico.
 */
@Entity(tableName = "subjects")
data class Subject(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val colorHex: Int, // Armazena a cor representativa da matéria em formato ARGB
    val teacherName: String? = null,
    val room: String? = null
)
