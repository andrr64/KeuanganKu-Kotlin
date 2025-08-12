package com.andreas.keuanganku.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andreas.keuanganku.data.local.dao.CategoryDao
import com.andreas.keuanganku.data.local.dao.GoalDao
import com.andreas.keuanganku.data.local.entity.CategoryEntity
import com.andreas.keuanganku.data.local.entity.GoalEntity

@Database(entities = [GoalEntity::class, CategoryEntity::class], version = 5)
abstract class AppDatabase : RoomDatabase() {
    abstract fun goalDao(): GoalDao
    abstract fun categoryDao(): CategoryDao
}
