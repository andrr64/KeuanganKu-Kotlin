package com.andreas.keuangankuplus.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreas.keuangankuplus.domain.model.GoalModel
import com.andreas.keuangankuplus.domain.usecase.DeleteGoalUseCase
import com.andreas.keuangankuplus.domain.usecase.GetAllGoalsUseCase
import com.andreas.keuangankuplus.domain.usecase.InsertGoalUseCase
import com.andreas.keuangankuplus.domain.usecase.UpdateGoalUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GoalViewModel(
    private val insertGoalUseCase: InsertGoalUseCase,
    private val getAllGoalsUseCase: GetAllGoalsUseCase,
    private val updateGoalUseCase: UpdateGoalUseCase,
    private val deleteGoalUseCase: DeleteGoalUseCase
) : ViewModel() {

    private val _goals = MutableStateFlow<List<GoalModel>>(emptyList())
    val goals: StateFlow<List<GoalModel>> get() = _goals

    fun loadGoals() {
        viewModelScope.launch {
            _goals.value = getAllGoalsUseCase()
        }
    }

    fun addGoal(goal: GoalModel) {
        viewModelScope.launch {
            insertGoalUseCase(goal)
            loadGoals()
        }
    }

    fun updateGoal(goal: GoalModel) {
        viewModelScope.launch {
            updateGoalUseCase(goal)
            loadGoals()
        }
    }

    fun deleteGoal(id: Int) {
        viewModelScope.launch {
            deleteGoalUseCase(id)
            loadGoals()
        }
    }
}
