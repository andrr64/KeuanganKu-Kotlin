package com.andreas.keuangankuplus.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "goals")
data class GoalEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val target: Long?, // nullable
    val collected: Long, // default 0
    val achieved: Boolean,
    val deadline: Long?,   // nullable
    val createdAt: Long,
    val updatedAt: Long
)
