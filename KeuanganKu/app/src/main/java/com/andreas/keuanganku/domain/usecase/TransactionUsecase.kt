package com.andreas.keuanganku.domain.usecase

import com.andreas.keuanganku.domain.model.TransactionModel
import com.andreas.keuanganku.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionUsecase @Inject constructor(
    private val repository: TransactionRepository
) {
    suspend fun insertTransaction(transaction: TransactionModel) {
        repository.insertTransaction(transaction)
    }

    suspend fun updateTransaction(transaction: TransactionModel) {
        repository.updateTransaction(transaction)
    }

    suspend fun deleteTransaction(transaction: TransactionModel) {
        repository.deleteTransaction(transaction)
    }

    fun getAllTransactions(): Flow<List<TransactionModel>> {
        return repository.getAllTransactions()
    }

    suspend fun getTransactionById(id: Int): TransactionModel? {
        return repository.getTransactionById(id)
    }

    fun getTransactionsByDateRange(startDate: Long, endDate: Long): Flow<List<TransactionModel>> {
        return repository.getTransactionsByDateRange(startDate, endDate)
    }

    suspend fun clearAll() {
        repository.clearAll()
    }
}
