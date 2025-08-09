package com.andreas.keuangankuplus.ui.features.goal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.andreas.keuangankuplus.domain.model.GoalModel
import com.andreas.keuangankuplus.ui.component.GoalItem
import kotlin.math.min

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoalScreen(
    isDarkTheme: Boolean,
    onThemeChange: () -> Unit
) {
    var goals by remember {
        mutableStateOf(
            listOf(
                GoalModel(1, "Beli Motor", 15000000, 5000000, false),
                GoalModel(2, "Dana Darurat", 10000000, 10000000, true),
                GoalModel(3, "Liburan Bali", 5000000, 2500000, false)
            )
        )
    }

    var filterTercapai by remember { mutableStateOf("all") }
    var filterJumlah by remember { mutableStateOf("all") }
    var searchKeyword by remember { mutableStateOf("") }

    val filteredGoals = goals
        .filter {
            when (filterTercapai) {
                "true" -> it.tercapai
                "false" -> !it.tercapai
                else -> true
            }
        }
        .filter {
            it.nama.contains(searchKeyword, ignoreCase = true)
        }
        .let { list ->
            if (filterJumlah != "all") {
                val jumlah = filterJumlah.toIntOrNull() ?: list.size
                list.take(jumlah)
            } else list
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "Daftar Goal",
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.titleLarge,
            color = if (isDarkTheme) Color.White else Color.Black
        )
        Text(
            text = "Daftar tujuan keuangan yang sedang atau telah dicapai",
            style = MaterialTheme.typography.bodySmall,
            color = if (isDarkTheme) Color.Gray else Color.DarkGray
        )

        Spacer(Modifier.height(16.dp))

        // ===== Filter Modern =====
        GoalFilterSection(
            filterTercapai = filterTercapai,
            onFilterTercapaiChange = { filterTercapai = it },
            filterJumlah = filterJumlah,
            onFilterJumlahChange = { filterJumlah = it }
        )

        Spacer(Modifier.height(12.dp))

        // Search Bar
        GoalSearchBar(
            value = searchKeyword,
            onValueChange = { searchKeyword = it }
        )

        Spacer(Modifier.height(16.dp))

        // ===== List Goal =====
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            if (filteredGoals.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Tidak ada goal yang ditemukan.",
                        color = if (isDarkTheme) Color.Gray else Color.DarkGray,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp) // jarak antar item
                ) {
                    items(filteredGoals) { goal ->
                        Card(
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (isDarkTheme) Color(0xFF1E1E1E) else Color.White
                            ),
                            modifier = Modifier.fillMaxWidth()
                                .padding(4.dp)
                        ) {
                            GoalItem(goal = goal, isDarkTheme = isDarkTheme, ketikaChecklist = {})
                        }
                    }
                }

            }
        }
    }
}

