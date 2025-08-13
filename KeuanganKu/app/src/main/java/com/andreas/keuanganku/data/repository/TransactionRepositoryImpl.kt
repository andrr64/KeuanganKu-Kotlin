package com.andreas.keuanganku.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.andreas.keuanganku.data.local.dao.TransactionDao
import com.andreas.keuanganku.data.local.entity.TransactionEntity
import com.andreas.keuanganku.domain.model.TransactionModel
import com.andreas.keuanganku.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
class TransactionRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao
) : TransactionRepository {

    override suspend fun insertTransaction(transaction: TransactionModel) {
        transactionDao.insertTransaction(transaction.toEntity())
    }

    override suspend fun updateTransaction(transaction: TransactionModel) {
        transactionDao.updateTransaction(transaction.toEntity())
    }

    override suspend fun deleteTransaction(transaction: TransactionModel) {
        transactionDao.deleteTransaction(transaction.toEntity())
    }

    override fun getAllTransactions(): Flow<List<TransactionModel>> {
        return transactionDao.getAllTransactions().map { list ->
            list.map { it.toModel() }
        }
    }

    override suspend fun getTransactionById(id: Int): TransactionModel? {
        return transactionDao.getTransactionById(id)?.toModel()
    }

    override fun getTransactionsByDateRange(
        startDate: Long,
        endDate: Long
    ): Flow<List<TransactionModel>> {
        return transactionDao.getTransactionsByDateRange(startDate, endDate).map { list ->
            list.map { it.toModel() }
        }
    }

    override suspend fun clearAll() {
        transactionDao.clearAll()
    }

    // ---------- Mapper ---------
    private fun TransactionModel.toEntity(): TransactionEntity {
        return TransactionEntity(
            id = id,
            accountId = accountId,
            categoryId = categoryId,
            amount = amount,
            date = date.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
            note = note
        )
    }

    private fun TransactionEntity.toModel(): TransactionModel {
        return TransactionModel(
            id = id,
            accountId = accountId,
            categoryId = categoryId,
            amount = amount,
            date = LocalDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneId.systemDefault()),
            note = note
        )
    }
}
