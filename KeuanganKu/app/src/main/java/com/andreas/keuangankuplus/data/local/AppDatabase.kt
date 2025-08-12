package com.andreas.keuangankuplus.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GoalEntity::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun goalDao(): GoalDao
}
