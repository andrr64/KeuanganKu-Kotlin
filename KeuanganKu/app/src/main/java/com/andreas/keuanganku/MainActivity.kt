package com.andreas.keuanganku

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.andreas.keuanganku.presentation.ui.navigation.AppNavGraph
import com.andreas.keuanganku.presentation.ui.theme.KeuanganKuTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KeuanganKuTheme(
                darkTheme = false
            ) {
                val isDarkTheme by remember { mutableStateOf(false) }
                val navController = rememberNavController()
                AppNavGraph(isDarkTheme = isDarkTheme, navController = navController)
            }
        }
    }
}