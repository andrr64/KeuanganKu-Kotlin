package com.andreas.keuangankuplus.presentation.ui.features.goal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.andreas.keuangankuplus.presentation.ui.component.AppDropdown

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
        AppDropdown(
            label = "Status",
            selectedText = when (filterTercapai) {
                "true" -> "Tercapai"
                "false" -> "Belum Tercapai"
                else -> "Semua"
            },
            options = listOf("Semua", "Tercapai", "Belum Tercapai"),
            onOptionSelected = { option ->
                onFilterTercapaiChange(
                    when (option) {
                        "Tercapai" -> "true"
                        "Belum Tercapai" -> "false"
                        else -> "all"
                    }
                )
            },
            modifier = Modifier.weight(1f) // ✅ biar proporsional
        )

        AppDropdown(
            label = "Jumlah Data",
            selectedText = when (filterJumlah) {
                "5" -> "5 Data"
                "10" -> "10 Data"
                else -> "Semua"
            },
            options = listOf("Semua", "5 Data", "10 Data"),
            onOptionSelected = { option ->
                onFilterJumlahChange(
                    when (option) {
                        "5 Data" -> "5"
                        "10 Data" -> "10"
                        else -> "all"
                    }
                )
            },
            modifier = Modifier.weight(1f) // ✅ sama kayak sebelumnya
        )
    }

}
