package com.andreas.keuanganku.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreas.keuanganku.domain.model.GoalModel
import com.andreas.keuanganku.domain.usecase.DeleteGoalUseCase
import com.andreas.keuanganku.domain.usecase.GetAllGoalsAsFlowUseCase
import com.andreas.keuanganku.domain.usecase.InsertGoalUseCase
import com.andreas.keuanganku.domain.usecase.ToggleGoalAchievedUseCase
import com.andreas.keuanganku.domain.usecase.UpdateGoalUseCase
import com.andreas.keuanganku.util.DateTimeUtils
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
    private val getAllGoalsUseCase: GetAllGoalsAsFlowUseCase,
    private val updateGoalUseCase: UpdateGoalUseCase,
    private val deleteGoalUseCase: DeleteGoalUseCase,
    private val toggleGoalAchievedUseCase: ToggleGoalAchievedUseCase
) : ViewModel() {

    private val _goals = MutableStateFlow<List<GoalModel>>(emptyList())
    val goals: StateFlow<List<GoalModel>> get() = _goals

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        viewModelScope.launch {
            try {
                getAllGoalsUseCase().collect { goalsList ->
                    _goals.value = goalsList
                    Log.d("GoalViewModel", "Goals updated: ${goalsList.size} items")
                }
            } catch (e: Exception) {
                Log.e("GoalViewModel", "Error collecting goals: ${e.message}", e)
                _uiEvent.emit(UiEvent.SaveResult(false, "Gagal memuat daftar goals"))
            }
        }
    }

    fun addGoal(name: String, target: Double, date: String) {
        viewModelScope.launch {
            try {
                val now = System.currentTimeMillis()
                val finalTarget = if (target != 0.0) target.toLong() else null
                val finalDeadline = DateTimeUtils.parseDateOnlyToTimestamp(date)
                val goal = GoalModel(
                    id = 0,
                    name = name,
                    target = finalTarget,
                    collected = 0L,
                    achieved = false,
                    deadline = finalDeadline,
                    createdAt = now,
                    updatedAt = now
                )
                insertGoalUseCase(goal)
                _uiEvent.emit(UiEvent.SaveResult(true, "Goal berhasil disimpan"))
                Log.d("GoalViewModel", "Goal added: $goal")
            } catch (e: Exception) {
                _uiEvent.emit(
                    UiEvent.SaveResult(
                        false,
                        "Gagal menyimpan goal: ${e.message ?: "Kesalahan tidak diketahui"}"
                    )
                )
                Log.e("GoalViewModel", "Error adding goal: ${e.message}", e)
            }
        }
    }


    fun updateGoal(oldGoal: GoalModel, name: String, target: Double, date: String) {
        viewModelScope.launch {
            try {
                val now = System.currentTimeMillis()
                val finalTarget = if (target != 0.0) target.toLong() else null
                val finalDeadline = DateTimeUtils.parseDateOnlyToTimestamp(date)
                val goal = oldGoal.copy(
                    name = name,
                    target = finalTarget,
                    deadline = finalDeadline,
                    updatedAt = now
                )
                updateGoalUseCase(goal)
                _uiEvent.emit(UiEvent.SaveResult(true, "Goal berhasil diperbarui"))
                Log.d("GoalViewModel", "Goal updated: $goal")
            } catch (e: Exception) {
                _uiEvent.emit(
                    UiEvent.SaveResult(
                        false,
                        "Gagal memperbarui goal: ${e.message ?: "Kesalahan tidak diketahui"}"
                    )
                )
                Log.e("GoalViewModel", "Error updating goal: ${e.message}", e)
            }
        }
    }

    fun toggleGoalStatus(goal: GoalModel) {
        viewModelScope.launch {
            try {
                toggleGoalAchievedUseCase(goal)
                Log.d("GoalViewModel", "Goal status toggled: $goal")
            } catch (e: Exception) {
                _uiEvent.emit(
                    UiEvent.SaveResult(
                        false,
                        "Gagal memperbarui status goal: ${e.message ?: "Kesalahan tidak diketahui"}"
                    )
                )
                Log.e("GoalViewModel", "Error toggling goal status: ${e.message}", e)
            }
        }
    }

    fun deleteGoal(id: Int) {
        viewModelScope.launch {
            try {
                deleteGoalUseCase(id)
                _uiEvent.emit(UiEvent.SaveResult(true, "Goal berhasil dihapus"))
                Log.d("GoalViewModel", "Goal deleted with id: $id")
            } catch (e: Exception) {
                _uiEvent.emit(
                    UiEvent.SaveResult(
                        false,
                        "Gagal menghapus goal: ${e.message ?: "Kesalahan tidak diketahui"}"
                    )
                )
                Log.e("GoalViewModel", "Error deleting goal id $id: ${e.message}", e)
            }
        }
    }
}
