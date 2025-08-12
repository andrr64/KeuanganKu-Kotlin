package com.andreas.keuangankuplus.presentation.ui.features.goal

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.andreas.keuangankuplus.domain.model.GoalModel
import com.andreas.keuangankuplus.presentation.ui.component.ConfirmationDialog
import com.andreas.keuangankuplus.presentation.ui.component.SearchField
import com.andreas.keuangankuplus.presentation.ui.component.item.GoalItem
import com.andreas.keuangankuplus.presentation.ui.modal.ModalAddGoal
import com.andreas.keuangankuplus.presentation.ui.modal.ModalEditGoal
import com.andreas.keuangankuplus.presentation.viewmodel.GoalViewModel
import com.andreas.keuangankuplus.presentation.viewmodel.UiEvent

@Composable
fun GoalScreen(
    isDarkTheme: Boolean,
    onThemeChange: () -> Unit,
) {
    val viewModel: GoalViewModel = hiltViewModel()
    val goals by viewModel.goals.collectAsState()
    var showAddGoalDialog by rememberSaveable { mutableStateOf(false) }
    var showConfirmDelete by remember { mutableStateOf(false) }

    var selectedGoalToEdit by remember { mutableStateOf<GoalModel?>(null) }
    var filterAchieved by remember { mutableStateOf("all") }
    var filterLimit by remember { mutableStateOf("all") }
    var searchKeyword by remember { mutableStateOf("") }
    val context = LocalContext.current

    // Listen for UI events
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            if (event is UiEvent.SaveResult) {
                Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Filter goals
    val filteredGoals = goals
        .filter {
            when (filterAchieved) {
                "true" -> it.achieved
                "false" -> !it.achieved
                else -> true
            }
        }
        .filter { it.name.contains(searchKeyword, ignoreCase = true) }
        .let { list ->
            if (filterLimit != "all") {
                val limit = filterLimit.toIntOrNull() ?: list.size
                list.take(limit)
            } else list
        }

    if (showAddGoalDialog) {
        ModalAddGoal(
            onDismiss = { showAddGoalDialog = false },
            onSave = { name, target, date ->
                viewModel.addGoal(name, target, date)
            }
        )
    }
    if (showConfirmDelete){
        ConfirmationDialog(
            showDialog = true,
            onConfirm = {
                selectedGoalToEdit?.let {
                    viewModel.deleteGoal(it.id)
                }
                selectedGoalToEdit = null
            },
            onDismiss = {
                showConfirmDelete = false
            },
            message = "Apakah kamu yakin ingin menghapus data ini?"
        )
    }
    if (selectedGoalToEdit != null) {
        ModalEditGoal(
            goal = selectedGoalToEdit!!, // sudah dicek tidak null
            onDismiss = { selectedGoalToEdit = null },
            onSave = {oldGoal, name, target, date ->
                viewModel.updateGoal(oldGoal, name, target, date)
                selectedGoalToEdit = null
            },
            onDelete = {
                showConfirmDelete = true
            }
        )
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddGoalDialog = true },
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Goal"
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "My Goals",
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleLarge,
                    color = if (isDarkTheme) Color.White else Color.Black
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Track your financial goals, whether in progress or completed.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (isDarkTheme) Color.Gray else Color.DarkGray
                )
                Spacer(Modifier.height(16.dp))
                SearchField(
                    value = searchKeyword,
                    placeholder = "Search goals...",
                    onValueChange = { searchKeyword = it }
                )
                Spacer(Modifier.height(16.dp))
                GoalFilterSection(
                    filterTercapai = filterAchieved,
                    onFilterTercapaiChange = { filterAchieved = it },
                    filterJumlah = filterLimit,
                    onFilterJumlahChange = { filterLimit = it }
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
                            text = "No goals found.",
                            color = if (isDarkTheme) Color.Gray else Color.DarkGray,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            } else {
                items(filteredGoals) { goal ->
                    GoalItem(
                        goal = goal,
                        whenClicked = {
                            selectedGoalToEdit = goal
                            Log.e("GoalScreen.kt", "Edit goal : $goal")
                        },
                        whenCheckPressed = { viewModel.toggleGoalStatus(goal) }
                    )
                }
            }
            item {
                Spacer(Modifier.height(90.dp))
            }
        }
    }
}
