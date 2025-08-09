package com.andreas.keuangankuplus.domain.usecase

import com.andreas.keuangankuplus.domain.model.GoalModel
import com.andreas.keuangankuplus.domain.repository.GoalRepository
import javax.inject.Inject

class InsertGoalUseCase @Inject constructor(private val repository: GoalRepository) {
    suspend operator fun invoke(goal: GoalModel) = repository.insertGoal(goal)
}

class GetGoalByIdUseCase @Inject constructor(private val repository: GoalRepository) {
    suspend operator fun invoke(id: Int): GoalModel? = repository.getGoalById(id)
}

class GetAllGoalsUseCase @Inject constructor(private val repository: GoalRepository) {
    suspend operator fun invoke(): List<GoalModel> = repository.getAllGoals()
}

class UpdateGoalUseCase @Inject constructor(private val repository: GoalRepository) {
    suspend operator fun invoke(goal: GoalModel) = repository.updateGoal(goal)
}

class DeleteGoalUseCase @Inject constructor(private val repository: GoalRepository) {
    suspend operator fun invoke(id: Int) = repository.deleteGoal(id)
}
