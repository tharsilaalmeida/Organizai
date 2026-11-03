package com.fogueteiras.organizai.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalhes da Tarefa", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { /* Voltar */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Deletar Tarefa (F3) */ }) {
                        Icon(Icons.Default.Delete, contentDescription = "Excluir")
                    }
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { /* Editar Tarefa (F3) */ },
                icon = { Icon(Icons.Default.Edit, contentDescription = null) },
                text = { Text("Editar") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Text(
                text = "Revisar Matemática",
                fontSize = 26.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.primary
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Surface(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = "Prazo: 10 Out 2026 - 14:00",
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Descrição",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "Estudar os capítulos 3 e 4 do livro didático. Focar em equações de segundo grau e fórmulas de Bhaskara para o simulado de sexta-feira.",
                fontSize = 16.sp,
                lineHeight = 24.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Status da Atividade",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            
            Row(
                modifier = Modifier.padding(top = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = false, 
                    onCheckedChange = { /* F4 - Marcar como concluída */ }
                )
                Text(
                    text = "Marcar como concluída",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskDetailScreenPreview() {
    MaterialTheme {
        TaskDetailScreen()
    }
}
