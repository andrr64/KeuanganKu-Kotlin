package com.andreas.keuangankuplus.presentation.ui.component.form

import android.R
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import java.text.NumberFormat
import java.util.Locale

@Composable
fun InputNumericField(
    value: Double,
    onValueChange: (Double) -> Unit,
    label: String
) {
    val numberFormat = NumberFormat.getNumberInstance(Locale.US)
    var textFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                if (value == 0.0) "" else numberFormat.format(value),
                TextRange((if (value == 0.0) "" else numberFormat.format(value)).length)
            )
        )
    }

    TextField(
        value = textFieldValue,
        onValueChange = { tfv ->
            val cleanInput = tfv.text.replace(",", "")
            val number = cleanInput.toDoubleOrNull() ?: 0.0
            onValueChange(number)

            val formatted = if (number == 0.0) "" else numberFormat.format(number)

            textFieldValue = TextFieldValue(
                text = formatted,
                selection = TextRange(formatted.length)
            )
        },
        label = { Text(label) },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
            unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
            disabledIndicatorColor = androidx.compose.ui.graphics.Color.Transparent
        ),
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier.fillMaxWidth()
    )

}
