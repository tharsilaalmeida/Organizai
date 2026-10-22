package com.fogueteiras.organizai.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")
data class Subject(
    @PrimaryKey val name: String,
    val colorArgb: Int
)

@Entity(
    tableName = "tasks",
    foreignKeys = [
        ForeignKey(
            entity = Subject::class,
            parentColumns = ["name"],
            childColumns = ["subjectName"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val subjectName: String,
    val time: String = "",
    val date: String = "",
    val isCompleted: Boolean = false,
    val colorArgb: Int
)
