package com.andreas.keuanganku.presentation.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun Header(
    title: String,
    description: String?,
    isDarkTheme: Boolean
) {
    Text(
        text = title,
        fontWeight = FontWeight.SemiBold,
        style = MaterialTheme.typography.titleLarge,
        color = if (isDarkTheme) Color.White else Color.Black
    )
    Spacer(Modifier.height(4.dp))
    description?.let {
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = if (isDarkTheme) Color.Gray else Color.DarkGray
        )
    }
}