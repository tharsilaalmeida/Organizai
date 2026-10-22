package com.fogueteiras.organizai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.fogueteiras.organizai.ui.MainContainerScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = lightColorScheme(
                    primary = Color(0xFF4CAF50), // Verde Organizaí
                    onPrimary = Color.White,
                    surface = Color.White,
                    background = Color(0xFFF8F9FA)
                )
            ) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainContainerScreen()
                }
            }
        }
    }
}
