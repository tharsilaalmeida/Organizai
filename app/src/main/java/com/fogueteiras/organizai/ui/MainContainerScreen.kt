package com.fogueteiras.organizai.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

import androidx.lifecycle.viewmodel.compose.viewModel
import com.fogueteiras.organizai.TaskViewModel

@Composable
fun MainContainerScreen(viewModel: TaskViewModel = viewModel()) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Início", "Calendário", "Materiais", "Perfil")
    val icons = listOf(
        Icons.Default.Home,
        Icons.Default.CalendarMonth,
        Icons.Default.LibraryBooks,
        Icons.Default.Person
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(icons[index], contentDescription = item) },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        }
    ) { innerPadding ->
        // Aqui simulamos a troca de telas baseada no clique
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedItem) {
                0 -> HomeScreen(viewModel)
                1 -> CalendarScreen(viewModel)
                2 -> SubjectsScreen()
                3 -> ProfileScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainContainerPreview() {
    MaterialTheme {
        MainContainerScreen()
    }
}
