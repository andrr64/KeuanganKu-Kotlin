package com.andreas.keuanganku.domain.model

data class GoalModel(
    val id: Int,
    val name: String,
    val target: Long?,
    val collected: Long,
    val achieved: Boolean,
    val deadline: Long?,
    val createdAt: Long,
    val updatedAt: Long
)
