package com.fogueteiras.organizai.data

import androidx.compose.ui.graphics.Color

data class Task(
    val id: Int = 0,
    val title: String,
    val subject: String,
    val time: String = "",
    val date: String = "",
    val isCompleted: Boolean = false,
    val color: Color = Color.Gray
)

data class Subject(
    val name: String,
    val color: Color
)
