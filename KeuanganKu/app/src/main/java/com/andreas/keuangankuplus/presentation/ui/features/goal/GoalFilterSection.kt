package com.andreas.keuangankuplus.presentation.ui.features.goal

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoalFilterSection(
    filterTercapai: String,
    onFilterTercapaiChange: (String) -> Unit,
    filterJumlah: String,
    onFilterJumlahChange: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Filter Status
        var expandedStatus by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = expandedStatus,
            onExpandedChange = { expandedStatus = !expandedStatus },
            modifier = Modifier.weight(1f)
        ) {
            OutlinedTextField(
                value = when (filterTercapai) {
                    "true" -> "Tercapai"
                    "false" -> "Belum Tercapai"
                    else -> "Semua"
                },
                onValueChange = {},
                readOnly = true,
                label = { Text("Status") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expandedStatus) },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expandedStatus,
                onDismissRequest = { expandedStatus = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Semua") },
                    onClick = { onFilterTercapaiChange("all"); expandedStatus = false }
                )
                DropdownMenuItem(
                    text = { Text("Tercapai") },
                    onClick = { onFilterTercapaiChange("true"); expandedStatus = false }
                )
                DropdownMenuItem(
                    text = { Text("Belum Tercapai") },
                    onClick = { onFilterTercapaiChange("false"); expandedStatus = false }
                )
            }
        }

        // Filter Jumlah
        var expandedJumlah by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = expandedJumlah,
            onExpandedChange = { expandedJumlah = !expandedJumlah },
            modifier = Modifier.weight(1f)
        ) {
            OutlinedTextField(
                value = when (filterJumlah) {
                    "5" -> "5 Data"
                    "10" -> "10 Data"
                    else -> "Semua"
                },
                onValueChange = {},
                readOnly = true,
                label = { Text("Jumlah Data") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expandedJumlah) },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expandedJumlah,
                onDismissRequest = { expandedJumlah = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Semua") },
                    onClick = { onFilterJumlahChange("all"); expandedJumlah = false }
                )
                DropdownMenuItem(
                    text = { Text("5 Data") },
                    onClick = { onFilterJumlahChange("5"); expandedJumlah = false }
                )
                DropdownMenuItem(
                    text = { Text("10 Data") },
                    onClick = { onFilterJumlahChange("10"); expandedJumlah = false }
                )
            }
        }
    }
}
