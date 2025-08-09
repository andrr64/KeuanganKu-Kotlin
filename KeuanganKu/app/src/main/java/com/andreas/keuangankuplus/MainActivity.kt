package com.andreas.keuangankuplus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.andreas.keuangankuplus.ui.MainScreen
import com.andreas.keuangankuplus.ui.theme.KeuanganKuTheme

// di dalam MainActivity.kt
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen() // Panggil Composable utama kita
        }
    }
}