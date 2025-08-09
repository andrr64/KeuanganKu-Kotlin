package com.andreas.keuangankuplus.domain.model

data class GoalModel(
    val id: Int,
    val nama: String,
    val target: Long,
    val terkumpul: Long,
    val tercapai: Boolean
)