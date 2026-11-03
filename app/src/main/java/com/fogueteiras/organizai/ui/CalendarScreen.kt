// O que faz: Exibe o calendário e as tarefas do dia tratando os quatro estados da UI (Carregando, Vazio, Erro, Sucesso).
// Por que existe: Permite visualização temporal das tarefas com tratamento robusto de erros e estados.
// O que quebra se sumir: A tela de calendário falha ao compilar e exibir as tarefas baseadas no estado da ViewModel.

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fogueteiras.organizai.TaskViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.fogueteiras.organizai.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(viewModel: TaskViewModel) {
    val estado by viewModel.estado.collectAsState()

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
                        aoClicar = { /* Navegar para cadastro se necessário */ }
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
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        // Seletor de Mês (Simplificado)
                        Text(
                            text = "Outubro 2026",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        // Linha de Dias da Semana
                        val days = (7..13).toList()
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
