package com.andreas.keuanganku.domain.repository

import com.andreas.keuanganku.domain.model.GoalModel
import kotlinx.coroutines.flow.Flow

interface GoalRepository {
    suspend fun insertGoal(goal: GoalModel)
    suspend fun getGoalById(id: Int): GoalModel?
    suspend fun getAllGoals(): List<GoalModel>
    fun getAllGoalsFlow(): Flow<List<GoalModel>>
    suspend fun updateGoal(goal: GoalModel)
    suspend fun deleteGoal(id: Int)
}
