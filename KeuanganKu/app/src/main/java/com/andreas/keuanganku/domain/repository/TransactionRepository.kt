package com.andreas.keuanganku.domain.repository

import com.andreas.keuanganku.domain.model.TransactionModel
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun insertTransaction(transaction: TransactionModel)
    suspend fun updateTransaction(transaction: TransactionModel)
    suspend fun deleteTransaction(transaction: TransactionModel)
    fun getAllTransactions(): Flow<List<TransactionModel>>
    suspend fun getTransactionById(id: Int): TransactionModel?
    fun getTransactionsByDateRange(startDate: Long, endDate: Long): Flow<List<TransactionModel>>
    suspend fun clearAll()
}
