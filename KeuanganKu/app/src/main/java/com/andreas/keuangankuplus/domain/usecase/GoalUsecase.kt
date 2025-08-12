package com.andreas.keuangankuplus.domain.usecase

import com.andreas.keuangankuplus.domain.model.GoalModel
import com.andreas.keuangankuplus.domain.repository.GoalRepository
import kotlinx.coroutines.flow.Flow
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

class GetAllGoalsAsFlowUseCase @Inject constructor(private val repository: GoalRepository){
    operator fun invoke(): Flow<List<GoalModel>> = repository.getAllGoalsFlow()
}

class UpdateGoalUseCase @Inject constructor(private val repository: GoalRepository) {
    suspend operator fun invoke(goal: GoalModel) = repository.updateGoal(goal)
}

class DeleteGoalUseCase @Inject constructor(private val repository: GoalRepository) {
    suspend operator fun invoke(id: Int) = repository.deleteGoal(id)
}


class ToggleGoalAchievedUseCase @Inject constructor(
    private val repository: GoalRepository
) {
    suspend operator fun invoke(goal: GoalModel) {
        val updatedGoal = goal.copy(achieved = !goal.achieved)
        repository.updateGoal(updatedGoal)
    }
}
