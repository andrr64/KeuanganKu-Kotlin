package com.andreas.keuanganku.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val accountId: Int,
    val categoryId: Int,
    val amount: Double,
    val date: Long,
    val note: String?,
)