package com.fogueteiras.organizai.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Representa uma disciplina ou matéria de estudo.
 */
@Entity(tableName = "subjects")
data class Subject(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val colorHex: Int,
    val teacherName: String? = null,
    val room: String? = null
)

/**
 * Entidade que representa uma tarefa de estudo.
 */
@Entity(
    tableName = "tasks",
    foreignKeys = [
        ForeignKey(
            entity = Subject::class,
            parentColumns = ["id"],
            childColumns = ["subjectId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String = "",
    val subjectId: Int,
    val date: String = "",
    val time: String = "",
    val isCompleted: Boolean = false,
    val colorHex: Int
)
