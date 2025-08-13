package com.andreas.keuanganku.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andreas.keuanganku.data.local.dao.CategoryDao
import com.andreas.keuanganku.data.local.dao.GoalDao
import com.andreas.keuanganku.data.local.dao.TransactionDao
import com.andreas.keuanganku.data.local.entity.CategoryEntity
import com.andreas.keuanganku.data.local.entity.GoalEntity
import com.andreas.keuanganku.data.local.entity.TransactionEntity

@Database(
    entities = [
        GoalEntity::class,
        CategoryEntity::class,
        TransactionEntity::class
    ],
    version = 8
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun goalDao(): GoalDao
    abstract fun categoryDao(): CategoryDao
    abstract fun transactionDao(): TransactionDao
}
