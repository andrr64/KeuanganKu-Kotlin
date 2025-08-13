package com.andreas.keuanganku.presentation.ui.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.andreas.keuanganku.presentation.ui.component.Header


@Composable
fun HomeScreen(
    isDarkTheme: Boolean,
    onThemeChange: () -> Unit
) {
    Scaffold(
        containerColor = colorScheme.background
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Header(
                    title = "Hello, Andreas!",
                    description = "Track your finances easily and stay on top of your goals.",
                    isDarkTheme = isDarkTheme
                )
            }

        }
    }
}