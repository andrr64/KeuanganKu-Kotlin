package com.andreas.keuangankuplus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.andreas.keuangankuplus.presentation.ui.MainScreen

// di dalam MainActivity.kt
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen() // Panggil Composable utama kita
        }
    }
}