package com.andreas.keuangankuplus.ui.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    isDarkTheme: Boolean, // Terima info tema
    onThemeChange: () -> Unit // Terima remote kontrol
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "🏠 Halaman Home")

        // Spasi sedikit
        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Dark Mode")
            Spacer(modifier = Modifier.width(8.dp))
            // Switch yang akan memanggil remote kontrol saat diganti
            Switch(
                checked = isDarkTheme,
                onCheckedChange = { onThemeChange() }
            )
        }
    }
}