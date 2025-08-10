package com.andreas.keuangankuplus.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreas.keuangankuplus.domain.model.GoalModel
import com.andreas.keuangankuplus.domain.usecase.DeleteGoalUseCase
import com.andreas.keuangankuplus.domain.usecase.GetAllGoalsUseCase
import com.andreas.keuangankuplus.domain.usecase.InsertGoalUseCase
import com.andreas.keuangankuplus.domain.usecase.UpdateGoalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalViewModel @Inject constructor(
    private val insertGoalUseCase: InsertGoalUseCase,
    private val getAllGoalsUseCase: GetAllGoalsUseCase,
    private val updateGoalUseCase: UpdateGoalUseCase,
    private val deleteGoalUseCase: DeleteGoalUseCase
) : ViewModel() {

    private val _goals = MutableStateFlow<List<GoalModel>>(emptyList())

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    val goals: StateFlow<List<GoalModel>> get() = _goals

    fun loadGoals() {
        viewModelScope.launch {
            try {
                _goals.value = getAllGoalsUseCase()
            } catch (e: Exception) {
                _uiEvent.emit(
                    UiEvent.SaveResult(
                        false,
                        "Gagal memuat goals: ${e.message ?: "Kesalahan"}"
                    )
                )
            }
        }
    }

    fun addGoal(name: String) {
        viewModelScope.launch {
            try {
                val now = System.currentTimeMillis()
                val randomTarget = (1_000_000L..10_000_000L).random()
                val randomDeadline = listOf<Long?>(null, now + 7L * 24 * 60 * 60 * 1000).random()

                val goalBaru = GoalModel(
                    id = 0,
                    name = name,
                    target = randomTarget,
                    collected = 0L,
                    achieved = false,
                    deadline = randomDeadline,
                    createdAt = now,
                    updatedAt = now
                )
                insertGoalUseCase(goalBaru) // suspend
                _uiEvent.emit(UiEvent.SaveResult(true, "Goal berhasil disimpan"))
                loadGoals()
            } catch (e: Exception) {
                _uiEvent.emit(
                    UiEvent.SaveResult(
                        false,
                        "Gagal menyimpan goal: ${e.message ?: "Kesalahan"}"
                    )
                )
            }
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
