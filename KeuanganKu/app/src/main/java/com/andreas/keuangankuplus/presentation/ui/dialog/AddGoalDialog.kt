package com.andreas.keuangankuplus.presentation.ui.dialog

import android.util.Log
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
import com.andreas.keuangankuplus.domain.model.GoalModel
import com.andreas.keuangankuplus.presentation.ui.component.form.InputDateTime
import com.andreas.keuangankuplus.presentation.ui.component.form.InputNumericField
import com.andreas.keuangankuplus.presentation.ui.component.form.InputTextField


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGoalDialog(
    onDismiss: () -> Unit,
    onSave: (GoalModel) -> Unit
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
                text = "New Goal",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            InputTextField(
                value = goalName,
                onValueChange = { goalName = it },
                label = "Name",
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                InputDateTime(
                    value = dateTime,
                    onValueChange = { dateTime = it },
                    label = "Tenggat (Optional)",
                    modifier = Modifier.weight(0.7f)
                )
                // Tombol Reset (30%)
                Button(
                    onClick = { dateTime = "" },
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
                        val now = System.currentTimeMillis()
                        val finalTarget = if (target != 0.0) target.toLong() else null
                        val finalDeadline = dateTime.toLongOrNull()
                        val goal = GoalModel(
                            id = 0,
                            name = goalName,
                            target = finalTarget,
                            collected = 0L,
                            achieved = false,
                            deadline = finalDeadline,
                            createdAt = now,
                            updatedAt = now
                        )
                        Log.d("AddGoalDialog.kt", "add new data$goal")
                        onSave(goal)
                    }

                }) {
                    Text("Save")
                }
            }
        }
    }
}
