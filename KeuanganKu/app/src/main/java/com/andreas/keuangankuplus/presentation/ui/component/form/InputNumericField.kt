package com.andreas.keuangankuplus.presentation.ui.component.form


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import java.text.NumberFormat
import java.util.Locale

@Composable
fun InputNumericField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isDarkTheme: Boolean
) {
    TextField(
        value = value,
        onValueChange = { input ->
            val filtered = input.filter { it.isDigit() }
            val formatted = formatUang(filtered)
            onValueChange(formatted)
        },
        label = { Text(label) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = if (isDarkTheme) Color(0xFF374151) else Color(0xFFF3F4F6),
            unfocusedContainerColor = if (isDarkTheme) Color(0xFF374151) else Color(0xFFF3F4F6),
            focusedTextColor = if (isDarkTheme) Color.White else Color.Black,
            unfocusedTextColor = if (isDarkTheme) Color.White else Color.Black,
            cursorColor = Color(0xFF6366F1),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = if (isDarkTheme) Color(0xFF374151) else Color(0xFFF3F4F6),
                shape = RoundedCornerShape(6.dp)
            )
            .border(
                width = 1.dp,
                color = if (isDarkTheme) Color(0xFF4B5563) else Color(0xFFD1D5DB),
                shape = RoundedCornerShape(6.dp)
            )
    )
}

fun formatUang(numberString: String): String {
    if (numberString.isEmpty()) return ""
    return try {
        val number = numberString.toLong()
        NumberFormat.getNumberInstance(Locale("id", "ID")).format(number)
    } catch (e: NumberFormatException) {
        numberString
    }
}