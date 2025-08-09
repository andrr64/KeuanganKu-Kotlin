package com.andreas.keuangankuplus.domain.model

data class GoalModel(
    val id: Int,
    val name: String,
    val target: Long,
    val collected: Long,
    val achieved: Boolean
)