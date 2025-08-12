package com.andreas.keuanganku.presentation.ui.modal

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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.andreas.keuanganku.presentation.ui.component.input.InputDate
import com.andreas.keuanganku.presentation.ui.component.input.InputNumericField
import com.andreas.keuanganku.presentation.ui.component.input.InputTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalAddGoal(
    onDismiss: () -> Unit,
    onSave: (name :String, target: Double, date: String) -> Unit
) {
    var goalName by remember { mutableStateOf("") }
    var target by remember { mutableDoubleStateOf(0.0) }
    var date by remember { mutableStateOf("") }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Title
            Text(
                text = "Create New Goal",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Set a goal to keep track of your progress and stay motivated.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Goal Name
            InputTextField(
                value = goalName,
                onValueChange = { goalName = it },
                label = "Goal Name",
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Deadline
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                InputDate(
                    value = date,
                    onValueChange = { date = it },
                    label = "Deadline (Optional)",
                    modifier = Modifier.weight(0.7f)
                )
                Button(
                    onClick = { date = "" },
                    modifier = Modifier
                        .weight(0.3f)
                        .padding(start = 8.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = MaterialTheme.colorScheme.onError
                    )
                ) {
                    Text("Reset")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Target Amount
            InputNumericField(
                value = target,
                onValueChange = { target = it },
                label = "Target Amount (Optional)"
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Action Buttons
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = onDismiss) {
                    Text("Cancel")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    if (goalName.isNotBlank()) {
                        onSave(goalName, target, date)
                        onDismiss()
                    }
                }) {
                    Text("Save Goal")
                }
            }
        }
    }
}
