package com.andreas.keuangankuplus.presentation.ui.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.andreas.keuangankuplus.presentation.ui.component.form.InputDateTime
import com.andreas.keuangankuplus.presentation.ui.component.form.InputNumericField
import com.andreas.keuangankuplus.presentation.ui.component.form.InputTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGoalDialog(
    onDismiss: () -> Unit,
    onSave: (String) -> Unit
) {
    var goalName by remember { mutableStateOf("") }
    var target by remember { mutableDoubleStateOf(0.0) }
    var dateTime by remember { mutableStateOf("") }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Add New Goal",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            InputTextField(
                value = goalName,
                onValueChange = { goalName = it },
                label = "Name",
            )
            Spacer(modifier = Modifier.height(16.dp))
            InputDateTime(
                value = dateTime,
                onValueChange = { dateTime = it },
                label = "Tenggat (Optional)"
            )
            Spacer(modifier = Modifier.height(16.dp))
            InputNumericField(
                value = target,
                onValueChange = {target = it},
                label = "Price (Optional)"
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = onDismiss) {
                    Text("Batal")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    if (goalName.isNotBlank()) {
                        onSave(goalName)
                    }
                }) {
                    Text("Simpan")
                }
            }
        }
    }
}
