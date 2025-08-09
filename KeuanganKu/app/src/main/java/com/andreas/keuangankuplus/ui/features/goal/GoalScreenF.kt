package com.andreas.keuangankuplus.ui.features.goal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.andreas.keuangankuplus.domain.model.GoalModel
import com.andreas.keuangankuplus.ui.component.GoalItem

@Composable
fun GoalScreenF(
    isDarkTheme: Boolean,
    onThemeChange: () -> Unit
){
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

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .background(if (!isDarkTheme) Color.White else MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Goal",
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.titleLarge,
                color = if (isDarkTheme) Color.White else Color.Black
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "Daftar tujuan keuangan yang sedang atau telah dicapai.",
                style = MaterialTheme.typography.bodyMedium,
                color = if (isDarkTheme) Color.Gray else Color.DarkGray
            )
            Spacer(Modifier.height(16.dp))
            GoalFilterSection(
                filterTercapai = filterTercapai,
                onFilterTercapaiChange = { filterTercapai = it },
                filterJumlah = filterJumlah,
                onFilterJumlahChange = { filterJumlah = it }
            )
            Spacer(Modifier.height(16.dp))
            GoalSearchBar(
                value = searchKeyword,
                onValueChange = { searchKeyword = it }
            )
            Spacer(Modifier.height(16.dp))
        }

        if (filteredGoals.isEmpty()) {
            item {
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
            }
        } else {
            items(filteredGoals) { goal ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isDarkTheme) Color(0xFF1E1E1E) else Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    GoalItem(goal = goal, isDarkTheme = isDarkTheme, ketikaChecklist = {})
                }
            }
        }
    }

}