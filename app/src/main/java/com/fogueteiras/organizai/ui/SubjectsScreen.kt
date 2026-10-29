package com.fogueteiras.organizai.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.toArgb
import com.fogueteiras.organizai.data.Subject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectsScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Minhas Matérias", fontWeight = FontWeight.Black) }
            )
        }
    ) { innerPadding ->
        val subjects = listOf(
            Subject("Matemática", Color(0xFFBBDEFB).toArgb()),
            Subject("Português", Color(0xFFF8BBD0).toArgb()),
            Subject("História", Color(0xFFFFF9C4).toArgb()),
            Subject("Geografia", Color(0xFFC8E6C9).toArgb()),
            Subject("Física", Color(0xFFD1C4E9).toArgb()),
            Subject("Química", Color(0xFFFFCCBC).toArgb())
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(subjects) { subject ->
                SubjectCard(subject)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectCard(subject: Subject) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(subject.colorHex)),
        onClick = { /* Navegar para detalhes */ }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(Icons.Default.Book, contentDescription = null, tint = Color.DarkGray)
                Text(
                    text = subject.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SubjectsScreenPreview() {
    MaterialTheme {
        SubjectsScreen()
    }
}
