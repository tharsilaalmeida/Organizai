package com.fogueteiras.organizai.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fogueteiras.organizai.TaskViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(viewModel: TaskViewModel) {
    val tasks by viewModel.tasks.collectAsState()
    val hasError by viewModel.hasError.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Calendário", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { /* Voltar */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            if (hasError) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Não foi possível carregar os dados. Tente novamente.", color = MaterialTheme.colorScheme.error)
                }
            } else {
                // Seletor de Mês (Simplificado)
                Text(
                    text = "Outubro 2026",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Linha de Dias da Semana
                val days = (7..13).toList() // Exemplo de dias
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(days) { day ->
                        DayItem(day = day.toString(), isSelected = day == 10)
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Tarefas do dia",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Filtra tarefas para o "dia 10" (exemplo estático do protótipo)
                val dailyTasks = tasks.filter { it.dueDate == "2026-10-10" }

                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(dailyTasks) { task ->
                        TaskItemSimple(task.title, "10:00", Color(task.colorHex))
                    }
                }
            }
        }
    }
}

@Composable
fun DayItem(day: String, isSelected: Boolean) {
    val backgroundColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
    val textColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable { /* Selecionar dia */ }
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        Text(text = day, fontWeight = FontWeight.Bold, color = textColor)
    }
}

@Composable
fun TaskItemSimple(title: String, time: String, tagColor: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(tagColor)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(title, fontWeight = FontWeight.Medium)
                Text(time, fontSize = 12.sp, color = Color.Gray)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarScreenPreview() {
    MaterialTheme {
        CalendarScreen(viewModel = TaskViewModel())
    }
}
