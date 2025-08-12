package com.andreas.keuanganku.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GoalEntity::class, CategoryEntity::class], version = 5)
abstract class AppDatabase : RoomDatabase() {
    abstract fun goalDao(): GoalDao
}
