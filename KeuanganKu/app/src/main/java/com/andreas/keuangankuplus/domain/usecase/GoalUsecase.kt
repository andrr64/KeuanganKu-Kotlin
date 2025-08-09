package com.andreas.keuangankuplus.domain.usecase

import com.andreas.keuangankuplus.domain.model.GoalModel
import com.andreas.keuangankuplus.domain.repository.GoalRepository

class InsertGoalUseCase(private val repository: GoalRepository) {
    suspend operator fun invoke(goal: GoalModel) = repository.insertGoal(goal)
}

class GetGoalByIdUseCase(private val repository: GoalRepository) {
    suspend operator fun invoke(id: Int): GoalModel? = repository.getGoalById(id)
}

class GetAllGoalsUseCase(private val repository: GoalRepository) {
    suspend operator fun invoke(): List<GoalModel> = repository.getAllGoals()
}

class UpdateGoalUseCase(private val repository: GoalRepository) {
    suspend operator fun invoke(goal: GoalModel) = repository.updateGoal(goal)
}

class DeleteGoalUseCase(private val repository: GoalRepository) {
    suspend operator fun invoke(id: Int) = repository.deleteGoal(id)
}
