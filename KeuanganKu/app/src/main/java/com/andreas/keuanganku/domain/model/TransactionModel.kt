package com.andreas.keuanganku.domain.model

import java.time.LocalDateTime

data class TransactionModel(
    val id: Int,
    val accountId: Int,
    val categoryId: Int,
    val amount: Double,
    val date: LocalDateTime,
    val note: String?,
)
