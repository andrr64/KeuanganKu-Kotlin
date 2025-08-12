package com.andreas.keuanganku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.andreas.keuanganku.presentation.ui.navigation.AppNavGraph
import com.andreas.keuanganku.presentation.ui.theme.KeuanganKuTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KeuanganKuTheme(
                darkTheme = true
            ) {
                val isDarkTheme by remember { mutableStateOf(true) }
                val navController = rememberNavController()
                AppNavGraph(isDarkTheme = isDarkTheme, navController = navController)
            }
        }
    }
}