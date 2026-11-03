// O que faz: Exibe a tela principal do Organizaí tratando os quatro estados da UI (Carregando, Vazio, Erro, Sucesso).
// Por que existe: Evita telas em branco e fornece feedback visual claro com o botão Tentar novamente.
// O que quebra se sumir: A interface deixa de reagir aos estados do ViewModel e a aplicação perde a renderização principal.

package com.fogueteiras.organizai.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fogueteiras.organizai.R
import com.fogueteiras.organizai.TaskViewModel
import com.fogueteiras.organizai.data.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: TaskViewModel) {
    val estado by viewModel.estado.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }

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
            FloatingActionButton(
                onClick = { showAddDialog = true },
                shape = RoundedCornerShape(20.dp),
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar Tarefa")
            }
        }
    ) { innerPadding ->
        if (showAddDialog) {
            AddTaskDialog(
                onDismiss = { showAddDialog = false },
                onConfirm = { title, subject, color ->
                    viewModel.addTask(title, subject, color)
                    showAddDialog = false
                }
            )
        }

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when (val atual = estado) {
                is UiState.Carregando -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is UiState.Vazio -> {
                    MensagemCentral(
                        texto = stringResource(R.string.lista_vazia),
                        rotuloBotao = stringResource(R.string.adicionar_primeiro),
                        aoClicar = { showAddDialog = true }
                    )
                }

                is UiState.Erro -> {
                    MensagemCentral(
                        texto = atual.mensagem,
                        rotuloBotao = stringResource(R.string.tentar_novamente),
                        aoClicar = { viewModel.carregar() }
                    )
                }

                is UiState.Sucesso -> {
                    val tasks = atual.itens
                    val completedCount = tasks.count { it.isCompleted }
                    val progress = if (tasks.isNotEmpty()) completedCount.toFloat() / tasks.size else 0f

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        // Card de Progresso
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text("Seu Progresso de Hoje", fontSize = 18.sp, fontWeight = FontWeight.Medium)
                                Spacer(modifier = Modifier.height(8.dp))
                                LinearProgressIndicator(
                                    progress = progress,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(8.dp),
                                    color = MaterialTheme.colorScheme.primary,
                                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                                    strokeCap = androidx.compose.ui.graphics.StrokeCap.Round
                                )
                                Text("${(progress * 100).toInt()}% das tarefas concluídas", modifier = Modifier.padding(top = 8.dp), fontSize = 14.sp)
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Text("Próximas Tarefas", fontSize = 20.sp, fontWeight = FontWeight.Bold)

                        Spacer(modifier = Modifier.height(8.dp))

                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(tasks) { task ->
                                HomeTaskItem(task) {
                                    viewModel.toggleTaskCompletion(task)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MensagemCentral(
    texto: String,
    rotuloBotao: String,
    aoClicar: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = texto,
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = aoClicar,
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(rotuloBotao)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskDialog(onDismiss: () -> Unit, onConfirm: (String, String, Color) -> Unit) {
    var title by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }
    val colors = listOf(Color(0xFF81C784), Color(0xFFFFD54F), Color(0xFF64B5F6), Color(0xFFFF8A65))
    var selectedColor by remember { mutableStateOf(colors[0]) }

    AlertDialog(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(20.dp),
        title = { Text("Nova Tarefa", fontWeight = FontWeight.Bold) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Título") },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = subject,
                    onValueChange = { subject = it },
                    label = { Text("Matéria") },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                )
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    colors.forEach { color ->
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .padding(2.dp)
                                .background(color, CircleShape)
                                .clickable { selectedColor = color }
                                .let { 
                                    if (selectedColor == color) it.background(Color.Black.copy(alpha = 0.2f), CircleShape) else it
                                }
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = { if (title.isNotBlank()) onConfirm(title, subject, selectedColor) },
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("Adicionar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun HomeTaskItem(task: Task, onToggle: () -> Unit) {
    OutlinedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        onClick = onToggle
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(12.dp).background(Color(task.colorHex), CircleShape))
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = task.title, 
                        fontWeight = FontWeight.SemiBold,
                        color = if (task.isCompleted) Color.Gray else Color.Unspecified
                    )
                    Text(task.subject, fontSize = 12.sp, color = Color.Gray)
                }
            }
            Checkbox(checked = task.isCompleted, onCheckedChange = { _ -> onToggle() })
        }
    }
}
