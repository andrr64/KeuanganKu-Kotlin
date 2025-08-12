package com.andreas.keuanganku.domain.model

data class AccountModel(
    val id: Int,
    val name: String,
    val type: String,
    val balance: Long,
    val isActive: Boolean
)
