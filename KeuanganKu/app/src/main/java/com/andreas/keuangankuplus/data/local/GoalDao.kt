package com.andreas.keuangankuplus.data.local

import androidx.room.*

@Dao
interface GoalDao {
    @Insert
    suspend fun insert(goal: GoalEntity)

    @Query("SELECT * FROM goals WHERE id = :id")
    suspend fun getGoalById(id: Int): GoalEntity?

    @Query("SELECT * FROM goals")
    suspend fun getAllGoals(): List<GoalEntity>

    @Update
    suspend fun update(goal: GoalEntity)

    @Query("DELETE FROM goals WHERE id = :id")
    suspend fun deleteGoalById(id: Int)
}