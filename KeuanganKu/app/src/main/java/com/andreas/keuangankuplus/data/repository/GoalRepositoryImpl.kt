package com.andreas.keuangankuplus.data.repository

import com.andreas.keuangankuplus.data.local.GoalDao
import com.andreas.keuangankuplus.data.local.GoalEntity
import com.andreas.keuangankuplus.domain.model.GoalModel
import com.andreas.keuangankuplus.domain.repository.GoalRepository
import javax.inject.Inject

class GoalRepositoryImpl @Inject constructor(
    private val dao: GoalDao
) : GoalRepository {

    private fun GoalEntity.toDomain() = GoalModel(
        id, name, target, collected, achieved,
        deadline,
        createdAt,
        updatedAt
    )

    private fun GoalModel.toEntity() = GoalEntity(
        id,
        name,
        target,
        collected,
        achieved,
        deadline,
        createdAt,
        updatedAt
    )

    override suspend fun insertGoal(goal: GoalModel) {
        dao.insert(goal.toEntity())
    }

    override suspend fun getGoalById(id: Int): GoalModel? {
        return dao.getGoalById(id)?.toDomain()
    }

    override suspend fun getAllGoals(): List<GoalModel> {
        return dao.getAllGoals().map { it.toDomain() }
    }

    override suspend fun updateGoal(goal: GoalModel) {
        dao.update(goal.toEntity())
    }

    override suspend fun deleteGoal(id: Int) {
        dao.deleteGoalById(id)
    }
}

