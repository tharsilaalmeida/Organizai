package com.fogueteiras.organizai.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticsScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Meu Desempenho", fontWeight = FontWeight.Bold) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Card de Resumo Geral
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.TrendingUp, contentDescription = null, modifier = Modifier.size(40.dp))
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text("Ótimo trabalho!", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text("Você concluiu 15 tarefas esta semana.", fontSize = 14.sp)
                    }
                }
            }

            Text("Tarefas por Matéria", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)

            // Simulação de Gráfico de Barras Simples
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                StatBar("Matemática", 0.8f, Color(0xFFBBDEFB))
                StatBar("História", 0.5f, Color(0xFFFFCCBC))
                StatBar("Biologia", 0.9f, Color(0xFFC8E6C9))
                StatBar("Física", 0.3f, Color(0xFFFFF9C4))
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Dica da IA/App
            OutlinedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("💡 Dica do Organizaí", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.secondary)
                    Text(
                        "Você está indo muito bem em Biologia! Que tal dedicar 30 minutinhos a mais para Física hoje?",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun StatBar(label: String, progress: Float, color: Color) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(label, fontSize = 14.sp)
            Text("${(progress * 100).toInt()}%", fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            color = color,
            trackColor = Color.LightGray.copy(alpha = 0.3f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StatisticsScreenPreview() {
    MaterialTheme {
        StatisticsScreen()
    }
}
