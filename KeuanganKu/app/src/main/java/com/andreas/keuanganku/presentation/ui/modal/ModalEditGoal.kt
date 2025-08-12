package com.andreas.keuanganku.presentation.ui.modal

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
import com.andreas.keuanganku.domain.model.GoalModel
import com.andreas.keuanganku.presentation.ui.component.input.InputDate
import com.andreas.keuanganku.presentation.ui.component.input.InputNumericField
import com.andreas.keuanganku.presentation.ui.component.input.InputTextField
import com.andreas.keuanganku.util.DateTimeUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalEditGoal(
    goal: GoalModel,
    onDismiss: () -> Unit,
    onSave: (oldGoal: GoalModel, name: String, target: Double, date: String) -> Unit,
    onDelete: () -> Unit,
) {
    var goalName by remember(goal.id) { mutableStateOf(goal.name) }
    var target by remember(goal.id) {
        mutableDoubleStateOf(goal.target?.toDouble() ?: 0.0)
    }
    var deadline by remember(goal.id) {
        mutableStateOf(DateTimeUtils.formatTimestampToDateOnly(goal.deadline))
    }
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Title
            Text(
                text = "Edit Goal",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Update your goal details to keep everything up to date.",
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
                    value = deadline,
                    onValueChange = { date ->
                        deadline = date
                    },
                    label = "Deadline (Optional)",
                    modifier = Modifier.weight(0.7f)
                )
                Button(
                    onClick = { deadline = "" },
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
            Row {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = MaterialTheme.colorScheme.onError
                    ),
                    onClick = {
                        onDelete()
                    }) {
                    Text("Delete")
                }
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
                            onSave(goal, goalName, target, deadline)
                            Log.d(
                                "ModalEditGoal.kt",
                                "Send updated data : $goalName, $target, $deadline"
                            )
                        }
                    }) {
                        Text("Update Goal")
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}