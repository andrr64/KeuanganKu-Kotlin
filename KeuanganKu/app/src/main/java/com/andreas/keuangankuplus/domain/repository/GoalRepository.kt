package com.andreas.keuangankuplus.domain.repository

import com.andreas.keuangankuplus.domain.model.GoalModel

interface GoalRepository {
    suspend fun insertGoal(goal: GoalModel)
    suspend fun getGoalById(id: Int): GoalModel?
    suspend fun getAllGoals(): List<GoalModel>
    suspend fun updateGoal(goal: GoalModel)
    suspend fun deleteGoal(id: Int)
}
