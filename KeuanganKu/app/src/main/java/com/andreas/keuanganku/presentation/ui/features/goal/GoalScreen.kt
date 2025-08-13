package com.andreas.keuanganku.presentation.ui.features.goal

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.andreas.keuanganku.domain.model.GoalModel
import com.andreas.keuanganku.presentation.ui.component.HeaderWithSearch
import com.andreas.keuanganku.presentation.ui.component.item.GoalItem
import com.andreas.keuanganku.presentation.viewmodel.GoalViewModel
import com.andreas.keuanganku.presentation.viewmodel.UiEvent

@Composable
fun GoalScreen(
    isDarkTheme: Boolean,
    onThemeChange: () -> Unit,
    viewModel: GoalViewModel
) {
    val goals by viewModel.goals.collectAsState()

    var showAddGoalDialog by rememberSaveable { mutableStateOf(false) }
    var showConfirmDelete by remember { mutableStateOf(false) }
    var selectedGoalToEdit by remember { mutableStateOf<GoalModel?>(null) }

    var filterAchieved by remember { mutableStateOf("all") }
    var filterLimit by remember { mutableStateOf("all") }
    var searchKeyword by remember { mutableStateOf("") }

    val context = LocalContext.current

    // Listen UiEvent
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            if (event is UiEvent.SaveResult) {
                Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Filtering
    val filteredGoals = remember(goals, filterAchieved, filterLimit, searchKeyword) {
        goals
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
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddGoalDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Goal")
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
                HeaderWithSearch(
                    title = "My Goals",
                    description = "Track your financial goals, whether in progress or completed.",
                    isDarkTheme = isDarkTheme,
                    searchValue = searchKeyword,
                    onSearchChange = { searchKeyword = it },
                    searchPlaceholder = "Search goals...",
                    filterContent = {
                        GoalFilterSection(
                            filterTercapai = filterAchieved,
                            onFilterTercapaiChange = { filterAchieved = it },
                            filterJumlah = filterLimit,
                            onFilterJumlahChange = { filterLimit = it }
                        )
                    }
                )
            }


            if (filteredGoals.isEmpty()) {
                item { GoalEmptyState(isDarkTheme) }
            } else {
                items(filteredGoals) { goal ->
                    GoalItem(
                        goal = goal,
                        whenClicked = {
                            selectedGoalToEdit = goal
                            Log.e("GoalScreen", "Edit goal : $goal")
                        },
                        whenCheckPressed = { viewModel.toggleGoalStatus(goal) }
                    )
                }
            }

            item { Spacer(Modifier.height(90.dp)) }
        }
    }

    GoalDialogs(
        showAddGoalDialog = showAddGoalDialog,
        onDismissAdd = { showAddGoalDialog = false },
        onSaveAdd = { name, target, date -> viewModel.addGoal(name, target, date) },

        showConfirmDelete = showConfirmDelete,
        onConfirmDelete = {
            selectedGoalToEdit?.let { viewModel.deleteGoal(it.id) }
            selectedGoalToEdit = null
            showConfirmDelete = false
        },
        onDismissDelete = { showConfirmDelete = false },

        selectedGoalToEdit = selectedGoalToEdit,
        onDismissEdit = { selectedGoalToEdit = null },
        onSaveEdit = { oldGoal, name, target, date ->
            viewModel.updateGoal(oldGoal, name, target, date)
            selectedGoalToEdit = null
        },
        onDeleteFromEdit = { showConfirmDelete = true }
    )
}