package com.fogueteiras.organizai.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String = "",
    val subject: String,
    val dueDate: String,
    val isCompleted: Boolean = false,
    val colorHex: Int = 0xFF81C784.toInt()
)
