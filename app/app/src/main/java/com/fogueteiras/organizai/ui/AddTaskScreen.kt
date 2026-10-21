package com.fogueteiras.organizai.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen() {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("10/10/2026") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nova Tarefa", fontWeight = FontWeight.Bold) },
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
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "O que você vai estudar?",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary
            )

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Título da tarefa") },
                placeholder = { Text("Ex: Revisar Biologia") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descrição (opcional)") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            OutlinedTextField(
                value = date,
                onValueChange = { date = it },
                label = { Text("Data de entrega") },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Icon(Icons.Default.DateRange, contentDescription = "Selecionar Data")
                },
                readOnly = true
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { /* Salvar tarefa */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Criar Tarefa", fontSize = 16.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddTaskScreenPreview() {
    MaterialTheme {
        AddTaskScreen()
    }
}
