package com.fogueteiras.organizai.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fogueteiras.organizai.TaskViewModel
import com.fogueteiras.organizai.data.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: TaskViewModel) {
    val tasks by viewModel.tasks.collectAsState()
    val hasError by viewModel.hasError.collectAsState()
    
    val completedCount = tasks.count { it.isCompleted }
    val progress = if (tasks.isNotEmpty()) completedCount.toFloat() / tasks.size else 0f

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Organizaí", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { /* Abrir menu */ }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* Adicionar tarefa */ }) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar Tarefa")
            }
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
                    Text(
                        text = "Não foi possível carregar os dados. Tente novamente.",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            } else {
                // Card de Progresso
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Seu Progresso de Hoje", fontSize = 18.sp, fontWeight = FontWeight.Medium)
                        Spacer(modifier = Modifier.height(8.dp))
                        LinearProgressIndicator(
                            progress = progress,
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text("${(progress * 100).toInt()}% das tarefas concluídas", modifier = Modifier.padding(top = 4.dp), fontSize = 14.sp)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text("Próximas Tarefas", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                
                Spacer(modifier = Modifier.height(8.dp))

                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(tasks) { task ->
                        HomeTaskItem(task) {
                            viewModel.toggleTaskCompletion(task.id)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeTaskItem(task: Task, onToggle: () -> Unit) {
    OutlinedCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = onToggle
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = task.title, 
                    fontWeight = FontWeight.SemiBold,
                    color = if (task.isCompleted) Color.Gray else Color.Unspecified
                )
                Text(task.time, fontSize = 12.sp, color = Color.Gray)
            }
            Checkbox(checked = task.isCompleted, onCheckedChange = { onToggle() })
        }
    }
}
