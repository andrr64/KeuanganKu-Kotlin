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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.andreas.keuanganku.presentation.ui.component.input.InputDropdown
import com.andreas.keuanganku.presentation.ui.component.input.InputTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalAddCategory(
    onDismiss: () -> Unit,
    onSave: (name: String, type: Int, color: String?) -> Unit
) {
    var categoryName by remember { mutableStateOf("") }
    var selectedTypeIndex by remember { mutableIntStateOf(0) }
    val typeOptions = listOf("Income", "Expense")

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Add New Category",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Create a category to organize your transactions.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            InputTextField(
                value = categoryName,
                onValueChange = { categoryName = it },
                label = "Category Name"
            )

            Spacer(modifier = Modifier.height(16.dp))

            InputDropdown(
                label = "Type",
                options = typeOptions,
                selectedIndex = selectedTypeIndex,
                onOptionSelected = { selectedTypeIndex = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = onDismiss) {
                    Text("Cancel")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        if (categoryName.isNotBlank() && selectedTypeIndex != -1) {
                            ///Todo: IMPLEMENT COLOR
                            onSave(categoryName, selectedTypeIndex, null)
                            onDismiss()
                        }
                    }
                ) {
                    Text("Save Category")
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}
